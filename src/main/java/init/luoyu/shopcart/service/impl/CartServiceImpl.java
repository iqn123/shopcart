package init.luoyu.shopcart.service.impl;

import init.luoyu.shopcart.dto.CartRecordDto;
import init.luoyu.shopcart.pojo.Phone;
import init.luoyu.shopcart.service.ICartService;
import init.luoyu.shopcart.service.IPhoneService;
import init.luoyu.shopcart.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IPhoneService phoneService;

    private Map<String,Object> currentCart= new HashMap<>();




    @Override
    public void addCart(Integer pid, Integer num, String type, String userName) {



        //去查询数据之前先获取
        currentCart= this.changeMapType(redisUtil.hmget(userName));
        if(pid==null){
            System.out.println("ID不能为NULL");
            return;
        }
        Phone phone = phoneService.selectById(pid);
        System.out.println(phone.toString());
        CartRecordDto dto = this.changeCartType(phone);

        dto.setNum(num);
        dto.setType(type);

        //如果获取到的数据为NULL说明没有添加过
        if(currentCart==null){
            currentCart.put(pid+type,dto);
            redisUtil.hmset(userName,currentCart);
        }else{
            CartRecordDto cartRecordDto= (CartRecordDto) currentCart.get(pid+type);
            if(cartRecordDto!=null){
                cartRecordDto.setNum(cartRecordDto.getNum()+num);
                redisUtil.hdel(userName,pid+type);
                currentCart.put(pid+type,cartRecordDto);
            }else{
                currentCart.put(pid+type,dto);
            }
        }

        redisUtil.hmset(userName,currentCart);
/*        Map<String,Object> map = new HashMap<>();
        map.put(pid+type,num);
        redisUtil.hmset(userName,map);*/
        currentCart=this.changeMapType(redisUtil.hmget(userName));
        CartRecordDto dto1 = (CartRecordDto) currentCart.get(pid+type);
        System.out.println(dto1);

    }

    @Override
    public List<CartRecordDto> quaryAllRecord(String userName) {

        currentCart = changeMapType(redisUtil.hmget(userName));
        List<CartRecordDto> dtos = new ArrayList<>();
        Iterator<String> iterator = currentCart.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            CartRecordDto dto = (CartRecordDto) currentCart.get(key);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public void updateCart(CartRecordDto dto) {

        currentCart = changeMapType(redisUtil.hmget("luoyu"));
        redisUtil.del("luoyu");
        CartRecordDto dto1 = (CartRecordDto) currentCart.get(dto.getPid()+dto.getType());
        dto1.setNum(dto.getNum());
        System.out.println(dto1.toString());
        currentCart.put(dto.getPid()+dto.getType(),dto1);

        redisUtil.hmset("luoyu",currentCart);

    }

    @Override
    public void deleteProdoct(CartRecordDto dto) {
        redisUtil.hdel("luoyu",dto.getPid()+dto.getType());
    }


    //map类型转换方法
    private Map<String, Object> changeMapType(Map<Object, Object> map){
        Map<String, Object> resultMap = new HashMap<>();
        Iterator<Object> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = (Object) iterator.next();
            resultMap.put((String)key, map.get(key));
        }
        return resultMap;
    }



    private CartRecordDto changeCartType(Phone phone){

        CartRecordDto dto = new CartRecordDto();

            dto.setPid(phone.getpId());
            dto.setImage(phone.getImg());
            dto.setpName(phone.getpName());
            dto.setPrice(phone.getPrice());
            return  dto;
    }

}

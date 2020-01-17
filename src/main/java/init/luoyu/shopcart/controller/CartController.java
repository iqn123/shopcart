package init.luoyu.shopcart.controller;


import init.luoyu.shopcart.dto.CartRecordDto;
import init.luoyu.shopcart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cart")
public class CartController {


    @Autowired
    private ICartService cartService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addCart(Integer pid,Integer num,String type){

        cartService.addCart(pid,num,type,"luoyu");

        return "success";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CartRecordDto> allRecord(){

        return  cartService.quaryAllRecord("luoyu");

    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String updateCart(CartRecordDto dto){

        cartService.updateCart(dto);
        return "success";
    }


    @RequestMapping(method = RequestMethod.DELETE)
    public String delProdoctByType(CartRecordDto dto){

        cartService.deleteProdoct(dto);

        return "success";
    }



}

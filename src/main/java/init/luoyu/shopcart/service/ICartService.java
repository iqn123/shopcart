package init.luoyu.shopcart.service;

import init.luoyu.shopcart.dto.CartRecordDto;

import java.util.List;

public interface ICartService {

    void addCart(Integer pid,Integer num,String type,String userName);


    List<CartRecordDto> quaryAllRecord(String userName);


    void updateCart(CartRecordDto dto);

    void deleteProdoct(CartRecordDto dto);

}

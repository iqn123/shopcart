package init.luoyu.shopcart.service;

import init.luoyu.shopcart.pojo.Phone;

import java.util.List;

public interface IPhoneService {

    Phone selectById(Integer id);

    List<Phone> selectAll();

}

package init.luoyu.shopcart.controller;

import init.luoyu.shopcart.pojo.Phone;
import init.luoyu.shopcart.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/phones")
public class PhoneController {

    @Autowired
    private IPhoneService phoneService;


    @RequestMapping(value = "/phone/{id}",method = RequestMethod.GET)
    public Phone getOnePhone(@PathVariable Integer id){

        return phoneService.selectById(id);
    }



    @RequestMapping(method = RequestMethod.GET)
    public List<Phone> allPhone(){

        return phoneService.selectAll();
    }


}

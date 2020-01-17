package init.luoyu.shopcart.service.impl;

import init.luoyu.shopcart.mapper.PhoneMapper;
import init.luoyu.shopcart.pojo.Phone;
import init.luoyu.shopcart.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private PhoneMapper phoneMapper;

    @Cacheable(value = "findPhoneById")
    @Override
    public Phone selectById(Integer id) {
        return phoneMapper.selectById(id);
    }

    @Cacheable(value = "selectAllPhone")
    @Override
    public List<Phone> selectAll() {
        return phoneMapper.selectAll();
    }
}

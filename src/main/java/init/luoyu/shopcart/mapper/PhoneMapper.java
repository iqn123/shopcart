package init.luoyu.shopcart.mapper;

import init.luoyu.shopcart.pojo.Phone;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PhoneMapper {

    @Select("select * from phone where pId=#{id}")
    @Results({
            @Result(id = true,column = "pId",property = "pId"),
            @Result(column = "pName",property = "pName"),
            @Result(column = "price",property = "price"),
            @Result(column = "desc",property = "desc"),
            @Result(column = "count",property = "count"),
            @Result(column = "img",property = "img"),
            @Result(property = "types",column = "pId",many =@Many(select = "selectPhoneType"))

    })
    Phone selectById(Integer id);


    @Select("select * from phone")
    List<Phone> selectAll();

    @Select("select typeName from type where pid=#{id}")
    List<String> selectPhoneType(@Param(value = "id")Integer id);

}

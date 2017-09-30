package com.mud.dao;

import com.mud.mapper.Product;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by leeesven on 2017/9/22.
 */
public interface ProductDao {

    @Select("SELECT * FROM product WHERE product_id = #{0}")
    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "createTime", column = "create_time")
    })
    Product getProductById(String productId);
}

package com.wzc.springboot_redis.mapper;

import com.wzc.springboot_redis.entity.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface cityMapper {
    void updateCity(City city);

    City getCity(String id);
}

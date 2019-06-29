package com.wzc.springboot_redis.service;

import com.wzc.springboot_redis.entity.City;
import com.wzc.springboot_redis.mapper.cityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class cityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(cityService.class);
    @Autowired
    private cityMapper cityDao;

    @Autowired
    private catchService catchService;


    public City getCity(String id) {
        String key = "city_" + id;
        boolean hasKey = catchService.hasKey(key);
        if(hasKey) {
            City city = (City) catchService.get(key);
            LOGGER.info("cityService.getCity(): 获取城市信息 from catch >> " + city);
            return city;
        }else {
            City city = cityDao.getCity(id);
            catchService.set(key, city,15, TimeUnit.SECONDS);
            LOGGER.info("cityService.getCity(): 信息插入缓存 >> " + city);
            return city;
        }
    }

    public City updateCity(Long id, String cityName, Long provinceId, String description) {
        City city = new City();
        city.setId(id);
        city.setCityName(cityName);
        city.setProvinceId(provinceId);
        city.setDescription(description);
        cityDao.updateCity(city);

        String key = "city_" + id;
        if(catchService.hasKey(key)) {
            catchService.deleteKey(key);
        }
        return city;

    }
}

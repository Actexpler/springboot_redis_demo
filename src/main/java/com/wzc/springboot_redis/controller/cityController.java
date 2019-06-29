package com.wzc.springboot_redis.controller;

import com.wzc.springboot_redis.entity.City;
import com.wzc.springboot_redis.service.cityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class cityController {

    @Autowired
    private cityService cityService;

    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    public City getCity(@PathVariable(value = "id")String id) {
        return cityService.getCity(id);
    }
    @RequestMapping(value = "/city/update", method = RequestMethod.POST)
    public City updateCity(@RequestParam(value = "cityName")String cityName,
                           @RequestParam(value = "description")String description,
                           @RequestParam(value = "id")Long id,
                           @RequestParam(value = "provinceId")Long provinceId){
        return cityService.updateCity(id,cityName,provinceId,description);
    }

}

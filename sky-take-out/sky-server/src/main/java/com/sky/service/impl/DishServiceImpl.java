package com.sky.service.impl;

import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishService dishService;
}

package com.sky.service.impl;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealMapper setmealMapper;
    @Autowired
    SetmealDishMapper setmealDishMapper;

    @Override
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.insert(setmeal);
        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        if (setmealDishList != null && setmealDishList.size() > 0) {
            for (SetmealDish setmealDish : setmealDishList) {
                setmealDish.setSetmealId(setmealId);
            }
            setmealDishMapper.insertBatch(setmealDishList);
        }
    }
}

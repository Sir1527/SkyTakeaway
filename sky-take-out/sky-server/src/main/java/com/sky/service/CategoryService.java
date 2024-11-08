package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 分类菜单分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增菜单分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 根据id删除菜单分类
     * @param id
     */
    void deleteById(Long id);

    /**
     * 启用或者禁用菜单分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 更新菜单分类接口
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 根据分类查询
     * @param type
     */
    List<Category> queryByType(Integer type);
}

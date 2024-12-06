package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 菜单分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 新增菜单分类
     * @param category
     */
    @Insert("insert into sky_take_out.category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    @Delete("DELETE FROM sky_take_out.category WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id修改分类
     */
    void update(Category category);

    /**
     * 根据类型查询
     * @param type
     * @return
     */
    @Select("SELECT * from sky_take_out.category where type = #{type}")
    List<Category> QueryByType(Integer type);
}

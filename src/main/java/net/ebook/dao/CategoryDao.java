package net.ebook.dao;

import net.ebook.model.Category;
import net.ebook.util.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:05 2018/1/27
 * @Modified By:
 */
@Mapper
public interface CategoryDao extends BaseDao<Category> {

    Category findById(@Param("id")long id);

    long saveCategory(@Param("category")Category category);

    List<Category> findAll();

    void update(@Param("category")Category category);
}

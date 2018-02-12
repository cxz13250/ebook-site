package net.ebook.service;

import net.ebook.model.Category;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2018/1/27
 * @Modified By:
 */
public interface CategoryService {

    List<Category> getCategoryList(int page, int rows, String keyword)throws Exception;

    Category getById(long id);

    Category create(Category category);

    Category update(Category category);

    void delete(Category category);
}

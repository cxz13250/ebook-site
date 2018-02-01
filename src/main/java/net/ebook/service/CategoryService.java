package net.ebook.service;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Category;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2018/1/27
 * @Modified By:
 */
public interface CategoryService {

    PageInfo<Category> getCategoryList(int page, int rows, String keyword)throws Exception;
}

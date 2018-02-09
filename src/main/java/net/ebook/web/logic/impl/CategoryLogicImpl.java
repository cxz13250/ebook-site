package net.ebook.web.logic.impl;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Category;
import net.ebook.service.CategoryService;
import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.CategoryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:20 2018/1/27
 * @Modified By:
 */
@Service
public class CategoryLogicImpl implements CategoryLogic{

    @Autowired
    CategoryService categoryService;

    @Override
    public PageInfo<Category> getCategoryList(int page, int rows, String keyword,HttpServletRequest request)throws Exception{
        return categoryService.getCategoryList(page,rows,keyword);
    }

    @Override
    public Category getById(long id){
        Category category=categoryService.getById(id);
        if (category == null) {
            throw new HttpBadRequestException("category not exist");
        }
        return category;
    }

    @Override
    public void update(Category category){
        if (category.getId()==0){
            throw new IllegalArgumentException("");
        }
        Category category1=categoryService.getById(category.getId());
        if (category1 == null) {
            throw new HttpBadRequestException("category not exist");
        }
        if (category1.getName()!=null){
            category1.setName(category.getName());
        }
        if (category1.getMenu()!=null){
            category1.setMenu(category.getMenu());
        }
        categoryService.update(category1);
    }

    @Override
    public void delete(Long id){
        Category category=categoryService.getById(id);
        if (category == null) {
            throw new HttpBadRequestException("category not exist");
        }
        categoryService.delete(category);
    }
}

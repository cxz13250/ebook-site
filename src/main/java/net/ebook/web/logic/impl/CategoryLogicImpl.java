package net.ebook.web.logic.impl;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Category;
import net.ebook.service.CategoryService;
import net.ebook.web.data.CategoryVO;
import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.CategoryLogic;
import net.ebook.web.wrapper.CategoryVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    CategoryVOWrapper categoryVOWrapper;

    @Override
    public PageInfo<CategoryVO> getCategoryList(int page, int rows, String keyword,HttpServletRequest request)throws Exception{
        List<Category> categories = categoryService.getCategoryList(page,rows,keyword);
        List<CategoryVO> vos = categoryVOWrapper.wrap(categories);

        return new PageInfo<CategoryVO>(vos);
    }

    @Override
    public CategoryVO create(CategoryVO vo){
        Category category=categoryVOWrapper.unwrap(vo);
        category=categoryService.create(category);
        return categoryVOWrapper.wrap(category);
    }

    @Override
    public CategoryVO getById(long id){
        Category category=categoryService.getById(id);
        if (category == null) {
            throw new HttpBadRequestException("category not exist");
        }
        return categoryVOWrapper.wrap(category);
    }

    @Override
    public CategoryVO update(CategoryVO vo){
        if (vo.getId()==0){
            throw new IllegalArgumentException("");
        }
        Category category=categoryService.getById(vo.getId());
        if (category == null) {
            throw new HttpBadRequestException("category not exist");
        }
        if (category.getName()!=null){
            category.setName(vo.getName());
        }
        if (category.getMenu()!=null){
            category.setMenu(vo.getMenu());
        }
        categoryService.update(category);
        return vo;
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

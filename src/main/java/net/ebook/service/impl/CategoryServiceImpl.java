package net.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.DeleteStatus;
import net.ebook.dao.CategoryDao;
import net.ebook.model.Category;
import net.ebook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:15 2018/1/27
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList(int page, int rows, String keyword)throws Exception{
        PageHelper.startPage(page, rows);
        List<Category> categories=categoryDao.findAll();
        return categories;
    }

    @Override
    public Category getById(long id){
        return categoryDao.findById(id);
    }

    @Override
    public Category create(Category category){
        category.setCreateTime(new Timestamp(System.currentTimeMillis()));
        category.setDeleted(DeleteStatus.IS_NOT_DELETE);
        categoryDao.saveCategory(category);
        return category;
    }

    @Override
    public Category update(Category category){
        categoryDao.update(category);
        return category;
    }

    @Override
    public void delete(Category category){
        category.setDeleted(DeleteStatus.IS_DELETE);
        categoryDao.update(category);
    }
}

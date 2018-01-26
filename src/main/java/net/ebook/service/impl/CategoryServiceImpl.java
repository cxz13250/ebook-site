package net.ebook.service.impl;

import net.ebook.dao.CategoryDao;
import net.ebook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

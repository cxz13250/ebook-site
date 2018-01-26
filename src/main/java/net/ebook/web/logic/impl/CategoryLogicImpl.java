package net.ebook.web.logic.impl;

import net.ebook.service.CategoryService;
import net.ebook.web.logic.CategoryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

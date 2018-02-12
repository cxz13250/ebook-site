package net.ebook.web.wrapper;

import net.ebook.model.Category;
import net.ebook.web.data.CategoryVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:05 2018/2/12
 * @Modified By:
 */
@Service
public class CategoryVOWrapper extends BaseWrapper<CategoryVO, Category> {

    @Override
    public CategoryVO wrap(Category category){
        CategoryVO vo=new CategoryVO();
        vo.setId(category.getId());
        vo.setDeleted(category.isDeleted());
        vo.setCreateTime(category.getCreateTime().getTime());
        vo.setMenu(category.getMenu());
        vo.setName(category.getName());
        return vo;
    }

    @Override
    public Category unwrap(CategoryVO vo){
        Category category=new Category();
        category.setMenu(vo.getMenu());
        category.setName(vo.getName());
        return category;
    }
}

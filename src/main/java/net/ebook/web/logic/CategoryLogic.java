package net.ebook.web.logic;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Category;
import net.ebook.web.data.CategoryVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:19 2018/1/27
 * @Modified By:
 */
public interface CategoryLogic {

    PageInfo<CategoryVO> getCategoryList(int page, int rows, String keyword, HttpServletRequest request)throws Exception;

    CategoryVO create(CategoryVO vo);

    CategoryVO getById(long id);

    CategoryVO update(CategoryVO vo);

    void delete(Long id);
}

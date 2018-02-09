package net.ebook.web.logic;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Book;
import net.ebook.web.data.BookVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:19 2018/1/27
 * @Modified By:
 */
public interface BookLogic {

    PageInfo<BookVO> getBookList(int page, int rows, String keyword, HttpServletRequest request)throws Exception;

    BookVO create(BookVO vo)throws Exception;

    BookVO update(BookVO vo);

    BookVO getById(Long id);

    void delete(Long id);
}

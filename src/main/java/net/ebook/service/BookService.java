package net.ebook.service;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Book;
import net.ebook.web.data.BookVO;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2018/1/27
 * @Modified By:
 */
public interface BookService {

    List<Book> getBookList(int page, int rows, String keyword);

    Book getById(long id);

    Book createBook(Book book);

    Book updateBook(Book book);

    void deleteBook(Book book);
}

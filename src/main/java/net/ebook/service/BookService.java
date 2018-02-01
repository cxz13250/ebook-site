package net.ebook.service;

import com.github.pagehelper.PageInfo;
import net.ebook.model.Book;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2018/1/27
 * @Modified By:
 */
public interface BookService {

    PageInfo<Book> getBookList(int page, int rows);
}

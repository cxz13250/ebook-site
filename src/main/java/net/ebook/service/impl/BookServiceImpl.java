package net.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.DeleteStatus;
import net.ebook.dao.BookDao;
import net.ebook.model.Book;
import net.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2018/1/27
 * @Modified By:
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getBookList(int page, int rows, String keyword){
        PageHelper.startPage(page, rows);
        List<Book> books=bookDao.findAll(keyword);
        return books;
    }

    @Override
    public Book getById(long id){
        return bookDao.findById(id);
    }

    @Override
    public Book createBook(Book book){
        book.setCreateTime(new Timestamp(System.currentTimeMillis()));
        book.setDeleted(DeleteStatus.IS_NOT_DELETE);
        bookDao.saveBook(book);
        return book;
    }

    @Override
    public Book updateBook(Book book){
        bookDao.updateBook(book);
        return book;
    }

    @Override
    public void deleteBook(Book book){
        book.setDeleted(DeleteStatus.IS_DELETE);
        bookDao.updateBook(book);
    }

    @Override
    public List<Book> findByCategory(long category){
        PageHelper.startPage(0, 20);
        List<Book> books=bookDao.findByCategory(category);
        return books;
    }
}

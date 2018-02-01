package net.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.ebook.dao.BookDao;
import net.ebook.model.Book;
import net.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

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
    public PageInfo<Book> getBookList(int page, int rows){
        PageHelper.startPage(page, rows);
        List<Book> books=bookDao.selectAll();
        return new PageInfo<>(books);
    }
}

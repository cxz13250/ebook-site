package net.ebook.service.impl;

import net.ebook.dao.BookDao;
import net.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

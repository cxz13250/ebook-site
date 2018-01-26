package net.ebook.web.logic.impl;

import net.ebook.service.BookService;
import net.ebook.web.logic.BookLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:20 2018/1/27
 * @Modified By:
 */
@Service
public class BookLogicImpl implements BookLogic{

    @Autowired
    BookService bookService;
}

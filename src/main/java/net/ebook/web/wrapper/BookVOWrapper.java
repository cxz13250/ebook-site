package net.ebook.web.wrapper;

import net.ebook.model.Book;
import net.ebook.model.BookStatistic;
import net.ebook.service.BookStatisticService;
import net.ebook.service.CategoryService;
import net.ebook.web.data.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:27 2018/2/9
 * @Modified By:
 */
@Service
public class BookVOWrapper extends BaseWrapper<BookVO,Book>{

    @Autowired
    CategoryService categoryService;

    @Autowired
    BookStatisticService statisticService;

    @Override
    public BookVO wrap(Book book){
        BookVO vo=new BookVO();
        vo.setAuthor(book.getAuthor());
        vo.setCategory(book.getCategory());
        vo.setId(book.getId());
        vo.setCreateTime(book.getCreateTime().getTime());
        vo.setPublisher(book.getPublisher());
        vo.setPublishTime(book.getPublishTime().getTime());
        vo.setDescription(book.getDescription());
        vo.setName(book.getName());
        vo.setTranslator(book.getTranslator());
        vo.setDeleted(book.isDeleted());
        vo.setImgUrl(book.getImgUrl());
        vo.setCategoryName(categoryService.getById(book.getCategory()).getName());

        BookStatistic statistic=statisticService.findbyId(book.getId());
        vo.setBorrowed(statistic.getBorrowed());
        vo.setTotal(statistic.getTotal());
        return vo;
    }

    @Override
    public Book unwrap(BookVO vo){
        Book book=new Book();
        book.setAuthor(vo.getAuthor());
        book.setCategory(vo.getCategory());
        book.setName(vo.getName());
        book.setDescription(vo.getDescription());
        book.setPublisher(vo.getPublisher());
        book.setPublishTime(new Timestamp(vo.getPublishTime()));
        book.setTranslator(vo.getTranslator());
        book.setImgUrl(vo.getImgUrl());
        return book;
    }

    public BookStatistic wrap(BookVO vo){
        BookStatistic statistic=new BookStatistic();
        statistic.setBookId(vo.getId());
        statistic.setTotal(vo.getTotal());
        statistic.setBorrowed(vo.getBorrowed());
        statistic.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return statistic;
    }
}

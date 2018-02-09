package net.ebook.web.logic.impl;

import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.OperationStatus;
import net.ebook.model.Book;
import net.ebook.model.BookStatistic;
import net.ebook.model.Category;
import net.ebook.service.BookService;
import net.ebook.service.BookStatisticService;
import net.ebook.service.CategoryService;
import net.ebook.web.data.BookVO;
import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.BookLogic;
import net.ebook.web.logic.UserOperationLogic;
import net.ebook.web.wrapper.BookVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    BookStatisticService statisticService;

    @Autowired
    BookVOWrapper bookVOWrapper;

    @Autowired
    UserOperationLogic operationLogic;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageInfo<BookVO> getBookList(int page, int rows, String keyword, HttpServletRequest request)throws Exception{
        List<Book> books=bookService.getBookList(page, rows, keyword);
        List<BookVO> vos=bookVOWrapper.wrap(books);

        HttpSession session=request.getSession();
        Long userId=(Long)session.getAttribute("id");

        operationLogic.recordUserOperation(request,userId, OperationStatus.BOOK_LIST);
        return new PageInfo<BookVO>(vos);
    }

    @Override
    public BookVO create(BookVO vo)throws Exception{
        if(vo.getCategory()==null){
            throw new IllegalArgumentException("category not present");
        }
        Category category=categoryService.getById(vo.getCategory());
        if(category!=null){
            throw new HttpBadRequestException("categry not exist");
        }
        Book book=bookVOWrapper.unwrap(vo);
        book=bookService.createBook(book);
        vo.setId(book.getId());

        BookStatistic statistic=bookVOWrapper.wrap(vo);
        statisticService.create(statistic);
        return vo;
    }

    @Override
    public BookVO update(BookVO vo){
        if (vo.getId() == null) {
            throw new IllegalArgumentException("book id not present");
        }
        Book book=bookService.getById(vo.getId());
        if(vo.getAuthor()!=null){
            book.setAuthor(vo.getAuthor());
        }
        if(vo.getDescription()!=null){
            book.setDescription(vo.getDescription());
        }
        if(vo.getName()!=null){
            book.setName(vo.getName());
        }
        if(vo.getPublisher()!=null){
            book.setPublisher(vo.getPublisher());
        }
        if(vo.getTranslator()!=null){
            book.setTranslator(vo.getTranslator());
        }
        bookService.updateBook(book);

        BookStatistic statistic=statisticService.findbyId(vo.getId());
        if(statistic==null){
            throw new HttpBadRequestException("book statistic not exist");
        }
        if(vo.getTotal()!=statistic.getTotal()) {
            statistic.setTotal(vo.getTotal());
        }
        if(vo.getBorrowed()!=statistic.getBorrowed()){
            statistic.setBorrowed(vo.getBorrowed());
        }
        statisticService.update(statistic);
        return vo;
    }

    @Override
    public BookVO getById(Long id){
        Book book=bookService.getById(id);
        if(book==null){
            throw new HttpBadRequestException("book not exist");
        }
        return bookVOWrapper.wrap(book);
    }

    @Override
    public void delete(Long id){
        Book book=bookService.getById(id);
        if(book==null){
            throw new HttpBadRequestException("book not exist");
        }else {
            bookService.deleteBook(book);
        }
    }
}

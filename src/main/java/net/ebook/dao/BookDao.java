package net.ebook.dao;

import net.ebook.model.Book;
import net.ebook.util.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:05 2018/1/27
 * @Modified By:
 */
public interface BookDao extends BaseDao<Book> {

    List<Book> findAll(@Param("keyword")String keyword);

    Book findById(@Param("id") long id);

    long saveBook(@Param("book")Book book);

    void updateBook(@Param("book")Book book);

    List<Book> findByCategory(@Param("category") long category);

    List<Book> findByMenu(@Param("menu") String menu);
}

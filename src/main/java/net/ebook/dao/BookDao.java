package net.ebook.dao;

import net.ebook.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:05 2018/1/27
 * @Modified By:
 */
@Mapper
public interface BookDao {

    Book findById(@Param("id") long id);

    long saveBook(@Param("book")Book book);
}

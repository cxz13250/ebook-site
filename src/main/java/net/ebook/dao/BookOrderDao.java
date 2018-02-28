package net.ebook.dao;

import net.ebook.model.BookOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:49 2018/2/18
 * @Modified By:
 */
@Mapper
public interface BookOrderDao {

    List<BookOrder> findByUserId(@Param(value = "userId")long userId);

    List<BookOrder> findAll();

    BookOrder findById(@Param(value = "id")long id);

    long saveOrder(@Param("order")BookOrder order);

    void updateOrder(@Param("order")BookOrder order);
}

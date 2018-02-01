package net.ebook.dao;

import net.ebook.model.BookStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:05 2018/1/27
 * @Modified By:
 */

public interface BookStatisticDao {

    long saveBookStatistic(@Param("statistic") BookStatistic statistic);

    BookStatistic getByBookId(@Param("id") long id);
}

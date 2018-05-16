package net.ebook.service;

import net.ebook.model.BookStatistic;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:14 2018/1/27
 * @Modified By:
 */
public interface BookStatisticService {

    BookStatistic create(BookStatistic statistic);

    BookStatistic findbyBookId(long bookId);

    void update(BookStatistic statistic);

}

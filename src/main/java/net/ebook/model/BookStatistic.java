package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:57 2018/1/26
 * @Modified By:
 */
@Data
public class BookStatistic {

    private long id;

    private long bookId;

    private long total;

    private long borrowed;

    private Timestamp createTime;
}

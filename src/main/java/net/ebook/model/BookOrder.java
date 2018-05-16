package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:09 2018/2/17
 * @Modified By:
 */
@Data
public class BookOrder {

    private long id;

    private long userId;

    private List<OrderItem> items;

    private Timestamp createTime;

    private boolean isDeleted;

    private boolean allReturned;

    // 0审核借阅,1批准借阅,2审核归还,3批准被归还
    private long status;
}

package net.ebook.model;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:36 2018/2/18
 * @Modified By:
 */
@Data
public class OrderItem {

    private long id;

    private long bookId;

    private long orderId;

    private boolean isReturned;
}

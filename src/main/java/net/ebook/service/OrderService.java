package net.ebook.service;

import net.ebook.model.BookOrder;
import net.ebook.model.OrderItem;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:04 2018/2/18
 * @Modified By:
 */
public interface OrderService {

    List<BookOrder> findByUserId(long userId, int page, int rows);

    BookOrder findById(long orderId);

    BookOrder createOrder(BookOrder order);

    void updateOrder(BookOrder order);

    OrderItem createItem(OrderItem item);

    OrderItem findItemById(long id);

    OrderItem updateItem(OrderItem item);
}

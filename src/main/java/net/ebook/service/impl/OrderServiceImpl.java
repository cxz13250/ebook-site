package net.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import net.ebook.common.constants.DeleteStatus;
import net.ebook.common.constants.OrderStatus;
import net.ebook.dao.BookOrderDao;
import net.ebook.dao.OrderItemDao;
import net.ebook.model.BookOrder;
import net.ebook.model.OrderItem;
import net.ebook.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:04 2018/2/18
 * @Modified By:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    BookOrderDao orderDao;

    @Autowired
    OrderItemDao itemDao;

    @Override
    public List<BookOrder> findByUserId(long userId, int page, int rows){
        PageHelper.startPage(page,rows);
        List<BookOrder> orders=orderDao.findByUserId(userId);
        return orders;
    }

    @Override
    public List<BookOrder> findAll(int page, int rows){
        PageHelper.startPage(page,rows);
        List<BookOrder> orders=orderDao.findAll();
        return orders;
    }

    @Override
    public BookOrder findById(long orderId){
        return orderDao.findById(orderId);
    }

    @Override
    public BookOrder createOrder(BookOrder order){
        order.setDeleted(DeleteStatus.IS_NOT_DELETE);
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setAllReturned(false);
        order.setStatus(OrderStatus.CHECK_BORROW);
        orderDao.saveOrder(order);
        return order;
    }

    @Override
    public void updateOrder(BookOrder order){
        orderDao.updateOrder(order);
    }

    @Override
    public OrderItem createItem(OrderItem item){
        item.setReturned(false);
        itemDao.saveItem(item);
        return item;
    }

    @Override
    public OrderItem updateItem(OrderItem item){
        itemDao.updateItem(item);
        return item;
    }

    @Override
    public OrderItem findItemById(long id){
        return itemDao.findById(id);
    }
}

package net.ebook.web.logic.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import net.ebook.common.constants.DeleteStatus;
import net.ebook.common.constants.OrderStatus;
import net.ebook.model.BookOrder;
import net.ebook.model.BookStatistic;
import net.ebook.model.OrderItem;
import net.ebook.service.BookStatisticService;
import net.ebook.service.OrderService;
import net.ebook.web.data.BookOrderVO;
import net.ebook.web.data.OrderItemVO;
import net.ebook.web.exception.HttpBadRequestException;
import net.ebook.web.logic.OrderLogic;
import net.ebook.web.wrapper.OrderItemVOWrapper;
import net.ebook.web.wrapper.OrderVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:47 2018/2/18
 * @Modified By:
 */
@Service
public class OrderLogicImpl implements OrderLogic {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderVOWrapper orderVOWrapper;

    @Autowired
    private BookStatisticService bookStatisticService;

    @Autowired
    private OrderItemVOWrapper itemVOWrapper;

    @Override
    public PageInfo<BookOrderVO> findByUserId(Long userId, int page, int rows){
        List<BookOrder> orders;
        if(userId==null){
            orders=orderService.findAll(page,rows);
//            orders=orders.stream().filter(order -> order.getStatus()==OrderStatus.CHECK_RETURN
//                    || order.getStatus()==OrderStatus.CHECK_BORROW).collect(Collectors.toList());
        }else {
            orders=orderService.findByUserId(userId,page,rows);
        }
        List<BookOrderVO> orderVOS=orders.parallelStream()
                .map(order -> orderVOWrapper.wrap(order)).collect(Collectors.toList());
        return new PageInfo<>(orderVOS);
    }

    @Override
    public BookOrderVO findById(long orderId){
        BookOrder order=orderService.findById(orderId);
        if (order == null) {
            throw new HttpBadRequestException("order not exists");
        }
        BookOrderVO orderVO= orderVOWrapper.wrap(order);
        return orderVO;
    }

    @Override
    public BookOrderVO createOrder(BookOrderVO vo){
        BookOrder order=orderVOWrapper.unwrap(vo);
        order=orderService.createOrder(order);
        long id=order.getId();
        List<OrderItem> items=vo.getItemVOS().stream().map(i-> {
            OrderItem item=itemVOWrapper.unwrap(i);
            item.setOrderId(id);
            return orderService.createItem(item);
        }).collect(Collectors.toList());
        order.setItems(items);
        return orderVOWrapper.wrap(order);
    }

    @Override
    public void updateOrder(BookOrderVO vo){
        BookOrder order=orderService.findById(vo.getId());
        if (order == null) {
            throw new HttpBadRequestException("order not exists");
        }
        if (vo.getUserId() != null) {
            order.setUserId(vo.getUserId());
        }
        order.setStatus(vo.getStatus());
        orderService.updateOrder(order);
    }

    @Override
    public void deleteOrder(long orderId){
        BookOrder order=orderService.findById(orderId);
        if (order == null) {
            throw new HttpBadRequestException("order not exists");
        }
        order.setDeleted(DeleteStatus.IS_DELETE);
        orderService.updateOrder(order);
    }

    @Override
    public OrderItemVO updateItem(OrderItemVO vo){
        if (vo.getId() == null) {
            throw new IllegalArgumentException("item's id not present");
        }
        OrderItem item=orderService.findItemById(vo.getId());
        if (item == null) {
            throw new HttpBadRequestException("item not exists");
        }
        if (vo.getIsReturned() != null) {
            item.setReturned(vo.getIsReturned());
        }
        if (vo.getBookId() != null) {
            item.setBookId(vo.getBookId());
        }
        orderService.updateItem(item);
        return vo;
    }

    @Override
    public BookOrderVO checkOrder(Long orderId, HttpServletRequest request){
        BookOrder order=orderService.findById(orderId);
        if (order == null) {
            throw new HttpBadRequestException("order not exists");
        }
        if (order.getStatus()== OrderStatus.CHECK_BORROW){
            order.setStatus(OrderStatus.BORROWED);
            orderService.updateOrder(order);
            List<OrderItem> items = order.getItems().parallelStream()
                    .map(item -> {
                        item.setOrderId(orderId);
                        BookStatistic statistic=bookStatisticService.findbyBookId(item.getBookId());
                        statistic.setBorrowed(statistic.getBorrowed()+1);
                        bookStatisticService.update(statistic);
                        return item;
                    }).collect(Collectors.toList());
            order.setItems(items);
        }else if (order.getStatus() == OrderStatus.CHECK_RETURN){
            order.setStatus(OrderStatus.RETURNED);
            order.setAllReturned(true);
            orderService.updateOrder(order);
            List<OrderItem> items = order.getItems().parallelStream()
                    .map(item -> {
                        BookStatistic statistic=bookStatisticService.findbyBookId(item.getBookId());
                        statistic.setBorrowed(statistic.getBorrowed()-1);
                        bookStatisticService.update(statistic);
                        item.setReturned(true);
                        return orderService.updateItem(item);
                    }).collect(Collectors.toList());
            order.setItems(items);
        }
        return orderVOWrapper.wrap(order);
    }
}

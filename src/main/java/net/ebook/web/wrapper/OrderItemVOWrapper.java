package net.ebook.web.wrapper;

import net.ebook.model.OrderItem;
import net.ebook.service.BookService;
import net.ebook.web.data.OrderItemVO;
import net.ebook.web.logic.BookLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:56 2018/2/18
 * @Modified By:
 */
@Service
public class OrderItemVOWrapper extends BaseWrapper<OrderItemVO,OrderItem> {

    @Autowired
    BookService bookService;

    @Autowired
    BookLogic bookLogic;

    @Override
    public OrderItemVO wrap(OrderItem item){
        OrderItemVO vo=new OrderItemVO();
        vo.setBookId(item.getBookId());
        vo.setIsReturned(item.isReturned());
        vo.setOrderId(item.getOrderId());
        vo.setBookName(bookService.getById(item.getBookId()).getName());
        vo.setVo(bookLogic.getById(item.getBookId()));
        return vo;
    }

    @Override
    public OrderItem unwrap(OrderItemVO vo){
        OrderItem item=new OrderItem();
        item.setBookId(vo.getBookId());
        return item;
    }
}

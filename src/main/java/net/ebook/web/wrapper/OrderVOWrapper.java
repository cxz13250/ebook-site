package net.ebook.web.wrapper;

import net.ebook.model.BookOrder;
import net.ebook.model.OrderItem;
import net.ebook.web.data.BookOrderVO;
import net.ebook.web.data.OrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:48 2018/2/18
 * @Modified By:
 */
@Service
public class OrderVOWrapper extends BaseWrapper<BookOrderVO, BookOrder>{

    @Autowired
    OrderItemVOWrapper itemVOWrapper;

    @Override
    public BookOrderVO wrap(BookOrder order){
        BookOrderVO vo=new BookOrderVO();
        vo.setId(order.getId());
        vo.setAllReturned(order.isAllReturned());
        vo.setCreateTime(order.getCreateTime().getTime());
        vo.setUserId(order.getUserId());
        List<OrderItemVO> itemVOS=order.getItems().parallelStream().map(item -> itemVOWrapper.wrap(item)).collect(Collectors.toList());
        vo.setItemVOS(itemVOS);
        return vo;
    }

    @Override
    public BookOrder unwrap(BookOrderVO vo){
        BookOrder order=new BookOrder();
        order.setUserId(vo.getUserId());
        List<OrderItem> items=vo.getItemVOS().parallelStream().map(vo1 -> itemVOWrapper.unwrap(vo1)).collect(Collectors.toList());
        order.setItems(items);
        return order;
    }

}

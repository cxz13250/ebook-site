package net.ebook.web.logic;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import net.ebook.model.BookOrder;
import net.ebook.model.OrderItem;
import net.ebook.web.data.BookOrderVO;
import net.ebook.web.data.OrderItemVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:47 2018/2/18
 * @Modified By:
 */
public interface OrderLogic {

    PageInfo<BookOrderVO> findByUserId(Long userId, int page, int rows);

    BookOrderVO findById(long orderId);

    BookOrderVO createOrder(BookOrderVO order);

    void updateOrder(BookOrderVO order);

    void deleteOrder(long orderId);

    OrderItemVO updateItem(OrderItemVO item);

    BookOrderVO checkOrder(Long orderId, HttpServletRequest request);
}

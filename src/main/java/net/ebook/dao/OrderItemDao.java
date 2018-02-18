package net.ebook.dao;

import net.ebook.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:50 2018/2/18
 * @Modified By:
 */
@Mapper
public interface OrderItemDao {

    List<OrderItem> findByOrderId(@Param(value = "orderId")long orderId);

    OrderItem findById(@Param(value = "id")long id);

    long saveItem(@Param(value = "item")OrderItem item);

    void updateItem(@Param(value = "item")OrderItem item);
}

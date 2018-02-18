package net.ebook.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:52 2018/2/18
 * @Modified By:
 */
@Data
public class OrderItemVO {

    private Long id;

    private Long orderId;

    private Long bookId;

    private String bookName;

    private Boolean isReturned;
}

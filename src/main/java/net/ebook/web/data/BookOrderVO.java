package net.ebook.web.data;

import lombok.Data;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午11:52 2018/2/18
 * @Modified By:
 */
@Data
public class BookOrderVO {

    private Long id;

    private Long userId;

    private Long createTime;

    private List<OrderItemVO> itemVOS;

    private Boolean allReturned;
}

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

    private String userName;

    private Long createTime;

    private List<OrderItemVO> itemVOS;

    //-1 逾期不还, 0 未归还, 1 已归还
    private Integer status;
}

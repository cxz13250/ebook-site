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

    //-1 逾期不还,0审核借阅,1批准借阅,2审核归还,3批准被归还
    private Long status;
}

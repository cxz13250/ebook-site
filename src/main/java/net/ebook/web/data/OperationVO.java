package net.ebook.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:58 2018/2/22
 * @Modified By:
 */
@Data
public class OperationVO {

    private String name;

    private String Operation;

    private Long createTime;

    private String ip;
}

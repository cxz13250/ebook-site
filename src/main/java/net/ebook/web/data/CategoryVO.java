package net.ebook.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:16 2018/1/27
 * @Modified By:
 */
@Data
public class CategoryVO {

    private Long id;

    private String name;

    private Long createTime;

    private Boolean deleted;

    private String menu;
}

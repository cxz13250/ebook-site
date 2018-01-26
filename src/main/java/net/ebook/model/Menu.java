package net.ebook.model;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:35 2018/1/23
 * @Modified By:
 */
@Data
public class Menu {

    private Long id;

    private Long roleId;

    private String menu;

    private String detail;

    private String url;
}

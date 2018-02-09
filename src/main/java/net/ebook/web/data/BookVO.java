package net.ebook.web.data;

import lombok.Data;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:16 2018/1/27
 * @Modified By:
 */
@Data
public class BookVO {

    private Long id;

    private String name;

    private Long category;

    private String categoryName;

    private String author;

    private String translator;

    private String publisher;

    private String description;

    private Long publishTime;

    private Long createTime;

    private Boolean deleted;

    private Long total;

    private Long borrowed;
}

package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:30 2018/1/26
 * @Modified By:
 */
@Data
public class Book {

    private long id;

    private String name;

    private long category;

    private String author;

    private String translator;

    private String publisher;

    private String description;

    private Timestamp publishTime;

    private Timestamp createTime;

    private boolean deleted;
}

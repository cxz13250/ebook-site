package net.ebook.model;

import lombok.Data;

import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:33 2018/1/26
 * @Modified By:
 */
@Data
@Table(name = "category")
public class Category {

    private long id;

    private String name;

    private Timestamp createTime;

    private boolean deleted;

    private String menu;

}

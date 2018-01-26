package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/9/15.
 */
@Data
public class Role {

    private long id;

    private String name;

    private Timestamp createTime;
}

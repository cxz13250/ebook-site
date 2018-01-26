package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by ROGK on 2017/11/1.
 * 用户操作
 */
@Data
public class UserOperation {


    private long id;

    private long userId;

    private String ip;

    private String operation;

    private Timestamp createTime;
}

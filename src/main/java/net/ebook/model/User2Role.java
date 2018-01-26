package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:58 2018/1/23
 * @Modified By:
 */
@Data
public class User2Role {

    private long id;

    private long userId;

    private long roleId;

    private Timestamp createTime;
}

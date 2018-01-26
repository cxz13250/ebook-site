package net.ebook.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:19 2018/1/21
 * @Modified By:
 */
@Data
public class User {

    private long id;

    private String name;

    private String email;

    private String mobile;

    private String password;

    private Timestamp createTime;

    private Boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

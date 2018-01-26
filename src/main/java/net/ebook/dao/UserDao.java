package net.ebook.dao;

import net.ebook.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:19 2018/1/21
 * @Modified By:
 */
@Mapper
public interface UserDao {

    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "email", column = "email"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "password", column = "password"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "deleted", column = "is_delete"),
    })
    User findById(long id);

    User findByEmail(@Param("email") String email);

    User findByMobile(@Param("mobile") String mobile);

    long createUser(@Param("user") User user);
}

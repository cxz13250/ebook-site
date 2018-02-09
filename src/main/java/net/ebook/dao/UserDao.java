package net.ebook.dao;

import net.ebook.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:19 2018/1/21
 * @Modified By:
 */

public interface UserDao {

    User findById(long id);

    User findByEmail(@Param("email") String email);

    User findByMobile(@Param("mobile") String mobile);

    long createUser(@Param("user") User user);
}

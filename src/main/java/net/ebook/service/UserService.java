package net.ebook.service;

import net.ebook.model.Role;
import net.ebook.model.User;
import net.ebook.model.User2Role;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:19 2018/1/21
 * @Modified By:
 */
public interface UserService {

    User createUser(User user);

    User getUserById(long id);

    User getUserByEmail(String email);

    User getUserByMobile(String mobile);

    List<User2Role> getRoles(long userId);

    Role findRole(long id);

    List<User> getUsers(int page, int rows, String keyword);

    void updateUser(User user);
}

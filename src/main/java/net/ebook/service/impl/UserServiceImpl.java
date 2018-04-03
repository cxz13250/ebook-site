package net.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import net.ebook.common.constants.DeleteStatus;
import net.ebook.dao.RoleDao;
import net.ebook.dao.User2RoleDao;
import net.ebook.dao.UserDao;
import net.ebook.model.Role;
import net.ebook.model.User;
import net.ebook.model.User2Role;
import net.ebook.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:19 2018/1/21
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    private User2RoleDao user2RoleDao;

    @Override
    public User createUser(User user){
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setDeleted(DeleteStatus.IS_NOT_DELETE);
        userDao.createUser(user);
        return user;
    }

    @Override
    public User getUserById(long id){
        return userDao.findById(id);
    }

    @Override
    public User getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Override
    public User getUserByMobile(String mobile){
        return userDao.findByMobile(mobile);
    }

    @Override
    public List<User2Role> getRoles(long userId){
        return user2RoleDao.findByUserId(userId);
    }

    @Override
    public Role findRole(long id){
        return roleDao.findById(id);
    }

    @Override
    public List<User> getUsers(int page, int rows, String keyword){
        PageHelper.startPage(page,rows);
        if(keyword!=null && StringUtils.trim(keyword)!=""){
            return userDao.findByKeyword("%"+keyword+"%");
        }else {
            return userDao.findAll();
        }
    }

    @Override
    public void updateUser(User user){
        userDao.updateUser(user);
    }
}

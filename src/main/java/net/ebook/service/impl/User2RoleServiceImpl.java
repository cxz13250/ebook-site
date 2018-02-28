package net.ebook.service.impl;

import net.ebook.dao.User2RoleDao;
import net.ebook.model.User2Role;
import net.ebook.service.User2RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午5:36 2018/1/25
 * @Modified By:
 */
@Service
public class User2RoleServiceImpl implements User2RoleService {

    @Autowired
    User2RoleDao user2RoleDao;

    @Override
    public void createRole(User2Role user2Role){
        user2Role.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user2RoleDao.saveRole(user2Role);
    }

    @Override
    public void updateRole(User2Role user2Role){
        user2RoleDao.updateRole(user2Role);
    }

}

package net.ebook.dao;

import net.ebook.model.User2Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ROGK on 2017/9/15.
 */

public interface User2RoleDao {

    List<User2Role> findByUserId(long userId);

    void saveRole(@Param(value = "role")User2Role role);
}

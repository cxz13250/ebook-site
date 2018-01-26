package net.ebook.dao;

import net.ebook.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ROGK on 2017/9/15.
 */
@Mapper
public interface RoleDao{

    Role findById(@Param(value = "id") long id);
}

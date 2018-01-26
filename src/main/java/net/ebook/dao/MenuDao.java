package net.ebook.dao;

import net.ebook.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午12:35 2018/1/23
 * @Modified By:
 */
@Mapper
public interface MenuDao {

    List<Menu> findByRoleId(@Param(value = "roleId") long roleId);
}

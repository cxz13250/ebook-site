package net.ebook.web.logic;

import net.ebook.web.data.MenuVO;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:03 2018/1/23
 * @Modified By:
 */
public interface MenuLogic {

    List<MenuVO> getMenusForLogin(Long roleId);
}

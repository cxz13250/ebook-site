package net.ebook.web.logic.impl;

import net.ebook.service.MenuService;
import net.ebook.web.data.MenuVO;
import net.ebook.web.logic.MenuLogic;
import net.ebook.web.wrapper.MenuVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:04 2018/1/23
 * @Modified By:
 */
@Service
public class MenuLogicImpl implements MenuLogic {

    @Autowired
    MenuService menuService;

    @Autowired
    MenuVOWrapper menuVOWrapper;

    @Override
    public List<MenuVO> getMenusForLogin(Long roleId){
        List<MenuVO> menuVOS=new ArrayList<>();
        List<MenuVO> menuVOList=menuVOWrapper.wrap(menuService.getMenusByRoleId(roleId));
        for (MenuVO m: menuVOList) {
            if (!menuVOS.stream().anyMatch(menuVO -> menuVO.getUrl().equals(m.getUrl()))){
                menuVOS.add(m);
            }
        }
        return menuVOS;
    }
}

package net.ebook.service.impl;

import net.ebook.dao.MenuDao;
import net.ebook.model.Menu;
import net.ebook.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:15 2017/12/4
 * @Modified By:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> getMenusByRoleId(long roleId){
        return menuDao.findByRoleId(roleId);
    }
}

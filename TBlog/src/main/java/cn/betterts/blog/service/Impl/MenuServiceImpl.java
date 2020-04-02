package cn.betterts.blog.service.Impl;

import cn.betterts.blog.dao.MenuDao;
import cn.betterts.blog.domain.Menu;
import cn.betterts.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired(required = false)
    private MenuDao menuDao;

    @Override
    public List<Menu> listMenu() {
        return menuDao.listMenu();
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuDao.getMenuById(menuId);
    }

    @Override
    public void deleteById(Integer menuId) {
        menuDao.deleteById(menuId);
    }

    @Override
    public Menu insert(Menu menu) {
        menuDao.insert(menu);
        return menu;
    }

    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }
}

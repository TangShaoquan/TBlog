package cn.betterts.blog.service;

import cn.betterts.blog.domain.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> listMenu();
    public Menu getMenuById(Integer menuId);
    public void deleteById(Integer menuId);
    public Menu insert(Menu menu);
    public void update(Menu menu);
}

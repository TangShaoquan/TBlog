package cn.betterts.blog.service;

import cn.betterts.blog.domain.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryService {
    public List<Category> listCategory();
    public List<Category> listCategoryWithCount();
    public List<Category> findChildCategory(@Param("categoryPid") Integer categoryPid);
    public Category getCategoryById(Integer categoryId);
    public Category getCategoryByName(String categoryName);
    public Integer countCategory();
    public void deleteCategory(Integer categoryId);
    public Category insert(Category category);
    public void update(Category category);
}

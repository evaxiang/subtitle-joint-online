package com;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew on 2016/1/1.
 */
public class Category {

    private Long id;

    private String name;

    private Category parentCategory;

    private Set childCategories = new HashSet<Object>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(Set childCategories) {
        this.childCategories = childCategories;
    }
}

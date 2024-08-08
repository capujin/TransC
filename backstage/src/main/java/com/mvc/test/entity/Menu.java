package com.mvc.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

public class Menu {
    private Integer menuId;
    private Integer parentId;
    private String title;
    private String url;
    private String icon;
    private Integer order;
    private Boolean isActive;

    private List<Menu> children;

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", parentId=" + parentId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", order=" + order +
                ", isActive=" + isActive +
                '}';
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

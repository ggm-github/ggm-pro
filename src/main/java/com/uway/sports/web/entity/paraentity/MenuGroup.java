package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.SysMenu;

import java.util.List;

public class MenuGroup {
    private String groupname;
    private List<SysMenu> menus;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<SysMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenu> menus) {
        this.menus = menus;
    }
}

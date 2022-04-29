package com.uway.sports.web.entity.dbentity;

public class SysMenu {
    /** 菜单id */
    private Integer mId;
    /** 菜单名称 */
    private String mName;
    private Integer mGroupid;
    /** 菜单组名 */
    private String mGroup;
    /** 菜单路径 */
    private String mPath;
    /** 菜单顺序 */
    private Integer mSort;
    /** 菜单状态 */
    private Integer mStatus;
    /** 父级id */
    private Integer mParentId;

    public Integer getMId() {
        return mId;
    }

    public void setMId(Integer mId) {
        this.mId = mId;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public Integer getMGroupid() {
        return mGroupid;
    }

    public void setMGroupid(Integer mGroupid) {
        this.mGroupid = mGroupid;
    }

    public String getMGroup() {
        return mGroup;
    }

    public void setMGroup(String mGroup) {
        this.mGroup = mGroup;
    }

    public String getMPath() {
        return mPath;
    }

    public void setMPath(String mPath) {
        this.mPath = mPath;
    }

    public Integer getMSort() {
        return mSort;
    }

    public void setMSort(Integer mSort) {
        this.mSort = mSort;
    }

    public Integer getMStatus() {
        return mStatus;
    }

    public void setMStatus(Integer mStatus) {
        this.mStatus = mStatus;
    }

    public Integer getMParentId() {
        return mParentId;
    }

    public void setMParentId(Integer mParentId) {
        this.mParentId = mParentId;
    }
}

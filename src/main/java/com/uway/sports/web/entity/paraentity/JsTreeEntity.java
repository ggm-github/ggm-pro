package com.uway.sports.web.entity.paraentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title 权限树实体类
 * @Author liujingce
 * @Date 2019/1/28 16:46
 * @Discription 用于权限树的展示
 */
public class JsTreeEntity implements Serializable {
    // 节点id
    private String id;
    // 节点名称
    private String text;
    private Integer parentId;
    private Map<String,Object> state;
    // 下级节点集合
    private List<JsTreeEntity> children;

    public JsTreeEntity(String id, String text) {
        this.id = id;
        this.text = text;
        this.children = new ArrayList<>();
        this.state = new HashMap<>();
    }

    public JsTreeEntity() {
        super();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<JsTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<JsTreeEntity> children) {
        this.children = children;
    }

    public Map<String, Object> getState() {
        return state;
    }

    public void setState(Map<String, Object> state) {
        this.state = state;
    }
}

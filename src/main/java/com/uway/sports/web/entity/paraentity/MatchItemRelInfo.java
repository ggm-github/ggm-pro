package com.uway.sports.web.entity.paraentity;

public class MatchItemRelInfo {
    private Integer matchId;
    private Integer itemId;
    private String matchName;
    private String itemName;
    private Integer teamLimit;  //参赛队伍限制,-1:不限制 (单人项目也理解为队伍),
    private Integer templateId; //'关联模板id',
    private Integer isCharge;   // '是否收费 0:免费 1:收费',
    private Double unitPrice;   //'收费单价',
    private Integer chargeType;  // '缴费方式 0:全选 1:统一缴费 2:自己缴费',
    private String chargeNotice; // '缴费须知',
    private String systemProperty; // '报名填写属性  eg：1,2,3|4,1,5 团队属性在前，个人属性在后',
    private String customProperty; // '自定义属性json数组',
    private String signupNotice; // '报名须知',
    private Integer signupNoticeStatus; // '是否显示报名须知 0:不显示 1:显示',
    private Integer isNeedAudit; // '报名信息是否需要审核 0:不审核 1:审核',
    // 父级项目名称
    private String parentItemName;
    // 报名的最大、最小值
    private Integer minUser;
    private Integer maxUser;
    private Integer teamOrPerson;
    private Integer parentId;

    public Integer getTeamLimit() {
        return teamLimit;
    }

    public void setTeamLimit(Integer teamLimit) {
        this.teamLimit = teamLimit;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(Integer isCharge) {
        this.isCharge = isCharge;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeNotice() {
        return chargeNotice;
    }

    public void setChargeNotice(String chargeNotice) {
        this.chargeNotice = chargeNotice;
    }

    public String getSystemProperty() {
        return systemProperty;
    }

    public void setSystemProperty(String systemProperty) {
        this.systemProperty = systemProperty;
    }

    public String getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(String customProperty) {
        this.customProperty = customProperty;
    }

    public String getSignupNotice() {
        return signupNotice;
    }

    public void setSignupNotice(String signupNotice) {
        this.signupNotice = signupNotice;
    }

    public Integer getSignupNoticeStatus() {
        return signupNoticeStatus;
    }

    public void setSignupNoticeStatus(Integer signupNoticeStatus) {
        this.signupNoticeStatus = signupNoticeStatus;
    }

    public Integer getIsNeedAudit() {
        return isNeedAudit;
    }

    public void setIsNeedAudit(Integer isNeedAudit) {
        this.isNeedAudit = isNeedAudit;
    }

    public String getParentItemName() {
        return parentItemName;
    }

    public void setParentItemName(String parentItemName) {
        this.parentItemName = parentItemName;
    }

    public Integer getMinUser() {
        return minUser;
    }

    public void setMinUser(Integer minUser) {
        this.minUser = minUser;
    }

    public Integer getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Integer maxUser) {
        this.maxUser = maxUser;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getTeamOrPerson() {
        return teamOrPerson;
    }

    public void setTeamOrPerson(Integer teamOrPerson) {
        this.teamOrPerson = teamOrPerson;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}

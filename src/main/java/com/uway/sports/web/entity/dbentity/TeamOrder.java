package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class TeamOrder implements Serializable {

    /** 订单id */
    private Integer	 id;
    /** 订单编号 */
    private String	orderNo;
    /** 订单类型 1报名费 */
    private Integer	orderType;
    /** 用户id */
    private Integer	userId;

    private String phone;

    /** 赛事id */
    private Integer matchId;

    private String matchTitle;

    private Integer teamId;
    /** 交费方式 1统一交费 2自己交费 */
    private Integer chargeType;
    /** 报名人数 */
    private Integer	numbers;
    /** 单价 */
    private Double	unitPrice;
    /** 总价 */
    private Double	totalPrice;
    /** 支付方式 1微信 2支付宝 */
    private Integer	payType;
    /** 支付订单号（支付平台回传回来的） */
    private String	payNo;
    /** 支付时间 */
    private String	payTime;
    /** 支付回调json */
    private String	payJson;
    /** 支付金额 */
    private Double	payPrice;
    /** 交易账号 */
    private String	trodeAccount;
    /** 异常备注 */
    private String	operRemark;
    /** 订单状态 0待付款 1已付款 2订单失效 */
    private Integer	orderStatus;
    /** 入库时间 */
    private String 	inserttime;
    /** 发票抬头 */
    private String	invoicePayable;
    /** 纳税人识别号 */
    private String	taxpayerIdentity;
	/** 赛事项目id */
    private Integer matchItemId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getChargeType() {
		return chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getPayJson() {
		return payJson;
	}

	public void setPayJson(String payJson) {
		this.payJson = payJson;
	}

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public String getTrodeAccount() {
		return trodeAccount;
	}

	public void setTrodeAccount(String trodeAccount) {
		this.trodeAccount = trodeAccount;
	}

	public String getOperRemark() {
		return operRemark;
	}

	public void setOperRemark(String operRemark) {
		this.operRemark = operRemark;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getInvoicePayable() {
		return invoicePayable;
	}

	public void setInvoicePayable(String invoicePayable) {
		this.invoicePayable = invoicePayable;
	}

	public String getTaxpayerIdentity() {
		return taxpayerIdentity;
	}

	public void setTaxpayerIdentity(String taxpayerIdentity) {
		this.taxpayerIdentity = taxpayerIdentity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMatchTitle() {
		return matchTitle;
	}

	public void setMatchTitle(String matchTitle) {
		this.matchTitle = matchTitle;
	}

	public Integer getMatchItemId() {
		return matchItemId;
	}

	public void setMatchItemId(Integer matchItemId) {
		this.matchItemId = matchItemId;
	}
}

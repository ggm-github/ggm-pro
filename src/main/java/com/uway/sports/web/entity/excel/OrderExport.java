package com.uway.sports.web.entity.excel;

import com.uway.sports.common.utils.ExcelAnnotation;

public class OrderExport {

    /** 订单id */
    @ExcelAnnotation(id=1,name={"订单id"},width = 5000)
    private Integer	 id;
    /** 订单编号 */
    @ExcelAnnotation(id=2,name={"订单编号"},width = 5000)
    private String	orderNo;
    /** 订单类型 1报名费 */
    @ExcelAnnotation(id=3,name={"订单类型"},width = 5000)
    private String orderType;
    /** 用户id */
    @ExcelAnnotation(id=4,name={"用户id"},width = 5000)
    private Integer 	userId;
    @ExcelAnnotation(id=5,name={"用户手机"},width = 5000)
    private String phone;

    @ExcelAnnotation(id=6,name={"赛事名称"},width = 5000)
    private String matchTitle;

    /** 交费方式 1统一交费 2自己交费 */
    @ExcelAnnotation(id=7,name={"交费方式"},width = 5000)
    private String chargeType;
    /** 报名人数 */
    @ExcelAnnotation(id=8,name={"报名人数"},width = 5000)
    private Integer	numbers;
    /** 单价 */
    @ExcelAnnotation(id=9,name={"单价"},width = 5000)
    private Double	unitPrice;
    /** 总价 */
    @ExcelAnnotation(id=10,name={"总价"},width = 5000)
    private Double	totalPrice;
    /** 支付方式 1微信 2支付宝 */
    @ExcelAnnotation(id=11,name={"支付方式"},width = 5000)
    private String	payType;
    /** 支付订单号（支付平台回传回来的） */
    @ExcelAnnotation(id=12,name={"支付订单号"},width = 5000)
    private String	payNo;
    /** 支付时间 */
    @ExcelAnnotation(id=13,name={"支付时间"},width = 5000)
    private String	payTime;
    /** 支付金额 */
    @ExcelAnnotation(id=14,name={"支付金额"},width = 5000)
    private Double	payPrice;
    /** 交易账号 */
    @ExcelAnnotation(id=15,name={"交易账号"},width = 5000)
    private String	trodeAccount;
    /** 异常备注 */
    @ExcelAnnotation(id=16,name={"异常备注"},width = 5000)
    private String	operRemark;
    /** 订单状态 0待付款 1已付款 2订单失效 */
    @ExcelAnnotation(id=17,name={"订单状态"},width = 5000)
    private String	orderStatus;
    /** 入库时间 */
    @ExcelAnnotation(id=18,name={"入库时间"},width = 5000)
    private String 	inserttime;
    /** 发票抬头 */
    @ExcelAnnotation(id=19,name={"发票抬头"},width = 5000)
    private String	invoicePayable;
    @ExcelAnnotation(id=20,name={"纳税人识别号"},width = 5000)
    private String taxpayerIdentity;

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
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
}

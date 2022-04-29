package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class Invoice implements Serializable {

    /** 发票id */
    private Integer	id;
    /** 订单id */
    private Integer	orderid;
    /** 发票类型 1公司 2个人 */
    private Integer	invoiceType;
    /** 发票抬头 */
    private String	invoicePayable;
    /** 纳税人识别号 */
    private String	taxpayerIdentity;
    /** 备注说明 */
    private String	bake;
    /** 地址和电话 */
    private String	address;
    /** 开户行和账号 */
    private String	bank;
    /** 金额 */
    private Double	price;
    /** 电子邮箱 */
    private String	mail;
    /** 收件地址 */
    private String	mailaddress;
    /** 联系电话 */
    private String	mailphone;
    /** 收件人 */
    private String	receiver;
    /** 开票状态 0未开票 1已开票 */
    private Integer	status;
    /** 入库时间 */
    private String	inserttime;

  	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrderid() {
		return orderid;
	}
	
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	public Integer getInvoiceType() {
		return invoiceType;
	}
	
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
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
	
	public String getBake() {
		return bake;
	}
	
	public void setBake(String bake) {
		this.bake = bake;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getBank() {
		return bank;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMailaddress() {
		return mailaddress;
	}
	
	public void setMailaddress(String mailaddress) {
		this.mailaddress = mailaddress;
	}
	
	public String getMailphone() {
		return mailphone;
	}
	
	public void setMailphone(String mailphone) {
		this.mailphone = mailphone;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getInserttime() {
		return inserttime;
	}
	
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
	
	
}

package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

public class TeamOrderRequest   extends TableEntity {
    /** 订单id */
    private Integer id;
    /** 订单编号 */
    private String orderNo;
    /** 用户id */
    private Integer userId;
    /** 赛事id */
    private Integer matchId;
    private Integer teamId;
    /** 订单状态 0待付款 1已付款 2订单失效 */
    private Integer orderStatus;

    private String startDate; //下单开始时间
    private String endDate;//下单结束时间
    private String paystartDate;//支付开始时间
    private String payendDate;//支付结束时间


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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPaystartDate() {
        return paystartDate;
    }

    public void setPaystartDate(String paystartDate) {
        this.paystartDate = paystartDate;
    }

    public String getPayendDate() {
        return payendDate;
    }

    public void setPayendDate(String payendDate) {
        this.payendDate = payendDate;
    }
}

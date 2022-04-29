package com.uway.sports.web.service;


import com.uway.sports.web.entity.dbentity.Invoice;
import com.uway.sports.web.entity.dbentity.TeamOrder;
import com.uway.sports.web.entity.excel.OrderExport;
import com.uway.sports.web.entity.paraentity.TeamOrderParas;
import com.uway.sports.web.entity.paraentity.TeamOrderRequest;

import java.util.List;

public interface ITeamOrderService {

    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    List<TeamOrder> getTeamOrderList(TeamOrderRequest teamOrderRequest);

    /**
     * 更新订单信息
     * @param teamOrder
     * @return
     */
    int updateTeamOrder(TeamOrder teamOrder);

    /**
     * 获取订单列表总数
     * @param teamOrderRequest
     * @return
     */
    int getTeamOrderListcount(TeamOrderRequest teamOrderRequest);

    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    List<OrderExport> getExportOrderList(TeamOrderRequest teamOrderRequest);

    Invoice getInvoice(Integer orderId);
}

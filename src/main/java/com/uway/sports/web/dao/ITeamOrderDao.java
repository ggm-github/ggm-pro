package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.Invoice;
import com.uway.sports.web.entity.dbentity.TeamOrder;
import com.uway.sports.web.entity.excel.OrderExport;
import com.uway.sports.web.entity.paraentity.TeamOrderRequest;

import java.util.List;

@MyBatisDao
public interface ITeamOrderDao {
    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    List<TeamOrder> getTeamOrderList(TeamOrderRequest teamOrderRequest);

    /**
     * 获取订单列表总数
     * @param teamOrderRequest
     * @return
     */
    int getTeamOrderListcount(TeamOrderRequest teamOrderRequest);

    /**
     * 更新订单信息
     * @param teamOrder
     * @return
     */
    int updateTeamOrder(TeamOrder teamOrder);


    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    List<OrderExport> getExportOrderList(TeamOrderRequest teamOrderRequest);

    /**
     * 根据条件查询订单
     * @param teamOrderRequest
     * @return
     */
    List<TeamOrder> getTeamOrders(TeamOrderRequest teamOrderRequest);

    /**
     * 获取发票信息
     * @param orderId
     * @return
     */
    Invoice getInvoice(Integer orderId);
}

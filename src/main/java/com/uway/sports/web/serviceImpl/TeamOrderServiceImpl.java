package com.uway.sports.web.serviceImpl;

import com.uway.sports.web.dao.ITeamOrderDao;
import com.uway.sports.web.entity.dbentity.Invoice;
import com.uway.sports.web.entity.dbentity.TeamOrder;
import com.uway.sports.web.entity.excel.OrderExport;
import com.uway.sports.web.entity.paraentity.TeamOrderRequest;
import com.uway.sports.web.service.ITeamOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeamOrderServiceImpl implements ITeamOrderService {


    @Autowired
    private ITeamOrderDao iTeamOrderDao;

    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    @Override
    public List<TeamOrder> getTeamOrderList(TeamOrderRequest teamOrderRequest){
        return iTeamOrderDao.getTeamOrderList(teamOrderRequest);
    }

    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    @Override
    public int getTeamOrderListcount(TeamOrderRequest teamOrderRequest){
        return iTeamOrderDao.getTeamOrderListcount(teamOrderRequest);
    }

    /**
     * 更新订单信息
     * @param teamOrder
     * @return
     */
    @Override
    public int updateTeamOrder(TeamOrder teamOrder){
        return iTeamOrderDao.updateTeamOrder(teamOrder);
    }

    /**
     * 获取订单列表
     * @param teamOrderRequest
     * @return
     */
    @Override
    public List<OrderExport> getExportOrderList(TeamOrderRequest teamOrderRequest){
        return iTeamOrderDao.getExportOrderList(teamOrderRequest);
    }

    @Override
    public Invoice getInvoice(Integer orderId) {
        return iTeamOrderDao.getInvoice(orderId);
    }
}

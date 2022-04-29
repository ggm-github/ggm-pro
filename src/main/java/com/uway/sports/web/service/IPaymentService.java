package com.uway.sports.web.service;

import com.uway.sports.common.utils.RestResponse;

import java.util.Map;

/**
 * Auth: liujingce
 * Date: 2018/6/27 18:04
 * Title:
 * Describer:
 **/
public interface IPaymentService {
    /**
     * Auth: liujingce
     * Date: 2018/6/27 18:07
     * Title: 查询商品信息，用于界面的展示
     * @param userId,productId,productDesId
     * @return Map<String,Object>
     * Describer: 查询商品信息、商品规格、用户积分信息等
    **/
    Map<String,Object> getProductInfoForShow(String userId,String productId,String productDesId);
    /**
     * Auth: liujingce
     * Date: 2018/6/28 11:18
     * Title: 下单支付前的校验
     * @param userId，productDesId
     * @return RestResponse
     * Describer: 校验积分、库存是否充足
    **/
    RestResponse orderPaymentCheck(Integer userId,Integer productDesId);

    /**
     * Auth: liujingce
     * Date: 2018/6/28 16:32
     * Title: 积分兑换（与支付有些不一样，先拆开处理）
     * @param userId,productDesId
     * @return RestResponse
     * Describer: 进行下单频次、积分值、库存的校验，校验通过后，添加订单信息，并添加一条消费积分记录
    **/
    RestResponse addOrderByExchange(String userId,String productDesId,String orderType);
}

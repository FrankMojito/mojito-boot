package com.mojito.mojitoboot.biz.service.impl;

import com.mojito.mojitoboot.biz.service.OrderService;
import com.mojito.mojitoboot.common.daomodel.UserDO;
import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.core.bizmodel.OrderBO;
import com.mojito.mojitoboot.core.service.GoodsCoreService;
import com.mojito.mojitoboot.core.service.UserCoreService;
import com.mojito.mojitoboot.web.response.error.BusinessException;
import com.mojito.mojitoboot.web.response.error.EmBusinessError;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @Auther: Mojito
 * @Date: 2019/4/5 15:16
 * @Description:
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    GoodsCoreService goodsCoreService;

    @Autowired
    UserCoreService userCoreService;

    @Override
    public OrderBO createOrder(Integer userId, Integer goodsId, Integer amount) throws BusinessException {
        // 校验下单状态，用户状态，商品状态，行为状态。
        GoodsBO goodsBO = goodsCoreService.selectGoodById(goodsId);
        if(goodsBO == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }

        UserDO userBO = userCoreService.getUserById(userId);
        if(userBO == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }

        if(amount<=0 ||amount >99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量信息不正确");
        }

        int affectedRow = goodsCoreService.updateStock(goodsId, amount);
        if(affectedRow<=0){
            throw new BusinessException(EmBusinessError.STOCK_NOT_BNOUGH);
        }
        OrderBO orderBO = new OrderBO();
        orderBO.setUserId(userId);
        orderBO.setGoodsId(goodsId);
        orderBO.setAmount(amount);
        orderBO.setPrice(goodsBO.getPrice());
        orderBO.setTotalPrice(new BigDecimal(1));

        //落单减库存

        //订单写入数据库
        return null;
    }
}

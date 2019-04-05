package com.mojito.mojitoboot.biz.service;

import com.mojito.mojitoboot.core.bizmodel.OrderBO;
import com.mojito.mojitoboot.web.response.error.BusinessException;

/**
 * @Auther: Mojito
 * @Date: 2019/4/5 15:14
 * @Description:
 */
public interface OrderService {
    OrderBO createOrder(Integer userId, Integer goodsId, Integer amount) throws BusinessException;
}

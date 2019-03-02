package com.mojito.mojitoboot.biz.service;

import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.web.response.error.BusinessException;

import java.util.List;

/**
 * @Auther: Mojito
 * @Date: 2019/2/23 16:10
 * @Description:
 */
public interface GoodsService {

    //商品创建
    GoodsBO createGoods(GoodsBO goodsBO) throws BusinessException;

    //商品浏览
    List<GoodsBO> getGoodsList();

    //商品详情
    GoodsBO getGoods(Integer id);
}

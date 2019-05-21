package com.mojito.mojitoboot.biz.service;

import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.web.response.error.BusinessException;

import java.util.List;

/**
 * @Auther: Mojito
 * @Date: 2019/2/23 16:10
 * @Description:
 *  商品处理业务层，模仿蚂蚁金服sofa框架，此层在分布式模块中应为biz，上层应该还有对外的facade层和integ层
 */
public interface GoodsService {

    //商品创建
    GoodsBO createGoods(GoodsBO goodsBO) throws BusinessException;

    //商品浏览
    List<GoodsBO> getGoodsList();

    //商品详情
    GoodsBO getGood(Integer id);

}

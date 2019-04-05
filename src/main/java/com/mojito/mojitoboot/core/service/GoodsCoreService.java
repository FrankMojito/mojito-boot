package com.mojito.mojitoboot.core.service;

import com.mojito.mojitoboot.core.bizmodel.GoodsBO;

import java.util.List;

/**
 * @Auther: Mojito
 * @Date: 2019/2/23 16:53
 * @Description:
 *  此层应为核心业务层，在sofa中应为core层，但是这里暂时只做与数据库的交互以吉模型的转换。
 */
public interface GoodsCoreService {

    GoodsBO insertGoods(GoodsBO goodsBO);

    List<GoodsBO> selectGoodsList();

    GoodsBO selectGoodById(Integer id);


    int updateStock(Integer goodsId, Integer amount);
}

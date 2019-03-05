package com.mojito.mojitoboot.core.service.impl;

import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.common.mapper.GoodsDOMapper;
import com.mojito.mojitoboot.common.mapper.GoodsStockDOMapper;
import com.mojito.mojitoboot.common.utils.other.ConvertUtil;
import com.mojito.mojitoboot.common.daomodel.GoodsDO;
import com.mojito.mojitoboot.common.daomodel.GoodsStockDO;
import com.mojito.mojitoboot.core.service.GoodsCoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: Mojito
 * @Date: 2019/2/23 17:27
 * @Description:
 */
@Service
public class GoodsCoreServiceImp implements GoodsCoreService {

    @Resource
    GoodsDOMapper goodsDOMapper;

    @Resource
    GoodsStockDOMapper goodsStockDOMapper;

    @Override
    public Integer insertGoods(GoodsBO goodsBO) {

        GoodsDO goodsDO = ConvertUtil.convert(goodsBO, GoodsDO.class);
        goodsDOMapper.insertSelective(goodsDO);
        goodsBO.setId(goodsDO.getId());

        GoodsStockDO goodsStockDO = new GoodsStockDO();
        goodsStockDO.setGoodsId(goodsBO.getId());
        goodsStockDO.setStock(goodsBO.getStock());
        goodsStockDOMapper.insertSelective(goodsStockDO);

        return null;
    }

}

package com.mojito.mojitoboot.core.service.impl;

import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.common.mapper.GoodsDOMapper;
import com.mojito.mojitoboot.common.mapper.GoodsStockDOMapper;
import com.mojito.mojitoboot.common.utils.other.ConvertUtil;
import com.mojito.mojitoboot.common.daomodel.GoodsDO;
import com.mojito.mojitoboot.common.daomodel.GoodsStockDO;
import com.mojito.mojitoboot.core.service.GoodsCoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Mojito
 * @Date: 2019/2/23 17:27
 * @Description:
 */
@Service
@Transactional
public class GoodsCoreServiceImp implements GoodsCoreService {

    @Resource
    GoodsDOMapper goodsDOMapper;

    @Resource
    GoodsStockDOMapper goodsStockDOMapper;

    @Override
    public GoodsBO insertGoods(GoodsBO goodsBO) {

        GoodsDO goodsDO = ConvertUtil.convert(goodsBO, GoodsDO.class);
        goodsDOMapper.insertSelective(goodsDO);
        goodsBO.setId(goodsDO.getId());

        GoodsStockDO goodsStockDO = new GoodsStockDO();
        goodsStockDO.setGoodsId(goodsBO.getId());
        goodsStockDO.setStock(goodsBO.getStock());
        goodsStockDOMapper.insertSelective(goodsStockDO);

        return this.getGoodsById(goodsDO.getId());
    }

    @Override
    public List<GoodsBO> selectGoodsList() {
        List<GoodsDO> goodsDOList = goodsDOMapper.selectGoodsList();

        List<GoodsBO> goodsBOS = goodsDOList.stream().map(goodsDO -> {
            GoodsStockDO goodsStockDO = goodsStockDOMapper.selectByGoodsId(goodsDO.getId());
            GoodsBO goodsBO = ConvertUtil.convert(goodsDO,GoodsBO.class);
            goodsBO.setStock(goodsStockDO.getStock());
            return goodsBO;
        }).collect(Collectors.toList());
        return goodsBOS;
    }

    @Override
    public GoodsBO selectGoodById(Integer id) {
        return getGoodsById(id);
    }

    @Override
    @Transactional
    public int updateStock(Integer goodsId, Integer amount) {
        return goodsStockDOMapper.updateStock(goodsId,amount);
    }

    private GoodsBO getGoodsById(Integer id) {
        GoodsDO goodsDO = goodsDOMapper.selectByPrimaryKey(id);
        if(goodsDO == null){
            return  null;
        }
        GoodsStockDO goodsStockDO = goodsStockDOMapper.selectByGoodsId(id);
        GoodsBO goodsBO = ConvertUtil.convert(goodsDO, GoodsBO.class);
        goodsBO.setPrice(new BigDecimal(goodsDO.getPrice()));
        goodsBO.setStock(goodsStockDO.getStock());

        return goodsBO;
    }

}

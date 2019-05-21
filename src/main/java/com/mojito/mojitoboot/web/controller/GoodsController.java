package com.mojito.mojitoboot.web.controller;

import com.mojito.mojitoboot.biz.service.GoodsService;
import com.mojito.mojitoboot.common.utils.other.ConvertUtil;
import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.web.URLCnstant.URLConstant;
import com.mojito.mojitoboot.web.request.GoodsCreateRequest;
import com.mojito.mojitoboot.web.response.CommonReturnType;
import com.mojito.mojitoboot.web.response.error.BusinessException;
import com.mojito.mojitoboot.web.viewmodel.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Mojito
 * @Date: 2019/3/3 15:24
 * @Description:
 */
@RestController
@RequestMapping(URLConstant.GoodsUrl.GOODS)
//@CrossOrigin(origins = {"*"},allowCredentials ="true")
public class GoodsController extends BaseController {

    @Autowired
    GoodsService goodsService;

    @PostMapping(URLConstant.GoodsUrl.GOODS_CREATE)
    public CommonReturnType goodsCreate (@RequestBody GoodsCreateRequest request) throws BusinessException {
        GoodsBO goodsBO = new GoodsBO();
        goodsBO.setStock(request.getStock());
        goodsBO.setPrice(request.getPrice());
        goodsBO.setDescription(request.getDescription());
        goodsBO.setImgUrl(request.getImgUrl());
        goodsBO.setTitle(request.getTitle());
        GoodsBO goods = goodsService.createGoods(goodsBO);

        GoodsVO goodsVO = ConvertUtil.convert(goods, GoodsVO.class);

        return  CommonReturnType.success(goodsVO);

    }

    @GetMapping(URLConstant.GoodsUrl.GOODS_LIST)
    public CommonReturnType goodsList(){
        List<GoodsBO> goodsList = goodsService.getGoodsList();
        return CommonReturnType.success(ConvertUtil.convertList(goodsList,GoodsVO.class));
    }

    @GetMapping(URLConstant.GoodsUrl.GOOD_DETAIL)
    public  CommonReturnType goods(@RequestParam Integer id){
        GoodsBO goods = goodsService.getGood(id);
        return CommonReturnType.success(ConvertUtil.convert(goods,GoodsVO.class));
    }
}

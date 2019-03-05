package com.mojito.mojitoboot.biz.service.impl;

import com.mojito.mojitoboot.core.bizmodel.GoodsBO;
import com.mojito.mojitoboot.biz.service.GoodsService;
import com.mojito.mojitoboot.web.response.error.BusinessException;
import com.mojito.mojitoboot.web.response.error.EmBusinessError;
import com.mojito.mojitoboot.common.utils.validator.ValidationResult;
import com.mojito.mojitoboot.common.utils.validator.ValidatorImpl;
import com.mojito.mojitoboot.core.service.GoodsCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Mojito
 * @Date: 2019/2/23 16:35
 * @Description:
 */
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    ValidatorImpl validator;

    @Autowired
    GoodsCoreService goodsCoreService;


    @Override
    @Transactional
    public GoodsBO createGoods(GoodsBO goodsBO) throws BusinessException {

        ValidationResult validationResult = validator.validate(goodsBO);
        if(validationResult.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
        }


//        goodsCoreService

        return null;
    }

    @Override
    public List<GoodsBO> getGoodsList() {
        return null;
    }

    @Override
    public GoodsBO getGoods(Integer id) {
        return null;
    }
}

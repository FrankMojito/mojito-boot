package com.mojito.mojitoboot.biz.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.common.error.BusinessException;
import com.mojito.mojitoboot.common.error.EmBusinessError;
import com.mojito.mojitoboot.common.utils.other.ConvertUtil;
import com.mojito.mojitoboot.common.utils.validator.ValidationResult;
import com.mojito.mojitoboot.common.utils.validator.ValidatorImpl;
import com.mojito.mojitoboot.core.daomodel.UserDO;
import com.mojito.mojitoboot.core.daomodel.UserPasswordDO;
import com.mojito.mojitoboot.core.service.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Auther: Mojito
 * @Date: 2019/1/17 00:56
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserCoreService userCoreService;

    @Autowired
    ValidatorImpl validator;

    @Override
    public UserBO getUserById(Integer id) {
        UserDO userDO = userCoreService.getUserById(id);
        UserBO userBO = ConvertUtil.convert(userDO, UserBO.class);
        UserPasswordDO userPasswordDO = userCoreService.selectPassWordByUserId(userDO.getId());
        userBO.setEncrptPassword(userPasswordDO.getPassword());
        return userBO;
    }

    @Override
    public Integer userRegister(UserBO userBO) throws BusinessException {
        Integer count;
        if(userBO == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ValidationResult validationResult = validator.validate(userBO);
        if(validationResult.isHasError()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
        }

        try {
            count = userCoreService.setUser(userBO);
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"手机号已注册");
        }

        return count;
    }

    @Override
    public UserBO validateLogin(String telephone,String password) throws BusinessException {
        if(org.apache.commons.lang3.StringUtils.isEmpty(telephone)|| StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO = userCoreService.selectByTelephone(telephone);
        UserBO userBO = ConvertUtil.convert(userDO, UserBO.class);
        UserPasswordDO userPasswordDO = userCoreService.selectPassWordByUserId(userDO.getId());
        userBO.setEncrptPassword(userPasswordDO.getPassword());
        return userBO;
    }

}

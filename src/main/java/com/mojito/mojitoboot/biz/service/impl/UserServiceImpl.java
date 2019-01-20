package com.mojito.mojitoboot.biz.service.impl;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.common.error.BusinessException;
import com.mojito.mojitoboot.common.error.EmBusinessError;
import com.mojito.mojitoboot.common.mapper.UserPasswordDOMapper;
import com.mojito.mojitoboot.common.utils.ConvertUtil;
import com.mojito.mojitoboot.common.viewmodel.UserVO;
import com.mojito.mojitoboot.core.daomodel.UserDO;
import com.mojito.mojitoboot.core.daomodel.UserPasswordDO;
import com.mojito.mojitoboot.core.service.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Auther: Mojito
 * @Date: 2019/1/17 00:56
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserCoreService userCoreService;

    @Override
    public UserBO getUserById(Integer id) {
        UserDO userDO = userCoreService.getUserById(id);
        UserBO userBO = ConvertUtil.convert(userDO, UserBO.class);
        UserPasswordDO userPasswordDO = userCoreService.selectPassWordByUserId(userDO.getId());
        userBO.setEncrptPassword(userPasswordDO.getPassword());
        return userBO;
    }

    @Override
    public Integer setUser(UserBO userBO) throws BusinessException {
        if(userBO == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        return null;
    }
}

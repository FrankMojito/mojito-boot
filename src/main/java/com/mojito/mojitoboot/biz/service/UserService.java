package com.mojito.mojitoboot.biz.service;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.common.error.BusinessException;

/**
 * @Auther: Mojito
 * @Date: 2019/1/17 00:50
 * @Description:
 */
public interface UserService {
    UserBO getUserById(Integer id);

    Integer userRegister(UserBO userBO) throws BusinessException;

    UserBO validateLogin(String telephone, String password) throws BusinessException;
}

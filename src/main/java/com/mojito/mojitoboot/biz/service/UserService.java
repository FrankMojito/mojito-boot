package com.mojito.mojitoboot.biz.service;

import com.mojito.mojitoboot.core.bizmodel.UserBO;
import com.mojito.mojitoboot.web.response.error.BusinessException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: Mojito
 * @Date: 2019/1/17 00:50
 * @Description:
 */
public interface UserService {
    UserBO getUserById(Integer id);

    Integer userRegister(UserBO userBO) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserBO validateLogin(String telephone, String password) throws BusinessException;
}

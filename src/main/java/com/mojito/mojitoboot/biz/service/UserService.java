package com.mojito.mojitoboot.biz.service;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.common.error.BusinessException;
import com.mojito.mojitoboot.common.viewmodel.UserVO;

/**
 * @Auther: Mojito
 * @Date: 2019/1/17 00:50
 * @Description:
 */
public interface UserService {
    public UserBO getUserById(Integer id);

    public Integer setUser(UserBO userBO) throws BusinessException;
}

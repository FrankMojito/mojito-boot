package com.mojito.mojitoboot.core.service;

import com.mojito.mojitoboot.core.bizmodel.UserBO;
import com.mojito.mojitoboot.common.daomodel.UserDO;
import com.mojito.mojitoboot.common.daomodel.UserPasswordDO;

/**
 * @Auther: Mojito
 * @Date: 2019/1/17 01:48
 * @Description:
 */
public interface UserCoreService {

   public UserDO getUserById(Integer id);

   public Integer setUser(UserBO userBO);

   public UserPasswordDO selectPassWordByUserId(Integer id);

   UserDO selectByTelephone(String telephone);
}

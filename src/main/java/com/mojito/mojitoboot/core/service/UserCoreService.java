package com.mojito.mojitoboot.core.service;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.core.daomodel.UserDO;
/**
 * @Auther: Mojito
 * @Date: 2019/1/17 01:48
 * @Description:
 */
public interface UserCoreService {

   public UserDO getUserById(Integer id);

   public Integer setUser(UserBO userBO);

}

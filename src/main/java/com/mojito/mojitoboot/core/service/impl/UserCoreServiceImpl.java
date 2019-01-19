package com.mojito.mojitoboot.core.service.impl;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.common.mapper.UserDOMapper;
import com.mojito.mojitoboot.core.daomodel.UserDO;
import com.mojito.mojitoboot.core.service.UserCoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Auther: Mojito
 * @Date: 2019/1/17 01:52
 * @Description:
 */
@Service
public class UserCoreServiceImpl implements UserCoreService {

    @Resource
    private UserDOMapper userDOMapper;


    @Override
    public UserDO getUserById(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer setUser(UserBO userBO) {
        return null;
    }
}

package com.mojito.mojitoboot.biz.service.impl;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.common.mapper.UserPasswordDOMapper;
import com.mojito.mojitoboot.common.utils.ConvertUtil;
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

    @Resource
    UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserBO getUserById(Integer id) {
        UserDO userDO = userCoreService.getUserById(id);
        UserBO userBO = ConvertUtil.convert(userDO, UserBO.class);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        userBO.setEncrptPassword(userPasswordDO.getPassword());
        return userBO;
    }
}

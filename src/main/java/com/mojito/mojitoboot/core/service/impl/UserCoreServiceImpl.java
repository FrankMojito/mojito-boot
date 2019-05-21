package com.mojito.mojitoboot.core.service.impl;

import com.mojito.mojitoboot.core.bizmodel.UserBO;
import com.mojito.mojitoboot.common.mapper.UserDOMapper;
import com.mojito.mojitoboot.common.mapper.UserPasswordDOMapper;
import com.mojito.mojitoboot.common.utils.other.ConvertUtil;
import com.mojito.mojitoboot.common.daomodel.UserDO;
import com.mojito.mojitoboot.common.daomodel.UserPasswordDO;
import com.mojito.mojitoboot.core.service.UserCoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    UserPasswordDOMapper userPasswordDOMapper;


    @Override
    public UserDO getUserById(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Integer insertUser(UserBO userBO) {

        UserDO userDO = ConvertUtil.convert(userBO, UserDO.class);
        userDOMapper.insertSelective(userDO);
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setPassword(userBO.getEncrptPassword());
        userPasswordDO.setUserId(userDO.getId());
        return userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    public UserPasswordDO selectPassWordByUserId(Integer id){
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        return userPasswordDO;
    }

    @Override
    public UserDO selectByTelephone(String telephone) {
        return userDOMapper.selectByTelephone(telephone);
    }


}

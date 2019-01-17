package com.mojito.mojitoboot.web.webapi;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.common.utils.ConvertUtil;
import com.mojito.mojitoboot.common.viewmodel.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserWeb {

    @Autowired
    private UserService userService;

    @RequestMapping("/user-info")
    public UserVO getUserById(Integer id){
        UserBO userBO = userService.getUserById(id);
        UserVO userVO = ConvertUtil.convert(userBO, UserVO.class);
        return userVO;
    }
}

package com.mojito.mojitoboot.web.controller;

import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.common.error.BusinessException;
import com.mojito.mojitoboot.common.error.EmBusinessError;
import com.mojito.mojitoboot.common.response.CommonReturnType;
import com.mojito.mojitoboot.common.utils.ConvertUtil;
import com.mojito.mojitoboot.common.viewmodel.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Mojito
 * @Date: 2019/1/18 00:54
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/user-info")
    public CommonReturnType getUserById(Integer id) throws BusinessException {
        UserBO userBO = userService.getUserById(id);
        UserVO userVO = ConvertUtil.convert(userBO, UserVO.class);
        if (userVO == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.success(userVO);
    }

    public CommonReturnType userLogin(@RequestBody UserVO user){
        request.getSession().setAttribute(user.getId().toString(),user);
        return null;
    }

    public CommonReturnType userRegister(@RequestBody UserVO userVO){
        if(userVO.getGender()!=null&&userVO.getName()!=null&&userVO.getTelephone()!=null){

        }
        return null;
    }

}

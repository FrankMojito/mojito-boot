package com.mojito.mojitoboot.web.controller;

import com.alibaba.druid.util.StringUtils;
import com.mojito.mojitoboot.biz.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.common.error.BusinessException;
import com.mojito.mojitoboot.common.error.EmBusinessError;
import com.mojito.mojitoboot.common.response.CommonReturnType;
import com.mojito.mojitoboot.common.utils.ConvertUtil;
import com.mojito.mojitoboot.common.utils.fileRenderUtil.EncodeByMD5Util;
import com.mojito.mojitoboot.common.viewmodel.UserVO;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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

    @PostMapping("/user-register")
    public CommonReturnType userRegister(
            String name,
            Integer gender,
            Integer age,
            String telephone,
            String otp,
            String password ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        String otpInSession = (String) request.getSession().getAttribute(telephone);
        if(StringUtils.equals(otp,otpInSession)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserBO userBO = new UserBO();
        userBO.setAge(age);
        userBO.setGender(Byte.valueOf(String.valueOf(gender.intValue())));
        userBO.setName(name);
        userBO.setTelephone(telephone);
        userBO.setEncrptPassword(EncodeByMD5Util.EncodeByMD5(password));
        userService.setUser(userBO);
        return CommonReturnType.success(null);
    }

    @PostMapping("/getotp")
    public CommonReturnType getOtp(String telephone){
        // 生成验证码
        Random random = new Random();
        int i = random.nextInt(99999);
        i += 10000;
        String otp = String.valueOf(i);

        // 验证码和手机号关联
        request.getSession().setAttribute(telephone,otp);

        // 通过短信通道发送给用户
        return null;
    }

}

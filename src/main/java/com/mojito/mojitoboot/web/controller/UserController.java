package com.mojito.mojitoboot.web.controller;

import com.alibaba.druid.util.StringUtils;
import com.mojito.mojitoboot.core.bizmodel.UserBO;
import com.mojito.mojitoboot.biz.service.UserService;
import com.mojito.mojitoboot.web.request.UserRegisterRequest;
import com.mojito.mojitoboot.web.response.error.BusinessException;
import com.mojito.mojitoboot.web.response.error.EmBusinessError;
import com.mojito.mojitoboot.web.response.CommonReturnType;
import com.mojito.mojitoboot.common.utils.other.ConvertUtil;
import com.mojito.mojitoboot.common.utils.fileRenderUtil.EncodeByMD5Util;
import com.mojito.mojitoboot.web.viewmodel.UserVO;
import com.mojito.mojitoboot.web.URLCnstant.URLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(URLConstant.UserURL.USER)
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(URLConstant.UserURL.USER_INFO)
    public CommonReturnType getUserById(Integer id) throws BusinessException {
        UserBO userBO = userService.getUserById(id);
        UserVO userVO = ConvertUtil.convert(userBO, UserVO.class);
        if (userVO == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.success(userVO);
    }

    @RequestMapping(URLConstant.UserURL.USER_LOGIN)
    public CommonReturnType userLogin(String telephone,String password) throws BusinessException {
        UserBO userBO = userService.validateLogin(telephone,password);

//        request.getSession().setAttribute("HAS_LOGGED_IN",true);
//        request.getSession().setAttribute("LOGIN_USER",userBO);
        return CommonReturnType.success(userBO);
    }

    @PostMapping(URLConstant.UserURL.USER_REGISTER)
    public CommonReturnType userRegister(@RequestBody UserRegisterRequest userRegisterRequest) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(request.getSession().getAttribute("13974082572")+"aaaaa");
        String otpInSession = (String) request.getSession().getAttribute(userRegisterRequest.getTelephone());
        if(!StringUtils.equals(userRegisterRequest.getOtp(),otpInSession)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码错误");
        }
        UserBO userBO = new UserBO();
        userBO.setAge(userRegisterRequest.getAge());
        userBO.setGender(Byte.valueOf(String.valueOf(userRegisterRequest.getGender().intValue())));
        userBO.setName(userRegisterRequest.getName());
        userBO.setTelephone(userRegisterRequest.getTelephone());
        System.out.println(userRegisterRequest.getPassword());
        userBO.setEncrptPassword(userRegisterRequest.getPassword());
        userBO.setThirdPartyId("wechat");
        userBO.setRegisterMode("手机");
        Integer count = userService.userRegister(userBO);
        if(count != 1){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"注册失败");
        }
        return CommonReturnType.success(null);
    }

    @GetMapping(URLConstant.UserURL.GETOTP)
    public CommonReturnType getOtp(@RequestParam String telephone) throws BusinessException {
        // 生成验证码
        if(org.apache.commons.lang3.StringUtils.isBlank(telephone)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Random random = new Random();
        int i = random.nextInt(99999);
        i += 10000;
        String otp = String.valueOf(i);

        // 验证码和手机号关联
        request.getSession().setAttribute(telephone,otp);
        System.out.println(request.getSession().getAttribute(telephone));
        System.out.println("telephone="+telephone+"&otp="+otp);
        // 通过短信通道发送给用户
        return CommonReturnType.success(null);
    }

}

package com.mojito.mojitoboot.core.bizmodel;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: Mojito
 * @Date: 2019/1/17 23:56
 * @Description:
 */
@Data
public class UserBO {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotBlank(message = "性别不能为空")
    private Byte gender;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0,message = "年龄需要大于零")
    @Max(value = 120,message = "年龄必须小于120")
    private Integer age;

    @NotBlank(message = "手机不能为空")
    private String telephone;

    private String registerMode;

    private String thirdPartyId;
    @NotBlank(message = "密码不能为空")
    private String encrptPassword;

}

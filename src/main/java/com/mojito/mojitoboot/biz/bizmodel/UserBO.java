package com.mojito.mojitoboot.biz.bizmodel;

import lombok.Data;
/**
 * @Auther: Mojito
 * @Date: 2019/1/17 23:56
 * @Description:
 */
@Data
public class UserBO {

    private Integer id;

    private String name;

    private Byte gender;

    private Integer age;

    private String telephone;

    private String registerMode;

    private String thirdPartyId;

    private String encrptPassword;

}

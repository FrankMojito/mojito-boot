package com.mojito.mojitoboot.biz.bizmodel;

import lombok.Data;

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

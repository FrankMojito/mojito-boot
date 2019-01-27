package com.mojito.mojitoboot.biz.bizmodel;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: Mojito
 * @Date: 2019/1/18 23:21
 * @Description:
 */
@Data
public class GoodsBO {

    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private Integer sales;

    private String imgUrl;

}

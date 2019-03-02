package com.mojito.mojitoboot.core.bizmodel;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @Auther: Mojito
 * @Date: 2019/1/18 23:21
 * @Description:
 */
@Data
public class GoodsBO {

    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String title;

    @NotBlank(message = "商品价格不能为空")
    @Min(value = 0,message = "商品价格必须大于零")
    // double 传到前端会有精度损失的风险
    private BigDecimal price;

    @NotBlank(message = "库存不能为空")
    private Integer stock;

    @NotBlank(message = "商品描述不能为空")
    private String description;

    private Integer sales;

    @NotBlank(message = "商品图片不能为空")
    private String imgUrl;

}

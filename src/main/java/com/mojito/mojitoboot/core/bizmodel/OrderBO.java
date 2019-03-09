package com.mojito.mojitoboot.core.bizmodel;

import java.math.BigDecimal;

/**
 * @Auther: Mojito
 * @Date: 2019/3/9 16:37
 * @Description:
 */
public class OrderBO {
    // 交易单号，采用自定义生成
    private String id;

    private Integer userId;

    private Integer goodsId;

    private Integer amount;

    private BigDecimal price;

    private BigDecimal totalPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

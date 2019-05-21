package com.mojito.mojitoboot.web.request;

import java.math.BigDecimal;

/**
 * @Auther: Mojito
 * @Date: 2019/3/3 15:36
 * @Description:
 */
public class GoodsCreateRequest {

    private String title;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private String imgUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

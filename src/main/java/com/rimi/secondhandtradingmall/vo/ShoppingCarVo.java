package com.rimi.secondhandtradingmall.vo;

import lombok.Data;

/**
 * @author junelee
 * @date 2019/12/11 11:21
 */
@Data
public class ShoppingCarVo {

    /**
     * 商品的id
     */
    private Integer goodsId;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 当前用户的手机号
     */
    private String telephone;

    /**
     * 商品的名称
     */
    private String goodsName;

    /**
     * 商品的单价
     */
    private Double goodsPrice;

    /**
     * 商品的数量
     */
    private Integer goodsCount;

    /**
     * 商品的小计
     */
    private Double goodsSubtotal;

    /**
     * 商品图片
     */
    private String goodsLogo;


}

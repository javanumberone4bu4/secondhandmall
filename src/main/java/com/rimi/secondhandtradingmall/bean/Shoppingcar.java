package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * ${Description}
 *
 * @author junelee
 * @date 2019/12/12 17:32
 */
@ApiModel(value = "com-rimi-secondhandtradingmall-bean-Shoppingcar")
@Data
public class Shoppingcar implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer shoppingcarId;

    /**
     * 单个商品的ID
     */
    @ApiModelProperty(value = "单个商品的ID")
    private Integer goodsId;

    /**
     * 单个商品的数量
     */
    @ApiModelProperty(value = "单个商品的数量")
    private Integer shoppingcarNum;

    /**
     * 单个商品的小计
     */
    @ApiModelProperty(value = "单个商品的小计")
    private Double shoppingcarSubtotal;

    /**
     * 合计（暂时不用）
     */
    @ApiModelProperty(value = "合计（暂时不用）")
    private Double shoppingcarSumsubtotal;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;

    /**
     * 商品的尺寸
     */
    @ApiModelProperty(value = "商品的尺寸")
    private String shoppingcarSize;

    /**
     * 商品的颜色
     */
    @ApiModelProperty(value = "商品的颜色")
    private String shoppingcarColor;

    /**
     * 商品对象
     */
    private Goods goods;

    private static final long serialVersionUID = 1L;
}
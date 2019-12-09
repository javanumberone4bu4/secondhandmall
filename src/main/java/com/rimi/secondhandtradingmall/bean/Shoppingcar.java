package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:43
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Shoppingcar")
@Data
public class Shoppingcar implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer shoppingcarId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer shoppingcarNum;

    /**
     * 小计
     */
    @ApiModelProperty(value = "小计")
    private Double shoppingcarSubtotal;

    /**
     * 合计
     */
    @ApiModelProperty(value = "合计")
    private Double shoppingcarSumsubtotal;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;

    private static final long serialVersionUID = 1L;
}
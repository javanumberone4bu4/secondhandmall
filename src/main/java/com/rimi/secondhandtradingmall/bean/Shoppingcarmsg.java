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
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Shoppingcarmsg")
@Data
public class Shoppingcarmsg implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer shoppingcarmsgId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;

    /**
     * 总数量(查询购物车总数量)
     */
    @ApiModelProperty(value = "总数量(查询购物车总数量)")
    private Integer shoppingcarmsgSumnum;

    private static final long serialVersionUID = 1L;
}
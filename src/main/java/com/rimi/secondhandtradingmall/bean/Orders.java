package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/10 8:58
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Orders")
@Data
public class Orders implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer ordersId;

    /**
     * 订单信息
     */
    @ApiModelProperty(value = "订单信息")
    private String ordersMsg;

    /**
     * 商品ID(可多个)
     */
    @ApiModelProperty(value = "商品ID(可多个)")
    private String goodsId;

    /**
     * 件数(总商品件数)
     */
    @ApiModelProperty(value = "件数(总商品件数)")
    private Integer ordersSumnum;

    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额")
    private Double ordersSummoney;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private String ordersStatus;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;

    /**
     * 用户的收货地址
     */
    @ApiModelProperty(value = "用户的收货地址")
    private String ordersAddress;

    private static final long serialVersionUID = 1L;
}
package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 13:40
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Goods")
@Data
public class Goods implements Serializable {
    /**
     * ID
     */
    @Field
    @ApiModelProperty(value = "ID")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @Field
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 价格
     */
    @Field
    @ApiModelProperty(value = "价格")
    private Double goodsPrice;

    /**
     * 上架时间
     */
    @Field
    @ApiModelProperty(value = "上架时间")
    private String goodsUptime;

    /**
     * 描述
     */
    @Field
    @ApiModelProperty(value = "描述")
    private String goodsDescription;

    /**
     * 多少人付款
     */
    @Field
    @ApiModelProperty(value = "多少人付款")
    private Integer goodsPaypeople;

    /**
     * 颜色
     */
    @Field
    @ApiModelProperty(value = "颜色")
    private String goodsColor;

    /**
     * 尺寸
     */
    @Field
    @ApiModelProperty(value = "尺寸")
    private String goodsSize;

    /**
     * 数量
     */
    @Field
    @ApiModelProperty(value = "数量")
    private Integer goodsNum;

    /**
     * 购买次数
     */
    @Field
    @ApiModelProperty(value = "购买次数")
    private Integer goodsPurchasetime;

    /**
     * 二级分类表ID
     */
    @Field
    @ApiModelProperty(value = "二级分类表ID")
    private Integer secondclassifyId;

    /**
     * 商品图标
     */
    @Field
    @ApiModelProperty(value = "商品图标")
    private String goodsLogo;
    private Secondclassify secondclassify;

    private List<Collections> list;


    private static final long serialVersionUID = 1L;
}
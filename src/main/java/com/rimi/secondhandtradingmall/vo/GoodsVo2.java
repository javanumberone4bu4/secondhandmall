package com.rimi.secondhandtradingmall.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author junelee
 * @date 2019/12/9 15:16
 */
@Data
public class GoodsVo2 {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Integer goodsId;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格")
    private Double goodsPrice;

    /**
     * 数量
     */
    @ApiModelProperty(value = "用户购买的数量")
    private Integer shoppingcarNum;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String telephone;

    /**
     * sessionId
     */
    @ApiModelProperty(value = "sessionId")
    private String sessionId;

}

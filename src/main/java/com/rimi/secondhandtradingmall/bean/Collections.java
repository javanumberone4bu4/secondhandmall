package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:46
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Collections")
@Data
public class Collections implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer collectionsId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Integer goodsId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;
    private Goods goods;
    private static final long serialVersionUID = 1L;
}
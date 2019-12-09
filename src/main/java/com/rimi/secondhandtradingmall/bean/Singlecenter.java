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
 * @date 2019/12/4 15:41
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Singlecenter")
@Data
public class Singlecenter implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer singlecenterId;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String singlecenterImage;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;

    /**
     * 待发货数
     */
    @ApiModelProperty(value = "待发货数")
    private Integer singlecenterDfnum;

    /**
     * 待收货数
     */
    @ApiModelProperty(value = "待收货数")
    private Integer singlecenterDsnum;

    /**
     * 积分数
     */
    @ApiModelProperty(value = "积分数")
    private Integer singlecenterMark;
    private List<Comments> list9;
    private static final long serialVersionUID = 1L;
}
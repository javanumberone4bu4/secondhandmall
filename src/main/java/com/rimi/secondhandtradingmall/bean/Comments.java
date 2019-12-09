package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * ${Description}
 *
 * @author Wang Xiaoping
 * @date 2019/12/4 15:45
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Comments")
@Data
public class Comments implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer commentsId;

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

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentsContent;

    /**
     * 评论图片
     */
    @ApiModelProperty(value = "评论图片")
    private String commentsPictures;

    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间")
    private String commentsTime;
    private Singlecenter singlecenter;
    private static final long serialVersionUID = 1L;
}
package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * ${Description}
 * @author Wang Xiaoping
 * @date 2019/12/9 20:08
 */
@ApiModel(value = "com-rimi-secondhandtradingmall-bean-Msg")
@Data
public class Msg implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer msgId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String telephone;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String msgMessage;

    /**
     * 用户的sessionId
     */
    @ApiModelProperty(value = "用户的sessionId")
    private String sessionId;

    private static final long serialVersionUID = 1L;
}
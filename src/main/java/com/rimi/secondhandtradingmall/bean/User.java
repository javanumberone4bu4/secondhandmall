package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * ${Description}
 * @author Wang Xiaoping
 * @date 2019/12/3 19:26
 */
@ApiModel(value="com.rimi.secondhandtradingmall.bean.User")
@Data
public class User implements Serializable {
    /**
    * ID
    */
    @ApiModelProperty(value="ID")
    private Integer userId;

    /**
    * 手机号(用户名)
    */
    @ApiModelProperty(value="手机号(用户名)")
    private String telephone;

    /**
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    private static final long serialVersionUID = 1L;
}
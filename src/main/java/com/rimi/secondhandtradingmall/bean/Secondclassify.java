package com.rimi.secondhandtradingmall.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * ${Description}
 * @author Wang Xiaoping
 * @date 2019/12/3 19:25
 */
@ApiModel(value="com.rimi.secondhandtradingmall.bean.Secondclassify")
@Data
public class Secondclassify implements Serializable {
    /**
    * ID
    */
    @ApiModelProperty(value="ID")
    private Integer secondclassifyId;

    /**
    * 总分类表id
    */
    @ApiModelProperty(value="总分类表id")
    private Integer sumclassifyId;

    /**
    * 分类名
    */
    @ApiModelProperty(value="分类名")
    private String secondclassifyName;
    private Sumclassify sumclassify;
    private List<Goods> list2;
    private static final long serialVersionUID = 1L;
}
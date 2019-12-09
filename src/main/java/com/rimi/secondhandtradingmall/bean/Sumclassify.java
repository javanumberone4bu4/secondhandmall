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
 * @date 2019/12/4 12:45
 */
@ApiModel(value = "com.rimi.secondhandtradingmall.bean.Sumclassify")
@Data
public class Sumclassify implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Integer sumclassifyId;

    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String sumclassifyName;

    /**
     * 点击量
     */
    @ApiModelProperty(value = "点击量")
    private Integer sumclassifyClicknum;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String sumclassifyLogo;

    /**
     * 多少元起
     */
    @ApiModelProperty(value = "多少元起")
    private String sumclassifyMoney;
    private List<Secondclassify> list;
    private static final long serialVersionUID = 1L;
}
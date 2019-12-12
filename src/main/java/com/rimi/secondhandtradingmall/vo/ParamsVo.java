package com.rimi.secondhandtradingmall.vo;


import lombok.Data;

/**
 * @author Wang Xiaoping
 * @date 2019/12/12 16:51
 */
@Data
public class ParamsVo {
    private String secondclassifyName;
    private double goodsPrice;
    private String goodsUptime;
    private Integer sumclassifyId;
    private Integer pageNum;
    private Integer pageSize;
}

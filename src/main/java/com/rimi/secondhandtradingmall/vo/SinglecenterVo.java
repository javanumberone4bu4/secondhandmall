package com.rimi.secondhandtradingmall.vo;

import lombok.Data;

/**
 * @author junelee
 * @date 2019/12/10 11:44
 */
@Data
public class SinglecenterVo {

    /**
     * 收货人
     */
    private String singlecenterConsignee;

    /**
     * 收获地址
     */
    private String singlecenterAddress;

    /**
     * 收货人手机号
     */
    private String sTelephone;
    /**
     * 是否默认
     */
    private String sureAddress;
}

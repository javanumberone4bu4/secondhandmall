package com.rimi.secondhandtradingmall.vo;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author Wang Xiaoping
 * @date 2019/12/4 19:37
 */
@Data
public class SumclassifyVo {
    private String sumclassifyName;
    private Integer sumclassifyId;
    private Integer pageNum;
    private Integer pageSize;
}

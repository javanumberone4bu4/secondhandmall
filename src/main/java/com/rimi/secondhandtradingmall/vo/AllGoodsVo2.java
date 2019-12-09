package com.rimi.secondhandtradingmall.vo;

        import com.rimi.secondhandtradingmall.bean.Goods;
        import io.swagger.annotations.ApiModelProperty;
        import lombok.Data;

        import java.util.List;

/**
 * 所有购物车内要支付的商品
 *
 * @author junelee
 * @date 2019/12/9 16:35
 */
@Data
public class AllGoodsVo2 {

    @ApiModelProperty(value = "所有商品的id")
    private List<Integer> goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String telephone;

    /**
     * sessionId
     */
    @ApiModelProperty(value = "sessionId")
    private String sessionId;


    /**
     * 数量
     */
    @ApiModelProperty(value = "用户购买的数量")
    private Integer shoppingcarNum;

}

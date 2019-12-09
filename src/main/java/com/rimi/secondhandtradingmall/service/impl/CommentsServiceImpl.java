package com.rimi.secondhandtradingmall.service.impl;

import com.rimi.secondhandtradingmall.bean.Comments;
import com.rimi.secondhandtradingmall.mapper.CommentsMapper;
import com.rimi.secondhandtradingmall.service.ICommentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wang Xiaoping
 * @date 2019/12/8 17:38
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentsServiceImpl implements ICommentsService {
    private CommentsMapper commentsMapper;

    public CommentsServiceImpl(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public List<Comments> selectByGoodsId(Integer goodsId) {
        List<Comments> comments = commentsMapper.selectByGoodsId(goodsId);
        if(comments!=null&&comments.size()>0){
            return comments;
        }
        return null;
    }
}

package com.rimi.secondhandtradingmall.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MultiMapSolrParams;
import org.springframework.data.solr.core.QueryParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wang Xiaoping
 * @date 2019/12/6 10:12
 */
@Controller
public class SolrController {
    private final SolrClient solrClient;

    public SolrController(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @GetMapping("/query")
    public String query(String goodsName, Model model, HttpSession session) throws IOException, SolrServerException {
        //拼写查询条件
        SolrQuery solrQuery=new SolrQuery();
        SolrQuery solrQuery1 = solrQuery.setQuery("goodsName:" + goodsName);
        //String intro = infomationSearchReqVO.getIntro();
        ////转义特殊字符
        //String searchIntro = queryParser.parse()
        //MultiMapSolrParams solrParams = queryParser.parse("goodsName:" + goodsName);
        //QueryResponse r = server.query(solrParams, SolrRequest.METHOD.GET);
        //总条数
        solrQuery.setRows(20);
        //开始条数
        solrQuery.setStart(0);
        //开启高亮
        solrQuery.setHighlight(true);
        //设置高亮字段
        solrQuery.addHighlightField("goodsName");
        //设置高亮样式
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        //执行查询
        QueryResponse query = solrClient.query(solrQuery);
        //获取查询结果
        SolrDocumentList results = query.getResults();
        //获取高亮结果
        Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
        //进行处理
        for (SolrDocument result : results) {
            String goodsName1 = highlighting.get(result.get("id")).get("goodsName").get(0);
            if(!StringUtils.isEmpty(goodsName1)){
                result.setField("goodsName",goodsName1);
            }
        }
        model.addAttribute("cache",results);
        session.setAttribute("count",results.size());
        return "list";
    }
}

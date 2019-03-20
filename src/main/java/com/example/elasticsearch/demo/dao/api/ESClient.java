package com.example.elasticsearch.demo.dao.api;

import com.example.elasticsearch.demo.config.ESConfig;
import com.example.elasticsearch.demo.exception.ESClientException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ESClient {

    @Autowired
    private ESConfig es;

    public int createDocument(String index , String type , String jsondata )  {

        IndexRequest indexRequest = new IndexRequest(index, type );
        indexRequest.source(jsondata, XContentType.JSON);
        try {
            IndexResponse indexResponse = es.client().index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.status().getStatus();
        } catch (IOException e) {
            throw new ESClientException(e.getMessage());
        }
    }

    public String getDocument(String index , String type , String id) {
        GetRequest getRequest = new GetRequest(index, type,id);
        try {
            GetResponse getResponse = es.client().get(getRequest, RequestOptions.DEFAULT);
            return getResponse.getSourceAsString();
        } catch (Exception e) {
            throw new ESClientException(e.getMessage());
        }
    }

    public List<String> getDocuments(String index , String type) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        try {
            SearchResponse searchResponse = es.client().search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();
            List result = Arrays.stream(hits.getHits()).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            throw new ESClientException(e.getMessage());
        }
    }

    public String setDocument(String index , String type , String id, String message) {

        UpdateRequest request = new UpdateRequest(index, type, id)
                .doc("user111" , message);

        request.fetchSource(true); // 변경된 docment 값을 리턴 받을지 여부
        request.retryOnConflict(3); // 재전송 시도 횟수
        request.docAsUpsert(false); // true일때 아이디 기준으로 데이터가 없을 경우 insert

        try {
            UpdateResponse updateResponse = es.client().update(request, RequestOptions.DEFAULT);
            GetResult result = updateResponse.getGetResult();
            return result.sourceAsString();
        } catch (IOException e) {
            throw new ESClientException(e.getMessage());
        }
    }

    public int delDocument(String index , String type , String id) {
        DeleteRequest delRequest = new DeleteRequest(index, type,id);
        try {
            DeleteResponse delResponse = es.client().delete(delRequest, RequestOptions.DEFAULT);
            return delResponse.status().getStatus();
        } catch (Exception e) {
            throw new ESClientException(e.getMessage());
        }
    }

}

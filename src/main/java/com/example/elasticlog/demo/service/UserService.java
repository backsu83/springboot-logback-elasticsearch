package com.example.elasticlog.demo.service;

import com.example.elasticlog.demo.config.ElasticSearchConfig;
import com.example.elasticlog.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private ElasticSearchConfig client;

    public User getUserInfo(Long id) {
        log.info("=== service input value === " + id);

//        getDocument();

        return User.builder()
                .id(id)
                .name("seunghyun")
                .age(10)
                .build();
    }

//    public void getDocument() {
//        String jsonObject = "{\"trackingId\":test,\"message\":test}";
//
//        IndicesExistsResponse response = client.createClient().admin().indices().prepareExists("logs-2019-03-17").execute().actionGet();
//        System.out.println(response);
//
//    }

}

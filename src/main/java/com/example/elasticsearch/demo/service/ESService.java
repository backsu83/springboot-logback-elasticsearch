package com.example.elasticsearch.demo.service;

import com.example.elasticsearch.demo.dao.api.ESClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ESService {

    @Autowired
    private ESClient client;

    /**
     * 생성
     * @param index
     * @param type
     * @param data
     * @return
     */
    public int createDocument(String index , String type , String data) {
        return client.createDocument(index , type , data);
    }

    /**
     * 단건 조회
     * @param index
     * @param type
     * @param id
     * @return
     */
    public String getDocument(String index , String type , String id) {
        return client.getDocument(index , type , id);
    }

    /**
     * 검색
     * @param index
     * @param type
     * @return
     */
    public List<String> getDocuments(String index , String type) {
        return client.getDocuments(index , type);
    }

    /**
     * 수정
     * @param index
     * @param type
     * @param id
     * @param message
     * @return
     */
    public String setDocument(String index , String type , String id , String message) {
        return client.setDocument(index , type , id , message);
    }

    /**
     * 삭제
     * @param index
     * @param type
     * @param id
     * @return
     */
    public int delDocument(String index , String type , String id) {
        return client.delDocument(index , type , id );
    }
}

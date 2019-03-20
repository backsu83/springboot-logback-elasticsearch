package com.example.elasticsearch.demo.controller;

import com.example.elasticsearch.demo.service.ESService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ESController {

    @Autowired
    ESService ESService;

    @ApiOperation(value = "검색엔진 Document (row) 조회")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "index", paramType = "path", value = "database", required = true) ,
            @ApiImplicitParam(name = "type", paramType = "path", value = "table", required = true),
            @ApiImplicitParam(name = "id", paramType = "path", value = "row", required = true)
    })
    @GetMapping("/{index}/{type}/{id}")
    public ResponseEntity<String> getDocument(
            @PathVariable String index ,
            @PathVariable String type ,
            @PathVariable String id
            ) {
        log.info("=== controller document start === " );
        String result = ESService.getDocument(index , type ,id);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @ApiOperation(value = "검색엔진 Type (table) 조회")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "index", paramType = "path", value = "database", required = true) ,
            @ApiImplicitParam(name = "type", paramType = "path", value = "table", required = true)
    })
    @GetMapping("/{index}/{type}")
    public ResponseEntity<List> getDocuments(
            @PathVariable String index ,
            @PathVariable String type) {
        log.info("=== controller search start=== ");
        List<String> result = ESService.getDocuments(index , type);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @ApiOperation(value = "검색엔진 Document (row) 업데이트")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "index", paramType = "path", value = "database", required = true) ,
            @ApiImplicitParam(name = "type", paramType = "path", value = "table", required = true),
            @ApiImplicitParam(name = "id", paramType = "path", value = "row", required = true) ,
            @ApiImplicitParam(name = "message", paramType = "query", value = "filed", required = false)
    })
    @PutMapping("/{index}/{type}/{id}")
    public ResponseEntity<String> setDocument(
            @PathVariable String index ,
            @PathVariable String type,
            @PathVariable String id,
            @RequestParam String message) {
        log.info("=== controller update start=== ");
        String result = ESService.setDocument(index , type ,id , message);
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @ApiOperation(value = "검색엔진 Document (row) 삭제")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "index", paramType = "path", value = "database", required = true) ,
            @ApiImplicitParam(name = "type", paramType = "path", value = "table", required = true),
            @ApiImplicitParam(name = "id", paramType = "path", value = "row", required = true)
    })
    @DeleteMapping("/{index}/{type}/{id}")
    public ResponseEntity<Integer> delDocument(
            @PathVariable String index ,
            @PathVariable String type,
            @PathVariable String id) {
        log.info("=== controller delete start=== ");
        int result = ESService.delDocument(index , type ,id );
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @ApiOperation(value = "검색엔진 Document (row) 생성")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "index", paramType = "path", value = "database", required = true) ,
            @ApiImplicitParam(name = "type", paramType = "path", value = "table", required = true),
            @ApiImplicitParam(name = "data", paramType = "body", value = "json", required = true)
    })
    @PostMapping("/{index}/{type}")
    public ResponseEntity<Integer> createDocument(
            @PathVariable String index ,
            @PathVariable String type,
            @RequestBody String data) {
        log.info("=== controller delete start=== ");
        int result = ESService.createDocument(index , type , data );
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}

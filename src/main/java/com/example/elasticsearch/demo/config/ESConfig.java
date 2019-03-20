package com.example.elasticsearch.demo.config;

import com.example.elasticsearch.demo.exception.ESConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Configuration
public class ESConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    @Value("${elasticsearch.transport}")
    private int transport;


    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port, "http")));
        return client;
    }


    @Bean(destroyMethod = "close")
    public Client transClient() {

        Client client;
        Settings settings = Settings.builder()
                .put("cluster.name","docker-cluster")
                .put("client.transport.sniff", true)
                .build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), transport));

        } catch (UnknownHostException e) {
            throw new ESConnectionException(e.getMessage());
        }
        return client;
    }
}

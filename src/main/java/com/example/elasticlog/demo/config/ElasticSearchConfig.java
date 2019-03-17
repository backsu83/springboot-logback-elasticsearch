package com.example.elasticlog.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Configuration
public class ElasticSearchConfig {

    //private TransportClient client;

    @Bean
    public Client createClient() {

        Client client = null;
        Settings settings = Settings.builder()
                .put("cluster.name","docker-cluster")
                .put("client.transport.sniff", true)
                .put("node.name","4fjX_TQ")
                .put("transport.tcp.port", 9300)
                .put("network.server",true)
                .build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
            IndicesExistsResponse response = client.admin().indices().prepareExists("logs-2019-03-17").execute().actionGet();
            System.out.println(response);

        } catch (UnknownHostException e) {
            log.error("=== elastic connection error === " + e);
        }
        return client;
    }

}

package com.tuppertech.microservices.order.config;

import com.tuppertech.microservices.order.client.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventoryServiceURL;

    @Bean
    public InventoryClient inventoryClient(){
        RestClient client = RestClient.builder()
                .baseUrl(inventoryServiceURL)
                .requestFactory(getClientRequestFactory())
                .build();

        var restClientAdapter = RestClientAdapter.create(client);
        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }

    @Bean
    public ClientHttpRequestFactory getClientRequestFactory() {
        ClientHttpRequestFactorySettings factory = ClientHttpRequestFactorySettings.DEFAULTS
                .withConnectTimeout(Duration.ofSeconds(3))
                .withConnectTimeout(Duration.ofSeconds(3));
        return ClientHttpRequestFactories.get(factory);

    }
}

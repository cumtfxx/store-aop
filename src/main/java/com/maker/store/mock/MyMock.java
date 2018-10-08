package com.maker.store.mock;

import static  com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;


public class MyMock {
    public static void main(String[] args){
        configureFor(8888);
        removeAllMappings();
        stubFor(get(urlPathEqualTo("/goods/1")).willReturn(aResponse().withBody("{\"id\":1}").withStatus(200)));
        stubFor(get(urlPathEqualTo("/goods/test/1")).willReturn(aResponse().withBody("{\"test\":1}").withStatus(200)));
    }
}

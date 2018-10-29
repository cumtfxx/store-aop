package com.maker.store.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class MyMock {
    public static void main(String[] args){
        configureFor(8888);
        removeAllMappings();
        stubFor(get(urlPathEqualTo("/store/1")).willReturn(aResponse().withBody("{\"id\":1,\"name\":\"优品阁\"}").withStatus(200)));
        stubFor(post(urlPathEqualTo("/add")).willReturn(aResponse().withBody("增加成功").withStatus(200)));
        stubFor(delete(urlPathEqualTo("/delete")).willReturn(aResponse().withBody("删除成功").withStatus(200)));
    }
}

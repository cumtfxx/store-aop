package com.maker.store.mock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class MyMock {
    public static void main(String[] args){
        configureFor(8888);
        removeAllMappings();
        stubFor(get(urlPathEqualTo("/store/1")).willReturn(aResponse().withBody("{\"id\":1}").withStatus(200)));
        stubFor(post(urlPathEqualTo("/all")).willReturn(aResponse().withBody("{\"test\":1}").withStatus(200)));
    }
}

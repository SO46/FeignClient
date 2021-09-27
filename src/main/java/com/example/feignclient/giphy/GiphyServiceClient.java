package com.example.feignclient.giphy;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "giphy", url = "${giphy.link}")
public interface GiphyServiceClient {

    @GetMapping("?api_key={id}&q={tag}&limit=1&offset={offset}")
    JSONObject getGiphy(@PathVariable("id") String id, @PathVariable("tag") String currency, @PathVariable("offset") String offset);

}

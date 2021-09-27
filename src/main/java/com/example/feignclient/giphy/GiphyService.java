package com.example.feignclient.giphy;

import com.example.feignclient.rates.OpenExchangeRatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiphyService {

    private final OpenExchangeRatesService ratesService;

    private final GiphyServiceClient client;

    @Value("${giphy.values.id}")
    private String id;

    @Value("${giphy.values.firstTag}")
    private String firstTag;

    @Value("${giphy.values.secondTag}")
    private String secondTag;

    public byte[] isRich() {
        try {
            Document doc = Jsoup.connect(getGiphyUrl()).get();
            String content = doc.head().selectFirst("meta[content$=.gif]").attr("content");
            URL imageUrl = new URL(content);
            InputStream is = imageUrl.openStream();
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getGiphyUrl(){
        Integer rand = new Random().nextInt(4999);
        JSONObject giphy = client.getGiphy(id, (ratesService.checkRate()) ? firstTag : secondTag, rand.toString());
        Object data = ((List<String>) giphy.get("data")).get(0);
        Object images = ((LinkedHashMap) data).get("images");
        Object original = ((LinkedHashMap) images).get("original");
        return ((LinkedHashMap) original).get("url").toString();
    }
}

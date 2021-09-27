package com.example.feignclient.rates;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rates", url = "${rates.link}")
interface OpenExchangeRatesServiceClient {

	@GetMapping("/latest.json?app_id={id}&symbols={currency}")
	JSONObject getLatestCurrency(@PathVariable("id") String id, @PathVariable("currency") String currency);

	@GetMapping("/historical/{data}.json?app_id={id}&symbols={currency}")
	JSONObject getHistoricalCurrency(@PathVariable("data") String data, @PathVariable("id") String id, @PathVariable("currency") String currency);
}

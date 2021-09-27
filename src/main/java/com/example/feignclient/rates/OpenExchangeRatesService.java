package com.example.feignclient.rates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenExchangeRatesService {

	private final OpenExchangeRatesServiceClient client;

	@Value("${rates.values.id}")
	private String id;

	@Value("${rates.values.currency}")
	private String currency;

	public boolean checkRate() {
		log.info("Requesting OpenExchangeRatesService");

		double latest = getRate(client.getLatestCurrency(id, currency));

		String data = LocalDate.now().minusDays(1).toString();
		double history = getRate(client.getHistoricalCurrency(data, id, currency));

		return (latest - history) >= 0;
	}

	private double getRate(JSONObject jsonObject){
		return (double) ((LinkedHashMap) jsonObject.get("rates")).get(currency);
	}
}

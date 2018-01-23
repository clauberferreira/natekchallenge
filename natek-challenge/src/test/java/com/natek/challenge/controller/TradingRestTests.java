package com.natek.challenge.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natek.challenge.NatekChallengeApplication;
import com.natek.challenge.domain.Customer;
import com.natek.challenge.domain.LegalEntity;
import com.natek.challenge.domain.Trader;
import com.natek.challenge.domain.Trading;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NatekChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class TradingRestTests {

	private String createURLWithPort(String uri) {
		return "http://localhost:8080".concat(uri);
	}

	@Test
	public void emptyJson() {
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(" ");

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/trade"), HttpMethod.POST, entity,
				String.class);

		assertThat(response.getBody()).isEqualTo("You need to inform a json parameter");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void wrongJsonFormat() {
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(" {Clauber } ");

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/trade"), HttpMethod.POST, entity,
				String.class);

		assertThat(response.getBody()).isEqualTo("You need to inform a valid json parameter");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void valueDateLowerThanTradeDate() throws JsonProcessingException, Exception {
		Trading trading = new Trading();
		trading.setCustomer(new Customer("PLUTO1"));
		trading.setccyPair("EURUSD");
		trading.setType(Trading.Type.FORWARD);
		trading.setDirection(Trading.Direction.BUY);
		trading.setAmountFrom(1000000.00);
		trading.setAmountTo(1120000.00);
		trading.setRate(1.12);
		trading.setValueDate(LocalDate.of(2016, 10, 10));
		trading.setTradeDate(LocalDate.of(2016, 10, 11));
		trading.setLegalEntity(new LegalEntity("CS Zurich"));
		trading.setTrader(new Trader("Johann Baumfiddler"));
		ObjectMapper mapper = new ObjectMapper();

		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(trading));

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/trade"), HttpMethod.POST, entity,
				String.class);

		assertThat(response.getBody())
				.isEqualTo("There are/is 1 error message(s) in the console. Please check it out!");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void consistentTradinfJson() throws JsonProcessingException, Exception {
		Trading trading = new Trading();
		trading.setCustomer(new Customer("PLUTO1"));
		trading.setccyPair("EURUSD");
		trading.setType(Trading.Type.FORWARD);
		trading.setDirection(Trading.Direction.BUY);
		trading.setAmountFrom(1000000.00);
		trading.setAmountTo(1120000.00);
		trading.setRate(1.12);
		trading.setValueDate(LocalDate.of(2016, 10, 11));
		trading.setTradeDate(LocalDate.of(2016, 10, 11));
		trading.setLegalEntity(new LegalEntity("CS Zurich"));
		trading.setTrader(new Trader("Johann Baumfiddler"));
		ObjectMapper mapper = new ObjectMapper();

		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(trading));

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/trade"), HttpMethod.POST, entity,
				String.class);

		assertThat(response.getBody()).isEqualTo("It was successfully processed!");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}

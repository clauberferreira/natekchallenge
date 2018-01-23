package com.natek.challenge.controller;

import java.util.List;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natek.challenge.business.TradingBusiness;
import com.natek.challenge.domain.Trading;
import com.natek.challenge.exception.TradingException;

/**
 * 
 * Rest Trading API to validate one o multiple trading JSON
 * 
 * @author Clauber
 *
 */
@RestController
public class TradingRest {

	@Autowired
	private TradingBusiness tradingBusiness;

	private long amountOfErrors;

	/**
	 * Validate one o multiple trading json against some business rules. It returns
	 * a process message status on the response, however, all the validation
	 * messages are found in the console
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/trade")
	public ResponseEntity<String> trade(@RequestBody String json) {
		if (json == null || json.trim().isEmpty()) {
			return ResponseEntity.badRequest().body("You need to inform a json parameter");
		}

		ObjectMapper mapper = new ObjectMapper();

		amountOfErrors = 0;
		BiConsumer<Trading, Boolean> tradingConsumer = (Trading t, Boolean multi) -> {
			try {
				tradingBusiness.validateTrading(t);
			} catch (TradingException e) {
				if (multi) {
					System.out.println(e.getTrading());
				}
				e.getExceptions().forEach(System.out::println);
				amountOfErrors += e.getExceptions().size();
				System.out.println("");
			}
		};

		try {
			if (json.startsWith("[")) {
				List<Trading> trading = mapper.readValue(json.trim(),
						mapper.getTypeFactory().constructCollectionType(List.class, Trading.class));
				trading.forEach(t -> tradingConsumer.accept(t, trading.size() > 1));
			} else {
				Trading trading = mapper.readValue(json.trim(), Trading.class);
				tradingConsumer.accept(trading, false);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("You need to inform a valid json parameter");
		}
		if (amountOfErrors > 0) {
			return ResponseEntity.ok().body("There are/is ".concat(String.valueOf(amountOfErrors))
					.concat(" error message(s) in the console. Please check it out!"));
		}
		return ResponseEntity.ok().body("It was successfully processed!");
	}

}
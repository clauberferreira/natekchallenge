package com.natek.challenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle index.html
 * 
 * @author Clauber
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}

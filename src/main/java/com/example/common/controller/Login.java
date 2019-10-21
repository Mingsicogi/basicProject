package com.example.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/v1/basic")
@Slf4j
public class Login {

	@Value("${line.chanel.id}")
	private String lineChanelId;

	@Value("${line.me.login.url.format}")
	private String lineMeUrlFormat;

	@Value("${domain}")
	private String domain;

	@Value("${server.port}")
	private String port;

	/**
	 * line login
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String loginPage(Model model){

		String loginRedirectUrl = lineMeUrlFormat;

		loginRedirectUrl = String.format(loginRedirectUrl, lineChanelId, domain + ":" + port + "/v1/basic/welcome", Math.random());

		return "redirect:" + loginRedirectUrl;
	}

}

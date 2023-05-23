package com.conference.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.conference.entity.email.EmailDetail;
import com.conference.service.EmailDetailService;

@RestController
@CrossOrigin("*")
public class EmailDetailController {
	
	@Autowired
	private EmailDetailService emailDetailService;
	
	@PostMapping("/sendEmail")
	public String sendSimpleEmail( @RequestBody EmailDetail emailDetail ) {
		String sendEmail = emailDetailService.sendSimpleEmail( emailDetail );
		return sendEmail;
	}
	
	@PostMapping("/sendEmailWithAttachment")
	public String sendMailWithAttachment( @RequestBody EmailDetail emailDetail ) {
		String sendEmail = emailDetailService.sendMailWithAttachment( emailDetail );
		return sendEmail;
	}

}

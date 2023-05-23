package com.conference.service;

import com.conference.entity.email.EmailDetail;

public interface EmailDetailService {
	
	public String sendSimpleEmail( EmailDetail emailDetail );
	
	public String sendMailWithAttachment( EmailDetail emailDetail );
}

package com.conference.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.conference.entity.User;
import com.conference.entity.speaker.InvitedSpeaker;
import com.conference.repo.InvitedSpeakerRepository;
import com.conference.repo.UserRepository;
import com.conference.service.InvitedSpeakerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class InvitedSpeakerServiceImpl implements InvitedSpeakerService {
	
	@Autowired
	private InvitedSpeakerRepository invitedSpeakerRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;
	
//	public InvitedSpeaker saveDate(InvitedSpeaker invitedSpeaker) {	
//		return invitedSpeakerRepository.save(invitedSpeaker);
//	}

	@Override
	public InvitedSpeaker uploadFileToDB(MultipartFile file, String invitedSpeaker) throws IOException {
		
		// INVITED SPEAKER
		InvitedSpeaker invitedSpeakerJson = new InvitedSpeaker();
		
		invitedSpeakerJson = objectMapper.readValue( invitedSpeaker, InvitedSpeaker.class );
		invitedSpeakerJson.getArrivalDate();
		invitedSpeakerJson.getDepartureDate();
		invitedSpeakerJson.setFileData(file.getBytes());
		invitedSpeakerJson.setFileName(file.getOriginalFilename());
		invitedSpeakerJson.setFileType(file.getContentType());
		invitedSpeakerJson.getUserId();
		
		return invitedSpeakerRepository.save(invitedSpeakerJson);
	}
	
	@Override
	public InvitedSpeaker uploadFileToDB(MultipartFile file, String invitedSpeaker, Long userId) throws IOException {	
		//USER
		User userThatwasInDb = this.userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("User with this id was not in the db");
        });
		
		// INVITED SPEAKER
		InvitedSpeaker invitedSpeakerJson = new InvitedSpeaker();
		
		invitedSpeakerJson = objectMapper.readValue( invitedSpeaker, InvitedSpeaker.class );
		invitedSpeakerJson.getArrivalDate();
		invitedSpeakerJson.getDepartureDate();
		invitedSpeakerJson.setFileData(file.getBytes());
		invitedSpeakerJson.setFileName(file.getOriginalFilename());
		invitedSpeakerJson.setFileType(file.getContentType());
		
		invitedSpeakerJson.setUserId(userId);
		
		return invitedSpeakerRepository.save(invitedSpeakerJson);
	}
	
	//Getting All Invited Speakers
	@Override
	public List<InvitedSpeaker> getAllInvitedSpeaker() {		
		return this.invitedSpeakerRepository.findAll();
	}

	@Override
	public InvitedSpeaker findByFileName(String fileName) {
		InvitedSpeaker invitedSpeakerDownload = invitedSpeakerRepository.findByFileName(fileName);
		return invitedSpeakerDownload;
	}

}

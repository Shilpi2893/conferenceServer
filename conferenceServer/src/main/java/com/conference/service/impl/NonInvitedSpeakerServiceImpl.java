package com.conference.service.impl;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.conference.entity.User;
import com.conference.entity.speaker.NonInvitedSpeaker;
import com.conference.repo.NonInvitedSpeakerRepository;
import com.conference.repo.UserRepository;
import com.conference.service.NonInvitedSpeakerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NonInvitedSpeakerServiceImpl implements NonInvitedSpeakerService {
	
	@Autowired
	private NonInvitedSpeakerRepository nonInvitedSpeakerRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public NonInvitedSpeaker save(NonInvitedSpeaker nonInvitedSpeaker) {
		return nonInvitedSpeakerRepository.save(nonInvitedSpeaker);
	}

	@Override
	public NonInvitedSpeaker saveSpeakerWithFile(MultipartFile file, String nonInvitedSpeaker)throws IOException {
		
		NonInvitedSpeaker nonInvitedSpeakerJson = new NonInvitedSpeaker();
		
		nonInvitedSpeakerJson = objectMapper.readValue( nonInvitedSpeaker, NonInvitedSpeaker.class );
		nonInvitedSpeakerJson.getIsParticipation();
		nonInvitedSpeakerJson.getArrivalDate();
		nonInvitedSpeakerJson.getDepartureDate();
		nonInvitedSpeakerJson.getAccommodation();
		nonInvitedSpeakerJson.getIsAbstract();
		nonInvitedSpeakerJson.setFileAbstract(file.getBytes());
		nonInvitedSpeakerJson.setFileNameAbstract(file.getOriginalFilename());
		nonInvitedSpeakerJson.setFileType(file.getContentType());
		nonInvitedSpeakerJson.getIsPoster();
//		nonInvitedSpeakerJson.setPosterFile(file.getBytes());
//		nonInvitedSpeakerJson.setFileNamePoster(file.getOriginalFilename());
		nonInvitedSpeakerJson.getSession();
		
		return nonInvitedSpeakerRepository.save(nonInvitedSpeakerJson);
	}
	
	@Override
	public NonInvitedSpeaker saveWithFile(MultipartFile file, String nonInvitedSpeaker, Long userId)throws IOException {
		//USER
		User userThatwasInDb = this.userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("User with this id was not in the db");
        });
		
		//NON-INVITED-SPEAKER
		NonInvitedSpeaker nonInvitedSpeakerJson = new NonInvitedSpeaker();
		
		nonInvitedSpeakerJson = objectMapper.readValue( nonInvitedSpeaker, NonInvitedSpeaker.class );
		nonInvitedSpeakerJson.getIsParticipation();
		nonInvitedSpeakerJson.getArrivalDate();
		nonInvitedSpeakerJson.getDepartureDate();
		nonInvitedSpeakerJson.getAccommodation();
		nonInvitedSpeakerJson.getIsAbstract();
		nonInvitedSpeakerJson.setFileAbstract(file.getBytes());
		nonInvitedSpeakerJson.setFileNameAbstract(file.getOriginalFilename());
		nonInvitedSpeakerJson.setFileType(file.getContentType());
		nonInvitedSpeakerJson.getIsPoster();
		nonInvitedSpeakerJson.getSession();
		
		nonInvitedSpeakerJson.setUserId(userId);
		
		return nonInvitedSpeakerRepository.save(nonInvitedSpeakerJson);
	}

	@Override
	public List<NonInvitedSpeaker> getAllNonInvitedSpeaker() {
		return this.nonInvitedSpeakerRepository.findAll();
	}

	@Override
	public NonInvitedSpeaker findByFileNameAbstract(String fileNameAbstract) {
		NonInvitedSpeaker nonInvitedSpeakerDownload = nonInvitedSpeakerRepository.findByFileNameAbstract(fileNameAbstract);
		return nonInvitedSpeakerDownload;
	}

	@Override
	public NonInvitedSpeaker saveSpeakerWithUser(String nonInvitedSpeaker, Long userId) throws IOException {
		//USER
		User userThatwasInDb = this.userRepository.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException("User with this id was not in the db");
        });
		
		//NON-INVITED SPEAKER
		NonInvitedSpeaker nonInvitedSpeakerUser = new NonInvitedSpeaker();
		
		nonInvitedSpeakerUser = objectMapper.readValue(nonInvitedSpeaker, NonInvitedSpeaker.class);
		
		nonInvitedSpeakerUser.getIsParticipation();
		nonInvitedSpeakerUser.getArrivalDate();
		nonInvitedSpeakerUser.getDepartureDate();
		nonInvitedSpeakerUser.getAccommodation();
		nonInvitedSpeakerUser.getFileAbstract();
		nonInvitedSpeakerUser.getFileNameAbstract();
		nonInvitedSpeakerUser.getFileType();
		nonInvitedSpeakerUser.getIsAbstract();
		nonInvitedSpeakerUser.getIsPoster();
		nonInvitedSpeakerUser.getSession();
		
		nonInvitedSpeakerUser.setUserId(userId);
		
		return nonInvitedSpeakerRepository.save(nonInvitedSpeakerUser);
	}

}

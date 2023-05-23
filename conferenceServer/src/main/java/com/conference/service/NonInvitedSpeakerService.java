package com.conference.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.conference.entity.speaker.NonInvitedSpeaker;

public interface NonInvitedSpeakerService {
	
	//saving non invited speaker choice
	public NonInvitedSpeaker save(NonInvitedSpeaker nonInvitedSpeaker);
	
	//saving non invited speaker choice linking with user
	public NonInvitedSpeaker saveSpeakerWithUser( String nonInvitedSpeaker, Long userId ) throws IOException;
	
	//saving non invited speaker choice with file(uploading file)
	public NonInvitedSpeaker saveSpeakerWithFile( MultipartFile file, String nonInvitedSpeaker) throws IOException;
	
	//Get all non-invited-speaker
	public List<NonInvitedSpeaker> getAllNonInvitedSpeaker();
	
	// save non-invited-speaker with user
	public NonInvitedSpeaker saveWithFile(MultipartFile file, String nonInvitedSpeaker, Long userId) throws IOException;
	
	// get file name
	public NonInvitedSpeaker findByFileNameAbstract( String fileNameAbstract );

}

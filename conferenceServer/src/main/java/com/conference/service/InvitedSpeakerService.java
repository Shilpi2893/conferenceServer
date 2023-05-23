package com.conference.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.conference.entity.speaker.InvitedSpeaker;

public interface InvitedSpeakerService {
	
	//saving invited speaker with file(uploading file)
	public InvitedSpeaker uploadFileToDB ( MultipartFile file, String invitedSpeaker ) throws IOException, Exception;
	
	//get all invited speaker
	public List<InvitedSpeaker> getAllInvitedSpeaker();
	
	//saving invited speaker with userId and file(uploading file)
	public InvitedSpeaker uploadFileToDB(MultipartFile file, String invitedSpeaker, Long userId) throws IOException;
	
	//get file name
	public InvitedSpeaker findByFileName( String fileName );
}

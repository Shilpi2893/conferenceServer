package com.conference.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.conference.entity.speaker.InvitedSpeaker;
import com.conference.service.InvitedSpeakerService;
import com.conference.service.impl.InvitedSpeakerServiceImpl;

@RestController
@RequestMapping("/invited-speaker")
@CrossOrigin("*")
public class InvitedSpeakerController {
	
	@Autowired
	private InvitedSpeakerServiceImpl invitedSpeakerServiceImpl;
	
	@Autowired
	private InvitedSpeakerService invitedSpeakerService;
	
//	@PostMapping("/")
//	public InvitedSpeaker saveDate( @RequestBody InvitedSpeaker invitedSpeaker) {
//		System.out.println(invitedSpeaker);
//		return invitedSpeakerServiceImpl.saveDate(invitedSpeaker);
//	}
	
	@PostMapping( path = "/multipart", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, 
			MediaType.APPLICATION_JSON_VALUE } )
	public InvitedSpeaker uploadFileToDB( @RequestParam("file") MultipartFile file, @RequestParam("jsonData") String jsonData) 
			throws IOException, Exception {
		
		InvitedSpeaker invitedSpeaker = new InvitedSpeaker();
		
		return invitedSpeakerServiceImpl.uploadFileToDB(file, jsonData);
	}
	
	@PostMapping( path = "/multipart1", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, 
			MediaType.APPLICATION_JSON_VALUE } )
	public InvitedSpeaker uploadFileToDB1( @RequestParam("file") MultipartFile file, @RequestParam("jsonData") String jsonData, 
			@RequestParam("userId") Long userId ) 
			throws IOException, Exception {
		
		return invitedSpeakerServiceImpl.uploadFileToDB(file, jsonData, userId);
	}
	
	//Getting all invited speaker
	@GetMapping("/")
	public List<InvitedSpeaker> getAllInvitedSpeaker() {
		return this.invitedSpeakerServiceImpl.getAllInvitedSpeaker();
	}
	
	//For downloading file
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Resource> downloadFile( @PathVariable String fileName ) {
		InvitedSpeaker invitedSpeakerToRel = invitedSpeakerServiceImpl.findByFileName(fileName);
		return ResponseEntity.ok()
				.contentType( MediaType.parseMediaType( invitedSpeakerToRel.getFileType() ) )
				.header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + invitedSpeakerToRel.getFileName() )
				.body( new ByteArrayResource( invitedSpeakerToRel.getFileData() ) );
	}

}

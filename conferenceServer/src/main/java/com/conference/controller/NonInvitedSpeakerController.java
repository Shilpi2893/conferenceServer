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

import com.conference.entity.speaker.NonInvitedSpeaker;
import com.conference.entity.speaker.Student;
import com.conference.service.NonInvitedSpeakerService;

@RestController
@RequestMapping("/non-invited")
@CrossOrigin("*")
public class NonInvitedSpeakerController {
	
	@Autowired
	private NonInvitedSpeakerService nonInvitedSpeakerService;
	
	@PostMapping("/")
	public NonInvitedSpeaker save( @RequestBody NonInvitedSpeaker nonInvitedSpeaker) {
		return this.nonInvitedSpeakerService.save(nonInvitedSpeaker);
	}
	
	@PostMapping( path = "/multipart", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, 
			MediaType.APPLICATION_JSON_VALUE } )
	public NonInvitedSpeaker uploadFileToDB( @RequestParam("file") MultipartFile file, @RequestParam("jsonData") String jsonData ) throws IOException {
		
		NonInvitedSpeaker nonInvitedSpeaker = new NonInvitedSpeaker();
		
		return nonInvitedSpeakerService.saveSpeakerWithFile(file, jsonData);
	}
	
	@PostMapping( path = "/multipart1", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, 
			MediaType.APPLICATION_JSON_VALUE } )
	public NonInvitedSpeaker uploadFileToDB1( @RequestParam("file") MultipartFile file, @RequestParam("jsonData") String jsonData,
			@RequestParam("userId") Long userId) throws IOException {	
		
		return nonInvitedSpeakerService.saveWithFile(file, jsonData, userId);
	}
	
	//Getting all non-invited speaker
	@GetMapping("/")
	public List<NonInvitedSpeaker> getAllNonInvitedSpeaker() {
		return this.nonInvitedSpeakerService.getAllNonInvitedSpeaker();
	}
	
	//For downloading file
	@GetMapping("/download/{fileNameAbstract}")
	public ResponseEntity<Resource> dowmload( @PathVariable String fileNameAbstract ) {
		NonInvitedSpeaker nonInvitedSpeakerToRel = nonInvitedSpeakerService.findByFileNameAbstract(fileNameAbstract);
		return ResponseEntity.ok()
				.contentType( MediaType.parseMediaType( nonInvitedSpeakerToRel.getFileType() ) )
				.header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + nonInvitedSpeakerToRel.getFileNameAbstract() )
				.body( new ByteArrayResource( nonInvitedSpeakerToRel.getFileAbstract() ) );
	}
	
	@PostMapping("/{userId}")
	public NonInvitedSpeaker saveNonInvitedSpeaker( @RequestBody String nonInvitedSpeaker, @PathVariable("userId") Long userId ) throws IOException {
		return nonInvitedSpeakerService.saveSpeakerWithUser(nonInvitedSpeaker, userId);
	}

}

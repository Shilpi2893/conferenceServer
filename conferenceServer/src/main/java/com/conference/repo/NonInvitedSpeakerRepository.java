package com.conference.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conference.entity.speaker.NonInvitedSpeaker;

public interface NonInvitedSpeakerRepository extends JpaRepository<NonInvitedSpeaker, Long> {
	
	//get file name
	public NonInvitedSpeaker findByFileNameAbstract(String fileNameAbstract);
}

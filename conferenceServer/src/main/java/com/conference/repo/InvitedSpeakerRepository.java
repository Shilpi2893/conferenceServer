package com.conference.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conference.entity.speaker.InvitedSpeaker;

public interface InvitedSpeakerRepository extends JpaRepository<InvitedSpeaker, Long> {
	
//	@Query("select distinct i from InvitedSpeaker i left join fetch i.user")
//	public List<InvitedSpeaker> findAllInvited();
	
	public InvitedSpeaker findByFileName( String fileName );

}

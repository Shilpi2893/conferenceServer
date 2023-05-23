package com.conference.entity.speaker;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conference.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "non_invited_speaker")
public class NonInvitedSpeaker {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long nonInvitedId;
	
	private String isParticipation;
	
	@JsonFormat(pattern = "MM-DD-YYYY", shape = Shape.STRING)
	private String arrivalDate;
	
	@JsonFormat(pattern = "MM-DD-YYYY", shape = Shape.STRING)
	private String departureDate;
	
	private String accommodation;
	
	private String isAbstract;
	
	@Lob
	private byte[] fileAbstract;
	
	private String fileNameAbstract;
	
	private String fileType;
	
	private String isPoster;
	
//	@Lob
//	private byte[] posterFile;
//	
//	private String fileNamePoster;
	
	private String session;
	
	@Column( name = "user_non_invited_id")
	private long userId;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn( name = "user_non_invited_id", referencedColumnName = "id", insertable = false, updatable = false )
    private User user;

	public NonInvitedSpeaker() {}

	public void setNonInvitedId(long nonInvitedId) {
		this.nonInvitedId = nonInvitedId;
	}

	public Long getNonInvitedId() {
		return nonInvitedId;
	}

	public void setNonInvitedId(Long nonInvitedId) {
		this.nonInvitedId = nonInvitedId;
	}

	public String getIsParticipation() {
		return isParticipation;
	}

	public void setIsParticipation(String isParticipation) {
		this.isParticipation = isParticipation;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public String getIsAbstract() {
		return isAbstract;
	}

	public void setIsAbstract(String isAbstract) {
		this.isAbstract = isAbstract;
	}

	public byte[] getFileAbstract() {
		return fileAbstract;
	}

	public void setFileAbstract(byte[] fileAbstract) {
		this.fileAbstract = fileAbstract;
	}
	
	public void setFileAbstract(String fileAbstract) {
		this.fileAbstract = fileAbstract.getBytes();
	}

	public String getFileNameAbstract() {
		return fileNameAbstract;
	}

	public void setFileNameAbstract(String fileNameAbstract) {
		this.fileNameAbstract = fileNameAbstract;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getIsPoster() {
		return isPoster;
	}

	public void setIsPoster(String isPoster) {
		this.isPoster = isPoster;
	}

//	public byte[] getPosterFile() {
//		return posterFile;
//	}
//
//	public void setPosterFile(byte[] posterFile) {
//		this.posterFile = posterFile;
//	}
//	
//	public void setPosterFile(String posterFile) {
//		this.posterFile = posterFile.getBytes();
//	}
//
//	public String getFileNamePoster() {
//		return fileNamePoster;
//	}
//
//	public void setFileNamePoster(String fileNamePoster) {
//		this.fileNamePoster = fileNamePoster;
//	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

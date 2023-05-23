package com.conference.entity.speaker;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "invited_speaker")
public class InvitedSpeaker {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long invitedId;
	
	@JsonFormat(pattern="MM-DD-YYYY", shape = Shape.STRING)
	private String arrivalDate;
	
	@JsonFormat(pattern="MM-DD-YYYY", shape = Shape.STRING)
	private String departureDate;
	
	private String fileName;
	
	private String fileType;
	
	@Lob
	private byte[] fileData;
	
	@Column( name = "user_invited_id")
	private long userId;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn( name = "user_invited_id", referencedColumnName = "id", insertable = false, updatable = false )
    private User user;

	public InvitedSpeaker() {}

	public long getInvitedId() {
		return invitedId;
	}

	public void setInvitedId(long invitedId) {
		this.invitedId = invitedId;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	public void setFileData(String fileData) {
		this.fileData = fileData.getBytes();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

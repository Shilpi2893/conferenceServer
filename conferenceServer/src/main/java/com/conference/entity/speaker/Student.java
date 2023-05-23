package com.conference.entity.speaker;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.conference.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private long studentId;
	
	private String isParticipation;
	
	@JsonFormat(pattern = "MM-DD-YYYY", shape = Shape.STRING)
	private String arrivalDate;
	
	@JsonFormat(pattern = "MM-DD-YYYY", shape = Shape.STRING)
	private String departureDate;
	
	private String isFinancialNeed;
	
	private String isAccomodation;
	
	private String accomodation;
	
	private String poster;
	
	private String session;
	
	@Column( name = "user_student_id")
	private long userId;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn( name = "user_student_id", referencedColumnName = "id", insertable = false, updatable = false )
    private User user;
	
	public Student() {}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
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

	public String getIsFinancialNeed() {
		return isFinancialNeed;
	}

	public void setIsFinancialNeed(String isFinancialNeed) {
		this.isFinancialNeed = isFinancialNeed;
	}

	public String getIsAccomodation() {
		return isAccomodation;
	}

	public void setIsAccomodation(String isAccomodation) {
		this.isAccomodation = isAccomodation;
	}

	public String getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

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

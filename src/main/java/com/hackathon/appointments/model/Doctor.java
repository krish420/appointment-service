package com.hackathon.appointments.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Doctor {
    @JsonProperty("Doctor Id")
	@JsonInclude(Include.NON_NULL)
	private String doctorId;
	
	@JsonProperty("Doctor Name")
	@JsonInclude(Include.NON_NULL)
	private String doctorName;
	
	@JsonProperty("Specialist")
	@JsonInclude(Include.NON_NULL)
	private String specialization;
	
	@JsonProperty("Gender")
	@JsonInclude(Include.NON_NULL)
	private String gender;
}

package TRMS.pojos;

import java.time.LocalDate;

public class WebTraining {
	
	private String email;
	private String description;
	private LocalDate date;
	private String location;
	private double cost;
	private String gradeType;
	private String trainingType;
	private String justification;
	
	public WebTraining() {
		super();
	}

	

	public WebTraining(String email, String description, LocalDate date, String location, double cost, String gradeType,
			String trainingType, String justification) {
		super();
		this.email = email;
		this.description = description;
		this.date = date;
		this.location = location;
		this.cost = cost;
		this.gradeType = gradeType;
		this.trainingType = trainingType;
		this.justification = justification;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}



	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

}

package TRMS.pojos;

import java.time.LocalDate;

public class Training {
	
	private long trainingId;
	private long employeeId;
	private double cost;
	private LocalDate date;
	private long gradingFormatId;
	private long trainingTypeId;
	private int trainingStatus;
	private TrainingInfo trainingInfo;
	private double grade;
	
	public Training() {
		super();
	}

	public Training(long trainingId, long employeeId) {
		super();
		this.trainingId = trainingId;
		this.employeeId = employeeId;
	}

	public Training(long employeeId, double cost, LocalDate date, long gradingFormatId, long trainingTypeId,
			int trainingStatus, TrainingInfo trainingInfo) {
		super();
		this.employeeId = employeeId;
		this.cost = cost;
		this.date = date;
		this.gradingFormatId = gradingFormatId;
		this.trainingTypeId = trainingTypeId;
		this.trainingStatus = trainingStatus;
		this.trainingInfo = trainingInfo;
	}

	public Training(long employeeId, double cost, LocalDate date, long gradingFormatId, long trainingTypeId,
			int trainingStatus, TrainingInfo trainingInfo, double grade) {
		super();
		this.employeeId = employeeId;
		this.cost = cost;
		this.date = date;
		this.gradingFormatId = gradingFormatId;
		this.trainingTypeId = trainingTypeId;
		this.trainingStatus = trainingStatus;
		this.trainingInfo = trainingInfo;
		this.grade = grade;
	}

	public Training(long trainingId, long employeeId, double cost, LocalDate date, long gradingFormatId,
			long trainingTypeId, int trainingStatus) {
		super();
		this.trainingId = trainingId;
		this.employeeId = employeeId;
		this.cost = cost;
		this.date = date;
		this.gradingFormatId = gradingFormatId;
		this.trainingTypeId = trainingTypeId;
		this.trainingStatus = trainingStatus;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getGradingFormatId() {
		return gradingFormatId;
	}

	public void setGradingFormatId(long gradingFormatId) {
		this.gradingFormatId = gradingFormatId;
	}

	public long getTrainingTypeId() {
		return trainingTypeId;
	}

	public void setTrainingTypeId(long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public int getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(int trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public TrainingInfo getTrainingInfo() {
		return trainingInfo;
	}

	public void setTrainingInfo(TrainingInfo trainingInfo) {
		this.trainingInfo = trainingInfo;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
		temp = Double.doubleToLongBits(grade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (gradingFormatId ^ (gradingFormatId >>> 32));
		result = prime * result + (int) (trainingId ^ (trainingId >>> 32));
		result = prime * result + ((trainingInfo == null) ? 0 : trainingInfo.hashCode());
		result = prime * result + trainingStatus;
		result = prime * result + (int) (trainingTypeId ^ (trainingTypeId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Training other = (Training) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (Double.doubleToLongBits(grade) != Double.doubleToLongBits(other.grade))
			return false;
		if (gradingFormatId != other.gradingFormatId)
			return false;
		if (trainingId != other.trainingId)
			return false;
		if (trainingInfo == null) {
			if (other.trainingInfo != null)
				return false;
		} else if (!trainingInfo.equals(other.trainingInfo))
			return false;
		if (trainingStatus != other.trainingStatus)
			return false;
		if (trainingTypeId != other.trainingTypeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Training [trainingId=" + trainingId + ", employeeId=" + employeeId + ", cost=" + cost + ", date=" + date
				+ ", gradingFormatId=" + gradingFormatId + ", trainingTypeId=" + trainingTypeId + ", trainingStatus="
				+ trainingStatus + ", trainingInfo=" + trainingInfo + ", grade=" + grade + "]";
	}

	
}

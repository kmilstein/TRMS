package TRMS.pojos;

import java.time.LocalTime;

public class TrainingInfo {
	private long trainingId;
	private String description;
	private LocalTime time;
	private String location;
	private String justification;
	
	public TrainingInfo() {
		super();
	}

	public TrainingInfo(long trainingId, String description, LocalTime time, String location, String justification) {
		super();
		this.trainingId = trainingId;
		this.description = description;
		this.time = time;
		this.location = location;
		this.justification = justification;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + (int) (trainingId ^ (trainingId >>> 32));
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
		TrainingInfo other = (TrainingInfo) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (trainingId != other.trainingId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingInfo [trainingId=" + trainingId + ", description=" + description + ", time=" + time
				+ ", location=" + location + ", justification=" + justification + "]";
	}
}

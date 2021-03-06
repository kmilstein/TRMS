package TRMS.pojos;

public class TrainingType {
	
	private long trainingTypeId;
	private String name;
	private double percentCovered;
	
	public TrainingType() {
		super();
	}

	public TrainingType(long trainingTypeId, String name, double percentCovered) {
		super();
		this.trainingTypeId = trainingTypeId;
		this.name = name;
		this.percentCovered = percentCovered;
	}

	public long getTrainingTypeId() {
		return trainingTypeId;
	}

	public void setTrainingTypeId(long trainingTypeId) {
		this.trainingTypeId = trainingTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPercentCovered() {
		return percentCovered;
	}

	public void setPercentCovered(double percentCovered) {
		this.percentCovered = percentCovered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(percentCovered);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		TrainingType other = (TrainingType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(percentCovered) != Double.doubleToLongBits(other.percentCovered))
			return false;
		if (trainingTypeId != other.trainingTypeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingType [trainingTypeId=" + trainingTypeId + ", name=" + name + ", percentCovered="
				+ percentCovered + "]";
	}
}

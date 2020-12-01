package TRMS.pojos;

public class GradingFormat {
	
	private long gradingFormatId;
	private String name;
	private int minimumPassingScore;
	
	public GradingFormat() {
		super();
	}
	
	public GradingFormat(long gradingFormatId, String name, int minimumPassingScore) {
		super();
		this.gradingFormatId = gradingFormatId;
		this.name = name;
		this.minimumPassingScore = minimumPassingScore;
	}
	
	public long getGradingFormatId() {
		return gradingFormatId;
	}
	
	public void setGradingFormatId(long gradingFormatId) {
		this.gradingFormatId = gradingFormatId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMinimumPassingScore() {
		return minimumPassingScore;
	}
	
	public void setMinimumPassingScore(int minimumPassingScore) {
		this.minimumPassingScore = minimumPassingScore;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (gradingFormatId ^ (gradingFormatId >>> 32));
		result = prime * result + minimumPassingScore;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		GradingFormat other = (GradingFormat) obj;
		if (gradingFormatId != other.gradingFormatId)
			return false;
		if (minimumPassingScore != other.minimumPassingScore)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "GradingFormat [gradingFormatId=" + gradingFormatId + ", name=" + name + ", minimumPassingScore="
				+ minimumPassingScore + "]";
	}
}

package TRMS.dao;

import java.io.File;

public class Attachment {
	
	private long attachmentId;
	private File file;
	private long trainingId;
	public Attachment() {
		super();
	}
	
	public Attachment(long attachmentId, File file, long trainingId) {
		super();
		this.attachmentId = attachmentId;
		this.file = file;
		this.trainingId = trainingId;
	}

	public long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (attachmentId ^ (attachmentId >>> 32));
		result = prime * result + ((file == null) ? 0 : file.hashCode());
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
		Attachment other = (Attachment) obj;
		if (attachmentId != other.attachmentId)
			return false;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (trainingId != other.trainingId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentId=" + attachmentId + ", file=" + file + ", trainingId=" + trainingId + "]";
	}
}

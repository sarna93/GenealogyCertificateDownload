package pl.polsl.inz.gcd.models;

public class DownloadForm {
	private String linkToCertificate;
	private String downloadType;
	private String folderPath;

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getLinkToCertificate() {
		return linkToCertificate;
	}

	public void setLinkToCertificate(String linkToCertificate) {
		this.linkToCertificate = linkToCertificate;
	}

	public String getDownloadType() {
		return downloadType;
	}

	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
}

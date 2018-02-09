package pl.polsl.inz.gcd.models;

public class DownloadForm {
	private String linkToCertificate;
	private String downloadType;
	
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

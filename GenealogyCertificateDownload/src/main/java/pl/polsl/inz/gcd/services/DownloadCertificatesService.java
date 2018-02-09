package pl.polsl.inz.gcd.services;

public interface DownloadCertificatesService {
	public void downloadCertificate(String urlToCertificatePage);
	public void downloadCertificatesBook(String urlToCertificatePage);
	public void downloadSingleImage(String urlToCertificatePage);
	public void changeImageName(String urlToCertificateDownload);
	public String getUrlToNextCertificatePage(String urlToCertificatePage);
	public String getImageFileName();
}

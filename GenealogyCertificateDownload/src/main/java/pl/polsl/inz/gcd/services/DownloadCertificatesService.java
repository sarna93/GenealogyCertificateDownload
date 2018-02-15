package pl.polsl.inz.gcd.services;

public interface DownloadCertificatesService {
	public boolean downloadCertificate(String urlToCertificatePage, String folderPath);
	public boolean downloadCertificatesBook(String urlToCertificatePage, String folderPath);
	public boolean downloadSingleImage(String urlToCertificateDownload, String folderPath);
	public String getUrlToNextCertificatePage(String urlToCertificatePage);
	public String getImageFileName();
}

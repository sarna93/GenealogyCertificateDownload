package pl.polsl.inz.gcd.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.polsl.inz.gcd.services.DownloadCertificatesService;

@Service("fsService")
@Qualifier("fsService")
public class FSDownloadCertificatesService implements DownloadCertificatesService {

	@Override
	public void downloadCertificate(String urlToCertificatePage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadCertificatesBook(String urlToCertificatePage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadSingleImage(String urlToCertificatePage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeImageName(String urlToCertificateDownload) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUrlToNextCertificatePage(String urlToCertificatePage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImageFileName() {
		// TODO Auto-generated method stub
		return null;
	}


}

package pl.polsl.inz.gcd.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.polsl.inz.gcd.models.FSNavigationFromJSON;
import pl.polsl.inz.gcd.services.DownloadCertificatesService;

@Service("fsService")
@Qualifier("fsService")
public class FSDownloadCertificatesService implements DownloadCertificatesService {

	private String imageFileName;
	private List<String> imagesFileNames;

	@Override
	public boolean downloadCertificate(String urlToCertificatePage, String folderPath) {
		RestTemplate restTemplate = new RestTemplate();
		FSNavigationFromJSON quote = restTemplate.getForObject(urlToCertificatePage, FSNavigationFromJSON.class);
		String urlToCertificateDownload = quote.getURL();
		setImageFileName(urlToCertificateDownload, Integer.toString(quote.getCurrent()));
		return downloadSingleImage(urlToCertificateDownload, folderPath);
	}

	@Override
	public boolean downloadCertificatesBook(String urlToCertificatePage, String folderPath) {
		imagesFileNames = new ArrayList<>();
		boolean downloadSuccess = true;

		RestTemplate restTemplate = new RestTemplate();
		FSNavigationFromJSON quote = restTemplate.getForObject(urlToCertificatePage, FSNavigationFromJSON.class);

		int countOfScans = quote.getCount();
		for (int i = 0; i < countOfScans; i++) {
			quote = restTemplate.getForObject(urlToCertificatePage, FSNavigationFromJSON.class);
			String urlToCertificateDownload = quote.getURL();
			System.out.println(urlToCertificateDownload);
			setImageFileName(urlToCertificateDownload, Integer.toString(quote.getCurrent()));
			imagesFileNames.add(imageFileName);
			downloadSuccess = downloadSingleImage(urlToCertificateDownload, folderPath);
			urlToCertificatePage = quote.getNext();
		}

		imagesFileNames.clear();
		return downloadSuccess;
	}

	@Override
	public boolean downloadSingleImage(String urlToCertificateDownload, String folderPath) {
		try {
			URL url = new URL(urlToCertificateDownload);
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
			byte[] response = out.toByteArray();

			FileOutputStream fos = new FileOutputStream(folderPath + File.separator + imageFileName);
			fos.write(response);
			fos.close();
			out.close();
			in.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String getUrlToNextCertificatePage(String urlToCertificatePage) {
		return null;
	}

	@Override
	public String getImageFileName() {
		return imageFileName;
	}

	private void setImageFileName(String urlToCertificatePage, String scanNumber) {
		int index;
		if ((index = urlToCertificatePage.lastIndexOf("?")) != -1) {
			urlToCertificatePage = urlToCertificatePage.substring(0, index);
		}

		index = urlToCertificatePage.lastIndexOf("/");
		String certificateIndex = urlToCertificatePage.substring(index + 1, urlToCertificatePage.length());

		imageFileName = scanNumber + "_" + certificateIndex + ".jpg";
		System.out.println("skan: " + scanNumber);
	}

}

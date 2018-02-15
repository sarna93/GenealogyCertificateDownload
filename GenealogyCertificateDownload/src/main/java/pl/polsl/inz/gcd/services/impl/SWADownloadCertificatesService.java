package pl.polsl.inz.gcd.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.polsl.inz.gcd.services.DownloadCertificatesService;

@Service("swaService")
@Qualifier("swaService")
public class SWADownloadCertificatesService implements DownloadCertificatesService {

	private String imageFileName;
	private List<String> imagesFileNames;

	private void setImageFileName(String urlToCertificatePage) {
		String scanNumber = "";
		try {
			Connection connection = Jsoup.connect(urlToCertificatePage);
			connection.header("Accept-Language", "en");

			Document docHtml = connection.get();
			Element elementWithScanNumber = docHtml.getElementsContainingText(".jpg").first();
			String textInlementWithScanNumber = elementWithScanNumber.text();
			int firstIndex = textInlementWithScanNumber.lastIndexOf("scan:");
			firstIndex += "scan:".length();
			int lastIndex = textInlementWithScanNumber.lastIndexOf(".jpg");
			scanNumber = textInlementWithScanNumber.substring(firstIndex, lastIndex);
			scanNumber = scanNumber.replaceAll("\\s", "");
			System.out.println("skan: " + scanNumber);
		} catch (IOException e) {

		}

		int firstIndex = urlToCertificatePage.lastIndexOf("/") + 1;
		String certificateIndex = urlToCertificatePage.substring(firstIndex, urlToCertificatePage.length());

		this.imageFileName = scanNumber + "_" + certificateIndex + ".jpg";
	}

	@Override
	public String getImageFileName() {
		return imageFileName;
	}

	@Override
	public boolean downloadCertificate(String urlToCertificatePage, String folderPath) {
		String urlToCertificateDownload = urlToCertificatePage.replace("full", "save");
		setImageFileName(urlToCertificatePage);
		return downloadSingleImage(urlToCertificateDownload, folderPath);
	}

	@Override
	public boolean downloadCertificatesBook(String urlToCertificatePage, String folderPath) {
		imagesFileNames = new ArrayList<>();

		boolean downloadSuccess = true;
		while (urlToCertificatePage != null) {
			System.out.println(urlToCertificatePage);
			String urlToCertificateDownload = urlToCertificatePage.replace("full", "save");
			setImageFileName(urlToCertificatePage);
			imagesFileNames.add(imageFileName);
			downloadSuccess = downloadSingleImage(urlToCertificateDownload, folderPath);
			urlToCertificatePage = getUrlToNextCertificatePage(urlToCertificatePage);
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
		String linkToNextImage = "";
		try {
			Connection connection = Jsoup.connect(urlToCertificatePage);
			connection.header("Accept-Language", "en");

			Document docHtml = connection.get();
			Elements links = docHtml.getElementsByAttribute("href");

			Element elementWithSearchedLink = null;
			for (Element link : links) {
				elementWithSearchedLink = link.getElementsContainingText("Next").first();
				if (elementWithSearchedLink != null) {
					break;
				}
			}
			if (elementWithSearchedLink == null) {
				return null;
			}
			linkToNextImage = "http://szukajwarchiwach.pl" + elementWithSearchedLink.attr("href");
		} catch (IOException e) {
		}

		return linkToNextImage;
	}

}

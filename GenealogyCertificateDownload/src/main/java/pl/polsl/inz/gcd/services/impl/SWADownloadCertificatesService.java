package pl.polsl.inz.gcd.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.polsl.inz.gcd.services.DownloadCertificatesService;

@Service("swaService")
@Qualifier("swaService")
public class SWADownloadCertificatesService implements DownloadCertificatesService {

	@Autowired
	private ServletContext servletContext;
	private String imageFileName;
	
	private void setImageFileName(String urlToCertificatePage, String urlToCertificateDownload) {
		String scanNumber = "";
		try {
		Connection connection = Jsoup.connect(urlToCertificatePage);
		connection.header("Accept-Language", "en");

		Document docHtml = connection.get();
		//Elements scanNumber = docHtml.getElementsByAttribute("p");
		
		Element elementWithScanNumber = docHtml.getElementsContainingText(".jpg").first();
		String textInlementWithScanNumber = elementWithScanNumber.text();
		int firstIndex = textInlementWithScanNumber.lastIndexOf("scan:");
		firstIndex += "scan:".length();
		int lastIndex = textInlementWithScanNumber.lastIndexOf(".jpg");
		scanNumber = textInlementWithScanNumber.substring(firstIndex, lastIndex);
		scanNumber = scanNumber.replaceAll("\\s", "");
		System.out.println("skan: " + scanNumber);
		}
		catch(IOException e) {
			
		}
		
		int firstIndex = urlToCertificatePage.lastIndexOf("/")  + 1;
		String certificateIndex = urlToCertificatePage.substring(firstIndex, urlToCertificatePage.length());
		
		this.imageFileName = scanNumber + "_" + certificateIndex + ".jpg";
	}
	
	@Override
	public String getImageFileName() {
		return imageFileName;
	}

	@Override
	public void downloadCertificate(String urlToCertificatePage) {
		downloadSingleImage(urlToCertificatePage);
	}

	@Override
	public void downloadCertificatesBook(String urlToCertificatePage) {
			downloadSingleImage(urlToCertificatePage);
		System.out.println("\n\n\n\n\n\n\n\n\n");
		while(urlToCertificatePage != null) {
			System.out.println(urlToCertificatePage);
			downloadSingleImage(urlToCertificatePage);
			urlToCertificatePage = getUrlToNextCertificatePage(urlToCertificatePage);
		}

		

		// System.out.println(html);
		

	}

	@Override
	public void downloadSingleImage(String urlToCertificatePage) {
		String urlToCertificateDownload = urlToCertificatePage.replace("full", "save");
		setImageFileName(urlToCertificatePage, urlToCertificateDownload);
		String path = servletContext.getRealPath("/WEB-INF/images/" + imageFileName);
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
			
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(response);
			fos.close();
			out.close();
			in.close();
		} catch (Exception e) {

		}
	}

	@Override
	public void changeImageName(String urlToCertificateDownload) {
		// TODO Auto-generated method stub

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
			if(elementWithSearchedLink == null) {
				return null;
			}
			linkToNextImage = "http://szukajwarchiwach.pl" + elementWithSearchedLink.attr("href");
		} catch (IOException e1) {
		}
		
		return linkToNextImage;
	}

}

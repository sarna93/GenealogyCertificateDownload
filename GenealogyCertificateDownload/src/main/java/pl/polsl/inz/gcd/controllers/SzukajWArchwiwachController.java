package pl.polsl.inz.gcd.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.polsl.inz.gcd.models.DownloadForm;
import pl.polsl.inz.gcd.services.DownloadCertificatesService;

@Controller
@RequestMapping("/szukajwarchiwach")
public class SzukajWArchwiwachController {

	@Autowired
	@Qualifier("swaService")
	private DownloadCertificatesService downloadCertificatesService;
	private String downloadMessage = "";
	private String downloadMessageColor = "";
	private boolean downloadSuccess;

	@RequestMapping("/panel")
	public String showDownloadPanel(Map<String, Object> model) {
		model.put("portal", "Download certificates from szukajwarchiwach.pl");
		model.put("action", "download");
		model.put("downloadMessage", downloadMessage);
		model.put("downloadMessageColor", downloadMessageColor);
		model.put("downloadForm", new DownloadForm());
		downloadMessage = "";
		return "downloadPanel";
	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public String downloadCertificates(@ModelAttribute("downloadForm") DownloadForm downloadForm, ModelMap model) {

		String downloadType = downloadForm.getDownloadType();
		if (downloadType.equals("singleCertificate")) {
			downloadSuccess = downloadCertificatesService.downloadCertificate(downloadForm.getLinkToCertificate(),
					downloadForm.getFolderPath());
		} else {
			downloadSuccess = downloadCertificatesService.downloadCertificatesBook(downloadForm.getLinkToCertificate(),
					downloadForm.getFolderPath());
		}

		downloadMessage = downloadSuccess ? "DOWNLOADED SUCCESSFULLY" : "ERROR DURING DOWNLOADING";
		downloadMessageColor = downloadSuccess ? "green" : "red";
		return "redirect:/szukajwarchiwach/panel";
	}

}

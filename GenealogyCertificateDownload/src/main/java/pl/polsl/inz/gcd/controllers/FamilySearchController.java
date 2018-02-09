package pl.polsl.inz.gcd.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import pl.polsl.inz.gcd.models.DownloadForm;
import pl.polsl.inz.gcd.models.Quote;
import pl.polsl.inz.gcd.services.DownloadCertificatesService;

@Controller
@RequestMapping("/familysearch")
public class FamilySearchController {
	
	@Autowired
	@Qualifier("fsService")
	private DownloadCertificatesService downloadCertificatesService;
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping("/panel")
	public String showDownloadPanel(Map<String, Object> model) {
		model.put("portal", "Family Search Panel");
		model.put("message", "You must be logged in familysearch.org");
		model.put("action", "download");
		model.put("downloadForm", new DownloadForm());
		return "downloadPanel";
	}  

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public String downloadCertificates(@ModelAttribute("SpringWeb") DownloadForm downloadForm, ModelMap model) {

		RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject(downloadForm.getLinkToCertificate(), Quote.class);
		// model.addAttribute("name", student.getName());
		// model.addAttribute("age", student.getAge());
		// model.addAttribute("id", student.getId());

		return "redirect:/familysearch/panel";
	}
	
	/*@RequestMapping(value = "/image-manual-response", method = RequestMethod.GET)
	public void getImageAsByteArray(HttpServletResponse response) throws IOException {
	    InputStream in = servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
	}*/
}

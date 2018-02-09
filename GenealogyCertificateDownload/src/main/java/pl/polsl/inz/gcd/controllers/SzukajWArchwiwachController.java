package pl.polsl.inz.gcd.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/panel")
	public String showDownloadPanel(Map<String, Object> model) {
		model.put("portal", "Szukaj w archiwach Panel");
		model.put("message", "");
		model.put("action", "download");
		model.put("downloadForm", new DownloadForm());
		return "downloadPanel";
	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public String downloadCertificates(@ModelAttribute("downloadForm")DownloadForm downloadForm, ModelMap model) {
		
		String downloadType = downloadForm.getDownloadType();
		if(downloadType.equals("singleCertificate")) {
			downloadCertificatesService.downloadCertificate(downloadForm.getLinkToCertificate());
			return "redirect:/szukajwarchiwach/download_single_certificate";
		}
		else {
			downloadCertificatesService.downloadCertificatesBook(downloadForm.getLinkToCertificate());
			return "redirect:/szukajwarchiwach//download_zip";
		}
		
        
		
	}
	
	@RequestMapping(value = "/download_single_certificate", method = RequestMethod.GET)
	public void getImageAsByteArray(HttpServletResponse response) throws IOException {
		String imageFileName = downloadCertificatesService.getImageFileName();
		InputStream in;
		String path = "/WEB-INF/images/" + imageFileName;
	    while( (in = servletContext.getResourceAsStream("/WEB-INF/images/" + imageFileName)) == null)

	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    response.setHeader( "Content-Disposition", "attachment;filename=" + imageFileName );
	    response.setHeader("Content-Length", String.valueOf(new File(path).length()));
	    IOUtils.copy(in, response.getOutputStream());
	    //response.getOutputStream().flush();
	    //response.getOutputStream().getFD().sync();
	}
	
	@RequestMapping(value = "/download_zip", method = RequestMethod.GET)
	public void getZipAsByteArray(HttpServletResponse response) throws IOException {
		InputStream in;
		String path = "/WEB-INF/images/" + "test2.zip";
	    while( (in = servletContext.getResourceAsStream(path)) == null)

	    response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
	    response.setHeader( "Content-Disposition", "attachment;filename=" + "testzipowy.zip" );
	    response.setHeader("Content-Length", String.valueOf(new File(path).length()));
	    IOUtils.copy(in, response.getOutputStream());
	}
	
}

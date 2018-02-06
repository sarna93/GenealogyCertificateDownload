package pl.polsl.inz.gcd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class GenealogyCertificateDownloadApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GenealogyCertificateDownloadApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GenealogyCertificateDownloadApplication.class, args);
	}
}

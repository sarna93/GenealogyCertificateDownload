package pl.polsl.inz.gcd.models;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FSNavigationFromJSON {

	private int count;
	private int current;
	private String next = null;
	private String partOfDownloadUrl;
	private String URL;

	public FSNavigationFromJSON() {
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPartOfDownloadUrl() {
		return partOfDownloadUrl;
	}

	public void setPartOfDownloadUrl(String partOfDownloadUrl) {
		this.partOfDownloadUrl = partOfDownloadUrl;
	}

	@JsonProperty("properties")
	private void unpackNavigation(Map<String, String>[] properties) {
		for (Map<String, String> element : properties) {
			if (element.get("type").equals("org.familysearch.records.next_image")) {
				next = element.get("value");
			} else {
				if (element.get("type").equals("org.familysearch.records.image_count")) {
					count = Integer.parseInt(element.get("value"));
				}
			}
		}
	}

	@JsonProperty("isPartOf")
	private void unpackCurrentPosition(Map<Object, Object> isPartOf) {
		for (Map.Entry<Object, Object> entry : isPartOf.entrySet()) {
			if (entry.getKey().equals("offsetInParent"))
				current = (int) isPartOf.get("offsetInParent");
			current++; // index page start from 0 but first page is 1
		}
	}

	@JsonProperty("bibliographicCitation")
	private void unpackPartOfLinkToDownload(String bibliographicCitation) {
		int index = bibliographicCitation.lastIndexOf("?cc");
		bibliographicCitation = bibliographicCitation.substring(0, index);
		index = bibliographicCitation.lastIndexOf("/");
		partOfDownloadUrl = bibliographicCitation.substring(index + 1, bibliographicCitation.length());
	}

	@JsonProperty("identifier")
	private void unpackURL(Map<String, String> identifier) {
		URL = identifier.get("value");
	}

}
package pl.polsl.inz.gcd.models;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private int count;
    private int current;
    private String next = null;
    
    
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

	private Value value;

    public Quote() {
    }


    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("properties")
    private void unpackNavigation(Map<String, String>[] properties) {
    	for(Map<String, String> element: properties) {
    		if(element.get("type").equals("org.familysearch.records.next_image")){
    			next = element.get("value");
    		}
    		else {
    			if(element.get("type").equals("org.familysearch.records.image_count")){
        			count = Integer.parseInt(element.get("value"));
        		}
    		}	
    	}
    }
    
    @SuppressWarnings("unchecked")
    @JsonProperty("isPartOf")
    private void unpackCurrentPosition(Map<Object, Object> properties) {
    	for(Map.Entry<Object, Object> entry: properties.entrySet()) {
    		if(entry.getKey().equals("offsetInParent"))
    			current = (int) properties.get("offsetInParent");
    			current++; // index page start from 0 but first page is 1
    	}
    }

}
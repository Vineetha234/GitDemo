package pojo;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class GetCourse
{
	
	private String instructor;
	private String url;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkedIn;
	
	//@JsonProperty("instructer")
	public String getInstructor()
	{
	
		return instructor;
	}
	public void setInstructer(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public pojo.Courses getCourses() {
		return courses;
	}
	public void setCourses(pojo.Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
}

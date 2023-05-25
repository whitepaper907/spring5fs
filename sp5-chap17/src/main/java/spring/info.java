package spring;

import org.springframework.beans.factory.annotation.Value;

public class info {

	@Value("${info.version}")
	private String version;
	
	public void printInfo() {
		System.out.println("version = "+version);
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}

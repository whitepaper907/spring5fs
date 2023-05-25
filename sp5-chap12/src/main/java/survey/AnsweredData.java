package survey;

import java.util.List;

public class AnsweredData {

	private List<String> responses; // responnses[0]
	private Respondent res; // res.age, res.location으로 표현
	
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	
	
}

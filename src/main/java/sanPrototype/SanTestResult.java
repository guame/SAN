package sanPrototype;

import sanPrototype.roll.SanRoll;

/**
 * @author Guillaume
 * 16/09/2018 - 21:59
 */
public class SanTestResult {
	StringBuffer buffer = new StringBuffer();
	private SanRoll sanRoll;
	
	public SanTestResult append(String s) {
		if(buffer.length() > 0) buffer.append("\n");
		buffer.append(s);
		return this;
	}
	
	public String getExplanation() {
		return buffer.toString();
	}
	
	public SanRoll getSanRoll() {
		return sanRoll;
	}
	
	public void setSanRoll(SanRoll sanRoll) {
		this.sanRoll = sanRoll;
	}
}

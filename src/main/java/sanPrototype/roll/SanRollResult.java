package sanPrototype.roll;

import static sanPrototype.roll.RollRank.*;

public class SanRollResult {
	private final static String explanationTemplate = "SAN roll: %s against %s. This is a %s.";
	private final static String fumbleUnder50ExplanationTemplate = "(SAN score < 50, fumble on 96-100).";
	private final static String fumbleOver50ExplanationTemplate = "(SAN score >= 50, fumble on 100).";
	RollRank rank;
	String explanation;
	
	
	public SanRollResult(Integer rollValue, int sanScore) {
		
			String fumbleTemplate = fumbleOver50ExplanationTemplate;
			
			if (rollValue < sanScore) {
				rank = SUCCESS;
			} 
			else {
				
				int fumblemargin = 100;
				
				if (sanScore < 50) {
						fumbleTemplate = fumbleUnder50ExplanationTemplate;
						fumblemargin = 96;
				}
				
				rank = (rollValue >= fumblemargin) ? FUMBLE : FAILURE;
			}
			
			explanation = String.format(explanationTemplate, rollValue, sanScore, rank.name());
			if(rank == FUMBLE) {
				explanation += fumbleTemplate;
			}
	}
	
	public String getExplanation() {
		return explanation;
	}
	
	public RollRank getRank() {
		return rank;
	}
	

}


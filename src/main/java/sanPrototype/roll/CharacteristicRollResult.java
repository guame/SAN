package sanPrototype.roll;

import static sanPrototype.roll.RollRank.*;

/**
 * @author Guillaume
 * 16/09/2018 - 21:09
 */
public class CharacteristicRollResult  {
	
	private final static String explanationTemplate = "%s roll: %s against %s. This is a %s.";
	private final static String fumbleUnder50ExplanationTemplate = "(%s score < 50, fumble on 96-100).";
	private final static String fumbleOver50ExplanationTemplate = "(%s score >= 50, fumble on 100).";
	
	RollRank rank;
	String explanation;
	
	public CharacteristicRollResult(String characteristicName, Integer rollValue, int characteristic) {
		
		String fumbleTemplate = String.format(fumbleOver50ExplanationTemplate, characteristicName);
		
		if (rollValue == 01 ) {
			rank = CRITICAL_SUCCESS;
		}
		else if (rollValue < characteristic/5) {
			rank = EXTREME_SUCCESS;
		}
		else if (rollValue < characteristic/2) {
			rank = HARD_SUCCESS;
		}
		if (rollValue < characteristic) {
			rank = SUCCESS;
		}
		else {
			
			int fumblemargin = 100;
			
			if (characteristic < 50) {
				fumbleTemplate = fumbleUnder50ExplanationTemplate;
				fumblemargin = 96;
			}
			
			rank = (rollValue >= fumblemargin) ? FUMBLE : FAILURE;
		}
		
		explanation = String.format(explanationTemplate, characteristicName, rollValue, characteristic, rank.name());
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
	
	public boolean isFailed() {
		return rank.getOrder() < RollRank.SUCCESS.getOrder();
	}
}

package sanPrototype;


import sanPrototype.roll.CharacteristicRoll;
import sanPrototype.roll.CharacteristicRollResult;
import sanPrototype.roll.Dice;
import sanPrototype.roll.SanRoll;
import sanPrototype.roll.SanRollResult;

import static sanPrototype.roll.RollRank.FAILURE;
import static sanPrototype.roll.RollRank.SUCCESS;

/**
 * @author Guillaume
 * 16/09/2018 - 21:14
 */
public class GameCharacter {
	
	int SAN;
	int INT;
	
	int SANLostInSession = 0;
	
	InsanityState insanityState = InsanityState.SANE;
	InsanityPhase insanityPhase = InsanityPhase.NONE;
	int underlyingPhaseDuration = 0;
	
	
	SanTestResult sanTest(Dice min, Dice max) {
		
		SanTestResult sanTestResult = new SanTestResult();
		sanTestResult.append("--- San test ---");
		
		if(insanityPhase == InsanityPhase.A_BOUT_OF_MADNESS) {
			sanTestResult.append("The investigator is in "+InsanityPhase.A_BOUT_OF_MADNESS+", no SAN loss");
		}
		else {
			
			SanRoll sanRoll = new SanRoll(this);
			SanRollResult sanRollResult = sanRoll.roll();
			sanTestResult.setSanRoll(sanRoll);
			sanTestResult.append(sanRollResult.getExplanation());
			
			Integer sanLoss;
			if (sanRollResult.getRank() == SUCCESS) {
				sanLoss = min.roll().getValue();
				sanTestResult.append("Minimum SAN loss because of the SUCCESS.");
				sanTestResult.append("SAN loss dice: " + min.describeLastRoll());
				
			} 
			else {
				if (sanRollResult.getRank() == FAILURE) {
					sanLoss = max.roll().getValue();
					sanTestResult.append("Regular SAN loss because of the FAILURE.");
					sanTestResult.append("SAN loss dice: " + max.describeLastRoll());
				} else {
					sanLoss = max.rollMax().getValue();
					sanTestResult.append("Maximum SAN loss because of the FUMBLE.");
					sanTestResult.append("SAN loss dice: " + max.describeLastRoll());
				}
				
				sanTestResult.append("Loss of control: Sursauter, hurler, mouvement/action combat involontaire, se figer...");
				
			}
		
			
			sanTestResult.append("SAN lost for this test: " + sanLoss);
			
		if(sanLoss > 0) {
			
			boolean newABoutOfMadness = false;
			SANLostInSession += sanLoss;
			sanTestResult.append("Cumuled SAN lost for today: " +SANLostInSession);
			
			if(insanityPhase == InsanityPhase.UNDERLYING_INSANITY) {
				sanTestResult.append("The investigator is in " + InsanityPhase.UNDERLYING_INSANITY + ", a new " + InsanityPhase.A_BOUT_OF_MADNESS + " is starting");
				insanityPhase = InsanityPhase.A_BOUT_OF_MADNESS;
				newABoutOfMadness = true;
			}
			
			if(sanLoss >= 5 && SANLostInSession <= SAN/5 && SAN > sanLoss) {
				CharacteristicRoll intRoll = new CharacteristicRoll(this, "INT");
				CharacteristicRollResult intRollResult = intRoll.roll();
				sanTestResult.append("The investigator is about to lose is mind...");
				sanTestResult.append(intRollResult.getExplanation());
				if(intRollResult.isFailed()) {
					sanTestResult.append("but his mind is not able to process !");	
				}
				else {
					insanityPhase = InsanityPhase.A_BOUT_OF_MADNESS;
					insanityState = InsanityState.TEMPORARY;
					newABoutOfMadness = true;
					sanTestResult.append("for a temporary period !");
				}
			}
			
			if(SANLostInSession > SAN/5 && SAN > sanLoss) {
				sanTestResult.append("The investigator is about to lose is mind for an Indefinite amount of time !");
				sanTestResult.append("He needs to have a treatment for it !");
				insanityPhase = InsanityPhase.A_BOUT_OF_MADNESS;
				insanityState = InsanityState.INDEFINITE;
				newABoutOfMadness = true;
			}
			
			if(SAN <= sanLoss) {
				sanTestResult.append("The investigator is about to lose is mind permanently !");
				sanTestResult.append("He needs to have a treatment for it !");
				insanityPhase = InsanityPhase.A_BOUT_OF_MADNESS;
				insanityState = InsanityState.PERMANENT;
			}
			SAN -= sanLoss;
			
			if(newABoutOfMadness) {
				newABoutOfMadness = false;
				Dice d10 = new Dice(10);
				Integer time = d10.roll().getValue();
				String realTimeEffect = realTimeSanityEffect(d10.roll().getValue());
				String summaryTimeEffect = summaryTimeEffect(d10.roll().getValue());
				sanTestResult.append(String.format("If the madness is in real time: the investigator is insane for %s round", time));
				sanTestResult.append(String.format("and suffer from: %s", realTimeEffect));
				sanTestResult.append(String.format("If the madness is in summary: the investigator is insane for %s hours", time));
				sanTestResult.append(String.format("and suffer from: %s", summaryTimeEffect));
			}

			
		}	
		}
		
		return sanTestResult; 
		}
	
	private String summaryTimeEffect(Integer value) {
		switch (value) {
			case 0:  return "Amnesia";
			case 1:  return "Robbed. Luck Roll to check if any treasure is lost";
			case 2:  return "Battered (bruised). Hit point are /2";
			case 3:  return "Violence";
			case 4:  return "Ideology Beliefs: exagerate one backstory entry";
			case 5:  return "Significant people: try to get close to that person to improve the relationship";
			case 6:  return "Institutionalized (prison, hospital, psychiatric ward)";
			case 7:  return "Flee in panic, far away (train station, bus stop)";
			case 8:  return "Random Phobia";
			case 9:  return "Random Mania";
		}
		return "? invalide result " + value;
	}
	
	private String realTimeSanityEffect(Integer value) {
		switch (value) {
			case 0:  return "Amnesia";
			case 1:  return "Psychosomatic disability (blindness, deafness, ...)";
			case 2:  return "Violence";
			case 3:  return "Paranoia";
			case 4:  return "Mistake surounding person with a significant one";
			case 5:  return "Faint";
			case 6:  return "Flee in Panic";
			case 7:  return "Physical hysterics or emotional outburst (laughting, crying, ...)";
			case 8:  return "Random Phobia (active, but will fade away)";
			case 9:  return "Random Mania (active, but will fade away)";
		}
		return "? invalide result " + value;
	}
	
	
	public int getSAN() {
		return SAN;
	}
	
	public void setSAN(int SAN) {
		this.SAN = SAN;
	}
	
	public int getINT() {
		return INT;
	}
	
	public void setINT(int INT) {
		this.INT = INT;
	}
	
	public int getCharacteristicValue(String characteristicName) {
		if(characteristicName.equals("INT")) return getINT();
		return 0;
	}
	
	public void hasSomeRest() {
		SANLostInSession = 0;
		insanityPhase = InsanityPhase.NONE;
		System.out.println("Investigator is now resting, the SAN decount is reset and the sanity phase as well");
	}
	
	public void updateInsanityPhase() {
		if(insanityPhase == InsanityPhase.A_BOUT_OF_MADNESS) {
			insanityPhase = InsanityPhase.UNDERLYING_INSANITY;
			Dice d10 = new Dice(10, 1,0);
			underlyingPhaseDuration = d10.roll().getValue();
			System.out.println(String.format(InsanityPhase.A_BOUT_OF_MADNESS + " is now " + InsanityPhase.UNDERLYING_INSANITY + " for %s hours", underlyingPhaseDuration));
		}
	}
	
	public void nextHour() {
		underlyingPhaseDuration--;
		if(underlyingPhaseDuration == 0 && insanityPhase == InsanityPhase.UNDERLYING_INSANITY) {
			insanityPhase = InsanityPhase.NONE;
			System.out.println("Investigator is no more in " + InsanityPhase.UNDERLYING_INSANITY);
		}
		
	}
}



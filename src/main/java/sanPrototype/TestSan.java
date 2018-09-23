package sanPrototype;

import sanPrototype.roll.Dice;

/**
 * @author Guillaume
 * 16/09/2018 - 15:32
 */
public class TestSan {
	
	
	
	public static void main(String[] args) {
		GameCharacter randolph = new GameCharacter();
		randolph.setSAN(50);
		randolph.setINT(75);
		Dice min = new Dice(4, 1, -2);
		Dice max = new Dice(10, 1, 0);
		int hour = 1;
		int sanRoll =0;
		int firstMadness=0;
		int firstIndeterminateMadness=0;
		int countTemporary=0;
		int countIndeterminate=0;
		
		while(randolph.SAN > 0) {
			System.out.println("Hour " + hour%24);
			
			Dice d10=new Dice(50);
			if(d10.roll().getValue() > 40) {
				SanTestResult sanTestResult = randolph.sanTest(min, max);
				sanRoll++;
				System.out.println(sanTestResult.getExplanation());
				if(randolph.insanityState != InsanityState.SANE && firstMadness == 0) {
					firstMadness = sanRoll;	
				}
				
				if(randolph.insanityState == InsanityState.INDEFINITE) {
					if(firstIndeterminateMadness == 0) {
						firstIndeterminateMadness = sanRoll;
					}
					countIndeterminate++;
				}
				
				if(randolph.insanityState == InsanityState.TEMPORARY) {
					countTemporary++;
				}
			}
			
			randolph.updateInsanityPhase();
			
			if(hour % 16 == 0) {
				randolph.hasSomeRest();
			}
			
			randolph.nextHour();
			hour++;
		}
		
		System.out.println("-----------------------");
		System.out.println("Permanent madness after " + sanRoll + " SAN Roll !");
		System.out.println("First madness after " + firstMadness + " SAN Roll !");
		System.out.println("First indeterminate after " + firstIndeterminateMadness + " SAN Roll !");
		System.out.println("count temporary before completion: " + countTemporary);
		System.out.println("count indeterminate before completion: " + countIndeterminate);
		System.out.println("-----------------------");
	}
}


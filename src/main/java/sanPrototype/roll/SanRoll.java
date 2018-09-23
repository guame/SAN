package sanPrototype.roll;

import sanPrototype.GameCharacter;

/**
 * @author Guillaume
 * 16/09/2018 - 21:09
 */
public class SanRoll implements Roll<SanRollResult> {
	
	GameCharacter character;
	Dice dice = new Dice(100, 1, 0);
	SanRollResult lastResult;
	private Integer rollValue;
	
	public SanRoll(GameCharacter character) {
		this.character = character;
	}
	
	@Override
	public SanRollResult roll() {
		DiceResult<Integer> roll = dice.roll();
		this.rollValue = roll.diceValue;
		lastResult = new SanRollResult(rollValue, character.getSAN());
		return lastResult;
	}
	
	public Dice getDice() {
		return dice;
	}
	
	public SanRollResult getLastResult() {
		return lastResult;
	}
	
	public Integer getRollValue() {
		return rollValue;
	}
	
	public void setLastResult(SanRollResult lastResult) {
		this.lastResult = lastResult;
	}
}

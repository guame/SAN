package sanPrototype.roll;

import sanPrototype.GameCharacter;

/**
 * @author Guillaume
 * 16/09/2018 - 21:09
 */
public class CharacteristicRoll implements Roll<CharacteristicRollResult> {
	
	GameCharacter character;
	Dice dice = new Dice(100, 1, 0);
	CharacteristicRollResult lastResult;
	private Integer rollValue;
	private String characteristicName;
	
	public CharacteristicRoll(GameCharacter character, String characteristicName) {
		this.character = character;
		this.characteristicName = characteristicName;
	}
	
	@Override
	public CharacteristicRollResult roll() {
		DiceResult<Integer> roll = dice.roll();
		this.rollValue = roll.diceValue;
		lastResult = new CharacteristicRollResult(characteristicName, rollValue, character.getCharacteristicValue(characteristicName));
		return lastResult;
	}
	
	public Dice getDice() {
		return dice;
	}
	
	public CharacteristicRollResult getLastResult() {
		CharacteristicRollResult lastResult = this.lastResult;
		if(lastResult == null) 
			roll();
		return lastResult;
	}
	
	public Integer getRollValue() {
		return rollValue;
	}
	
	public void setLastResult(CharacteristicRollResult lastResult) {
		this.lastResult = lastResult;
	}
}

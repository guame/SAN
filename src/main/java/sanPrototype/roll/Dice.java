package sanPrototype.roll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Guillaume
 * 16/09/2018 - 17:47
 */
public class Dice extends AbstractDice<Integer> {
	
	int side = 6;
	int offset = 1;
	int modifier = 0;
	List<DiceResult<Integer>> history = new ArrayList<>();
	
	public Dice() {
		
	}
	
	public Dice(int side) {
		this.side = side;
	}
	
	public Dice(int side, int modifier) {
		this.side = side;
		this.modifier = modifier;
	}
	
	public Dice(int side, int offset, int modifier) {
		this.side = side;
		this.offset = offset;
		this.modifier = modifier;
	}
	
	@Override
	Integer diceRoll() {
			Random random = new Random();
		int i = random.nextInt(side) + modifier + offset;
		
		if(i<offset) 
			return offset;
		
		return i;						
	}
	
	
	public DiceResult<Integer> rollMax() {
		DiceResult<Integer> result = new DiceResult<>(getMax());
		history.add(result);
		return result;
	}
	
	public String describeDice() {
		String modifierStr = modifier != 0 ? String.valueOf(modifier) : "";
		if(modifier > 0) modifierStr += "+"; 
		return "d" + side + modifierStr;
	}
	
	public String describeLastRoll() {
		if(history.isEmpty()) {
			describeDice();
		}
		
		return describeDice() + " -> " + lastValue();
		
	}
	
	public int getMin() {
		int i = offset + modifier;
		if(i < offset) return offset;
		return i;
	}
	public int getMax() {return (side-offset) + modifier;}
	public int getSum() {
		return lastValue();
	}
	
	
	
}

package sanPrototype.roll;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume
 * 16/09/2018 - 17:47
 */
public class FixedValue extends Dice {
	
	
	List<Integer> history = new ArrayList<>();
	
	public FixedValue(int value) {
		this.side = value;
	}
	
	@Override
	Integer diceRoll() {
		return side;
	}
	
	@Override
	public String describeDice() {
		return side + "";
	}
	
	@Override
	public String describeLastRoll() {
		return super.describeLastRoll();
	}
}

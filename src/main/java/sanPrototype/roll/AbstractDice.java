package sanPrototype.roll;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guillaume
 * 16/09/2018 - 17:47
 */
public abstract class AbstractDice<R> implements Roll<DiceResult<R>> {
	
	List<DiceResult<R>> history = new ArrayList<>();
	
	
	@Override
	public DiceResult<R> roll() {
		DiceResult<R> result = new DiceResult<>(diceRoll());
		history.add(result);
		return result;
	}
	
	abstract R diceRoll();
	
	
	
	public DiceResult<R> lastResult() {
		if(history.isEmpty()) 
			roll();
		
		return history.get(history.size()-1);
	} 
	
	public R lastValue() {
		return lastResult().diceValue;
	}
}

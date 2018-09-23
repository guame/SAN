package sanPrototype.roll;

/**
 * @author Guillaume
 * 16/09/2018 - 22:51
 */
public class DiceResult<R> {
	R diceValue;
	
	public DiceResult(R result) {
		this.diceValue = result;
	}
	
	public R getValue() {
		return diceValue;
	}
}

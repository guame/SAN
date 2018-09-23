//package san.roll;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author Guillaume
// * 16/09/2018 - 17:46
// */
//public class DiceGroup extends Dice {
//
//	List<Dice> diceList;
//	
//	public DiceGroup(Dice ... dice) {
//		this.diceList = Arrays.asList(dice);
//	}
//	
//	@Override
//	public DiceResult roll() {
//		diceList.forEach(Dice::roll);
//	}
//	
//	@Override
//	public int getMin() {
//		return super.getMin();
//	}
//	
//	@Override
//	public int getMax() {
//		return super.getMax();
//	}
//	
//	@Override
//	public int getSum() {
//		return super.getSum();
//	}
//}

package sanPrototype.roll;

/**
 * @author Guillaume
 * 16/09/2018 - 21:10
 */
public enum RollRank {
	CRITICAL_SUCCESS(5),
	EXTREME_SUCCESS(4),
	HARD_SUCCESS(3),
	SUCCESS(2),
	FAILURE(1),
	FUMBLE(0);
	
	private int order;
	RollRank(int order) {
		this.order = order;
	}
	
	public int getOrder() {
		return order;
	}
}

package decorator;

public class Push implements Drink {
	private Drink d;
	public Push(Drink d) {
		this.d = d;
	}
	
	public void cost() {
		d.cost();
	}

}

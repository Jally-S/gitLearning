package decorator;

public class BingTang extends Push {
	public BingTang(Drink d) {
		super(d);
	}
	public void cost() {
		super.cost();
		push();
	}
	public void push() {
		System.out.println("�ӵ����...");
		System.out.println("+���Ƿ�dԪ");
	}
}

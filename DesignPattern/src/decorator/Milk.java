package decorator;

public class Milk extends Push {
	public Milk(Drink d) {
		super(d);
	}
	public void cost() {
		super.cost();
		push();
	}
	public void push() {
		System.out.println("�ӵ�����...");
		System.out.println("+���ͷ�eԪ");
	}
}

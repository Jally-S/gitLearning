package decorator;

public class RedTang extends Push {
	public RedTang(Drink d) {
		super(d);
		
	}
	public void cost() {
		super.cost();
		push();
	}
	public void push() {
		System.out.println("�ӵ����...");
		System.out.println("+���Ƿ�cԪ");
	}
}

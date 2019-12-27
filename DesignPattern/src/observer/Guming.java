package observer;

public class Guming implements OnlineSystem {
	int i;
	public Guming (int i) {
		this.i = i;
	}
	public void update() {
		System.out.println("����"+i+"�յ����¼۸�֪ͨ...");
	}

}

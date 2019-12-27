package decorator;
/**
 * װ��ģʽ
 * @author Сʯͷ
 *
 */
public class Test {
	public static void main(String[] args) {
		Drink d = new Tea();
		Milk m = new Milk(d);
		RedTang r = new RedTang(m);
		r.cost();
		
		Drink d1 = new Coffee();
		BingTang b1 = new BingTang(d1);
		b1.cost();
	}

}

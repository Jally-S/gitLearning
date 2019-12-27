package abstract_factory;
/**
 * ���󹤳�ģʽ
 * @author Сʯͷ
 *
 */
public class Test {
	public static void main(String[] args) {
		Factory f1 = new PcFactory();
		Cpu c1 = f1.produceCpu();
		c1.play();
		Ram r1 = f1.produceRam();
		r1.play();
		
		Factory f2 = new MacFactory();
		Cpu c2 = f2.produceCpu();
		c2.play();
		Ram r2 = f2.produceRam();
		r2.play();
	}

}

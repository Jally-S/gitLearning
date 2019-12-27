package factory_method;
/**
 * ��������ģʽ
 * @author Сʯͷ
 *
 */
public class Test {
	public static void main(String []args) {
		Factory f = new HaierFactory();
		AirCondition ad = f.produce();
		ad.play();
		
		Factory f1 = new MideaFactory();
		AirCondition ad1 = f1.produce();
		ad1.play();
		
	}

}

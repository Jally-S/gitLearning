package factory_method;

public class HaierFactory implements Factory {
	public AirCondition produce() {
		System.out.println("�����������������յ�");
		return new HaierAirCondition();
	}

}

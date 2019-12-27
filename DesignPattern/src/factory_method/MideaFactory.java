package factory_method;

public class MideaFactory implements Factory {
	public AirCondition produce() {
		System.out.println("���Ĺ����������Ŀյ�");
		return new MideaAirCondition();
	}
}

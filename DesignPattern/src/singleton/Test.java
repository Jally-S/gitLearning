package singleton;
/**
 * ����ģʽ
 * @author Сʯͷ
 *
 */
public class Test{
	public static void main(String[] args) {
		Danlimoshi d1 = Danlimoshi.getShili();
		Danlimoshi d2 = Danlimoshi.getShili();
		
		System.out.println("�ж�����ʵ���ǲ���һ����" + (d1==d2));
		
		
	}

}

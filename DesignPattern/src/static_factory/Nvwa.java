package static_factory;
/**
 * �򵥹���ģʽ
 * @author Сʯͷ
 *
 */
public class Nvwa {
	public static Person show(String s) throws Exception {
		if(s == null) {
			return null;
		}
		if(s.equalsIgnoreCase("M")) {
			System.out.println("��һ������...");
			return new Man();
		}else if(s.equalsIgnoreCase("W")) {
			System.out.println("��һ��Ů��...");
			return new Woman();
		}else if(s.equalsIgnoreCase("R")) {
			System.out.println("��һ��������...");
			return new Robot();
		}else
		{
			throw new Exception("�Բ����첻��");
			//return null;
		}
	}
	public static void main(String[] args) throws Exception {
		Person p1 = Nvwa.show("M");
		p1.produce();
		Person p2 = Nvwa.show("W");
		p2.produce();
		Person p3 = Nvwa.show("R");
		p3.produce();
		Person p4 = Nvwa.show("n");
		p4.produce();
	}

}

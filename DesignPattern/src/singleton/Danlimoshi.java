package singleton;

public class Danlimoshi {
	private static Danlimoshi i = null;
	private Danlimoshi () {}
	
	public static  Danlimoshi getShili() {
		int j = 1;
		if(i==null) {
			System.out.println("��1��ʵ����...");
			System.out.println("�����ֻ����һ��ʵ����...");
			i = new Danlimoshi();
		}
		else {
			
			j = j + 1;
			System.out.println("��" + j + "��ʵ����...");
			System.out.println("�˴�ʵ������Ĭ�ϲ��õ�һ��ʵ����...");
		}
		return i;
	}
	

}

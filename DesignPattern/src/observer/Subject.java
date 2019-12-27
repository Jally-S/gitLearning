package observer;

public class Subject extends OnlineWare {
	public void notified() {
		System.out.println("��Ʊ�۸�仯���ȴﵽ5%,�¼۸�ΪxxԪ...");
		for(Object o:onlineSystem) {
			((OnlineSystem)o).update();	
		}
	}

}

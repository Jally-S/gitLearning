package composite;

import java.util.ArrayList;

public class Folder extends AntiVirus {
	private ArrayList<AntiVirus> list = new ArrayList<AntiVirus>();
	
	public void add(AntiVirus a) {
		list.add(a);
	}
	
	public void remove(AntiVirus a) {
		list.remove(a);
	}
	
	public void shadu() {
		for(Object object:list) {
			((AntiVirus)object).shadu();
		}
	}

}

package observer;

import java.util.ArrayList;

public abstract class OnlineWare {
	protected ArrayList<OnlineSystem> onlineSystem = new ArrayList<OnlineSystem>();
	public void add(OnlineSystem o) {
		onlineSystem.add(o);
	}
	public void remove(OnlineSystem o) { 
		onlineSystem.remove(o);
	}
	public abstract void notified();

}

package command;

public class OpenCommand implements MenuItem{
	private BoardScreen bs;
	public OpenCommand() {
		bs = new BoardScreen();
	}
	public void click() {
		bs.open();
	}

}

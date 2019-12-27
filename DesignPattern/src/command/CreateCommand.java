package command;

public class CreateCommand implements MenuItem {
	private BoardScreen bs;
	public CreateCommand() {
		bs = new BoardScreen();
	}
	public void click() {
		bs.create();
	}

}

package command;

public class EditCommand implements MenuItem {
	private BoardScreen bs;
	public EditCommand() {
		bs = new BoardScreen();
	}
	public void click() {
		bs.edit();
	}

}

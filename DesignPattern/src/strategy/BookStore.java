package strategy;

public class BookStore {
	private BookCost bc;
	public void setBookCost(BookCost bc) {
		this.bc = bc;
	}
	public void cost() {
		bc.cost();
	}

}

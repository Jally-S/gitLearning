package strategy;
/**
 * ����ģʽ
 * @author Сʯͷ
 *
 */
public class Test {
	public static void main(String args[]) {
		BookCost cb = new ComputerBook();
		BookCost lb = new LanguageBook();
		BookCost nb = new NovelBook();
		BookStore b1 = new BookStore();
		b1.setBookCost(cb);
		b1.cost();
		b1.setBookCost(lb);
		b1.cost();
		b1.setBookCost(nb);
		b1.cost();

	}

}

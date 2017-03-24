
public class Connect4Tester {

	public static void main(String[] args) throws ColoumFullException {
		Connect4Core a = new Connect4Core();
		try {
			a.addChess(7);
		} catch (ColoumFullException excption) {
			System.out.println("Error");
		}
		a.addChess(0);
		System.out.println(a.toString());
		a.addChess(1);
		System.out.println(a.toString());
		a.addChess(2);
		System.out.println(a.toString());
		a.addChess(3);
		System.out.println(a.toString());
		System.out.println(a.checkWins());

	}

}

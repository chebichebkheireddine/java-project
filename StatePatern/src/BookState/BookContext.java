package BookState;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import State.Phone;

public class BookContext {
	private State state;

	public BookContext() {
		// TODO Auto-generated constructor stub
		state = new Englech(this);
	}

		//this is set of the next book
	public void setBook(State state) {
		this.state = state;
	}
	public String textNext() {
		return "The next book is : ";
	}
	public String preNext() {
		return "The last book is : ";
	}
	//this is for final valus
	public String Endbook() {
		return "End of book";
	}
	public String corent() {
		return " First book is  eng ";
	}
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		JPanel lapJLabel=new JPanel();
		// TODO Auto-generated method stub
		//this is book
		BookContext book=new BookContext();
		JButton nextbook= new JButton("next book");
		nextbook.addActionListener(e ->System.out.println(book.state.nextbook()));
		JButton prebook=new JButton("pre book");
		prebook.addActionListener(e ->System.out.println(book.state.prebook()));
		lapJLabel.add(nextbook);
		lapJLabel.add(prebook);
		frame.setSize(100,100);
		frame.add(lapJLabel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(book.corent());
		

	}
}

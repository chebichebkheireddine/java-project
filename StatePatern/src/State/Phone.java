package State;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Phone {
	private State state;
	public Phone() {
		state= new Offstate(this);
	}
	public void SetState(State state) {
		this.state=state;
	}
	public String Lock() {
		return "Lock the phone State";
	}
	public String UnLock() {
		return "UnLock the scrin of phone";
	}
	public String Home() {
		return "Home app system";
	}
	public String TurneON() {
		return "The phon is on ";
	}
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		JPanel lapJLabel=new JPanel();
		// TODO Auto-generated method stub
		Phone phone=new Phone();
		JButton home= new JButton("home");
		home.addActionListener(e ->System.out.println(phone.state.Onhome()));
		JButton off=new JButton("OFF:");
		off.addActionListener(e ->System.out.println(phone.state.OnOffOn()));
		lapJLabel.add(off);
		lapJLabel.add(home);
		frame.setSize(600,600);
		frame.add(lapJLabel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(phone.state.Onhome());
		System.out.println(phone.state.OnOffOn());
		

	}

}

package State;

public class Radystate extends State {
	public Radystate(Phone phone) {
		// TODO Auto-generated constructor stub
		super(phone);
	}

	@Override
	public String Onhome() {
		// TODO Auto-generated method stub
		return phone.Home();
	}

	@Override
	public String OnOffOn() {
		phone.SetState(new Offstate(phone));
		return phone.Lock();
	}

}

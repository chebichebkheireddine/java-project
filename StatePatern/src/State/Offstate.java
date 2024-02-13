package State;

public class Offstate extends State {
	public Offstate(Phone phone) {
		super(phone);
	}

	@Override
	public String Onhome() {
		// TODO Auto-generated method stub
		phone.SetState(new LockState(phone));
		return phone.TurneON();
	}

	@Override
	public String OnOffOn() {
		// TODO Auto-generated method stub
		phone.SetState(new LockState(phone));
		return phone.TurneON();
	}

}

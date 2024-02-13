package State;

public class LockState extends State {
	public LockState (Phone phone) {
		// TODO Auto-generated constructor stub
		super(phone);
	}

	@Override
	public String Onhome() {
		// TODO Auto-generated method stub
		phone.SetState(new Radystate(phone));
		return phone.UnLock();
	}

	@Override
	public String OnOffOn() {
		// TODO Auto-generated method stub
		phone.SetState(new Offstate(phone));
		
		return phone.Lock();
	}

}

package corba;

public class HelloImpl extends HelloApp.HelloPOA{

	@Override
	public String sayHello() {
		
		return "\n Hello World\n";
	}

}

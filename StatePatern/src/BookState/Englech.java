package BookState;

public class Englech extends State {

	public Englech(BookContext bookContext) {
		super(bookContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String nextbook() {
		// TODO Auto-generated method stub
		bookContext.setBook(new french(bookContext));
		return bookContext.textNext()+"Franch";
	}

	@Override
	public String prebook() {
		// TODO Auto-generated method stub
		return bookContext.corent();
	}

}

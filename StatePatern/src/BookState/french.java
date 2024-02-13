package BookState;

public class french extends State {

	public french(BookContext bookContext) {
		super(bookContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String nextbook() {
		bookContext.setBook(new arab(bookContext));
		return bookContext.textNext()+" Arabe";
	}

	@Override
	public String prebook() {
		// TODO Auto-generated method stub
		bookContext.setBook(new Englech(bookContext));
		return bookContext.corent();
	}

}

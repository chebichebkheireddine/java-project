package BookState;

public class arab extends State {

	public arab(BookContext bookContext) {
		super(bookContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String nextbook() {
		// TODO Auto-generated method stub
		return bookContext.Endbook();
	}

	@Override
	public String prebook() {
		bookContext.setBook(new french(bookContext));
		return bookContext.preNext()+"french";
	}

}

package BookState;

public abstract class State {
	protected BookContext bookContext;
	public State(BookContext bookContext) {
		this.bookContext=bookContext;
	}
	public abstract String nextbook();
	public abstract String prebook();

}

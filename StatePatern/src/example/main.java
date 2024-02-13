package example;

public class main {

	public String getNextBook() {
		String book="";
		if (book == "English book") {
			return "Arabic book";
		}else if (book=="Arabic book") {
			return "French book";
		}else if (book == "French book") {
			return "Mathematics book";
		}
		return null;
	}
}

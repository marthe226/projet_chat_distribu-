package mesobjets;

public class ChatMessage {

	int types;
	String message;
	
	
	public ChatMessage(int types, String message) {
		super();
		this.types = types;
		this.message = message;
	}
	public int getTypes() {
		return types;
	}
	public String getMessage() {
		return message;
	}
	
}

package engine.dialogue;

/**
 * This is an interface for any type of conversation state that might need to be implemeneted in the 
 * <code>ConversationManager</code> class. there is only one method.
 *
 */
public interface ConversationState {

	/**
	 * This will be called everytime the user initializes the <code>getNextText()</code> method in the 
	 * ConversationManger class
	 * @param cm
	 */
	public void doState(ConversationManager cm);
}

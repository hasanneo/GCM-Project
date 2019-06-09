package common;
/**
 * 
 * @author Hasan
 *
 *
 */
public interface GCMIF {
	/**
	   * Method that when overriden is used to display objects onto
	   * a UI.
	   */
	  public abstract void display(String message);
	  public abstract void SetServerObject(Object obj);
	  public abstract Object GetServerObject();
}

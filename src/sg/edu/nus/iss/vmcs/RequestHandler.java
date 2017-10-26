package sg.edu.nus.iss.vmcs;

import java.awt.Panel;

public abstract class RequestHandler extends Panel {

	  private RequestHandler next;

	  public RequestHandler(RequestHandler next) {
	    this.next = next;
	  }

	  /**
	   * Request handler
	   */
	  public void handleRequest(Request req) {
	    if (next != null) {
	      next.handleRequest(req);
	    }
	  }
}

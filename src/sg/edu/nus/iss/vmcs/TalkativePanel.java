package sg.edu.nus.iss.vmcs;

public abstract class TalkativePanel{
	  private Mediator mediator;
	  public TalkativePanel(Mediator m) {
	    mediator = m;
	  }
	  
	  public void send(Object arg) {
	    mediator.send(arg, this);
	  }
	  
	  public Mediator getMediator() {return mediator;}
	  public abstract void receive(Object arg);
}

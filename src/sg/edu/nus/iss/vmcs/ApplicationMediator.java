package sg.edu.nus.iss.vmcs;

import java.util.ArrayList;

public class ApplicationMediator implements Mediator {
	  private ArrayList<TalkativePanel> panels;
	  public ApplicationMediator() {
		  panels = new ArrayList<TalkativePanel>();
	  }
	  public void addTalkativePanel(TalkativePanel TalkativePanel) {
		  panels.add(TalkativePanel);
	  }
	  @Override
		public void send(Object arg, TalkativePanel originator) {
	    //let all other screens know that this screen has changed
	    for(TalkativePanel panel: panels) {
	      //don't tell ourselves
	      if(panel != originator) {
	    	  panel.receive(arg);
	      }
	    }
	  }
	}

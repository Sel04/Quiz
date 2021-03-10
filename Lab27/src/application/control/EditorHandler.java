package application.control;

import application.view.EditorView;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class EditorHandler implements EventHandler<ActionEvent> {
    public EditorView ev;
    
   
    PauseTransition ps=new PauseTransition(new Duration(30));
    public EditorHandler(EditorView ev) {
    	this.ev=ev;
    }
    
    
;	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String data=null;
		
		if(arg0.getSource() instanceof Button) {
			data=(String)((Button)arg0.getSource()).getUserData();
			
			switch(data) {
			case "adden":
				ev.questionAdd();
				break;
				
			case "bearbeiten":
				ev.questionEdit();
				break;
				
			case "löschen":
				ev.questionDelete();
				break;
				
			case "Ausgeben":
				ev.ausgeben();
				break;
			}
		}
	}

}

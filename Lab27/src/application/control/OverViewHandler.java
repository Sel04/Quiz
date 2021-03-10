package application.control;

import application.view.OverView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class OverViewHandler implements EventHandler<ActionEvent> {
    public OverView ov;
    
    public OverViewHandler(OverView ov) {
    	this.ov=ov;
    }
	@Override
	public void handle(ActionEvent a) {
		// TODO Auto-generated method stub
		String dat=null;
		
		if(a.getSource() instanceof Button) {
			dat=(String)((Button)a.getSource()).getUserData();
			switch(dat) {
			
			case "deleteHistory":
				ov.presenter.deleteHistory();
		
				break;
			}
		}
	}
	

}

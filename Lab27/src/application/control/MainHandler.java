package application.control;

import java.io.IOException;

import application.MainView;
import application.ModelInitializer;
import application.view.QuizView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MainHandler implements EventHandler<ActionEvent> {
    public MainView mv;


    
    public MainHandler(MainView mv) {
    	this.mv=mv;
    }
    

	@Override
	public void handle(ActionEvent a) {
		// TODO Auto-generated method stub
		String data=null;
		
		if(a.getSource() instanceof MenuItem) {
			data=(String)((MenuItem)a.getSource()).getUserData();
			
			switch(data) {
			case "Start":
				mv.disableQuizContinue();
	             mv.presenter.startQuizView();
	             
	             mv.presenter.showQuizView(mv.presenter.getQuestionType());

	             break;
	             
			
	             
			case "continue":
				 mv.presenter.showQuizView(mv.presenter.getQuestionType());
				 break;
				 
			case "Before":
				mv.disableQuizContinue();
				mv.presenter.beforeQuestion();
				
				mv.presenter.showQuizView(mv.presenter.getQuestionType());
				break;
				 
			
				 
			case "tableView":
				mv.disableQuizContinue();
	            mv. presenter.showOverviewView();
	            break;
	            
			case "Bearbeiten":
				 mv.quizContinue.setDisable(true);
	            mv. presenter.showEditorView();
	            break;
				
			}
		}
	}

}

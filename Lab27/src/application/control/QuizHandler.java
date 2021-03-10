package application.control;

import application.view.QuizView;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.util.Duration;

public class QuizHandler implements EventHandler<ActionEvent> {
   public QuizView qu;
   
   public QuizHandler(QuizView qu) {
	   this.qu=qu;
	   	   
   }
   
   
 
   
   
   
   PauseTransition ps=new PauseTransition(new Duration(30));
   
  
	@Override
	public void handle(ActionEvent a) {
		// TODO Auto-generated method stub
		String data="";
		if(a.getSource() instanceof Button) {
			data=(String)((Button)a.getSource()).getUserData();
			switch(data) {
			case "Weiter":
				 RadioButton answerSelected = null;

		          if (qu.toggleGroup.getSelectedToggle() != null) {
		              answerSelected = (RadioButton) qu.toggleGroup.getSelectedToggle();
		          }
		          
		        

		          qu.presenter.validateAnswer(answerSelected);
		          
		         

		         qu. presenter.nextQuestion();
		          qu.setSelected();
		          qu.showQuestion(qu.presenter.getQuestionType());
		          
		          break;
			}
		}
		
	}
	
	

}

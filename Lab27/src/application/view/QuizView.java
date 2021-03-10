package application.view;

import java.io.File;
import java.net.URL;
import java.util.Optional;

import application.control.QuizHandler;
import application.model.Model;
import application.model.Question;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuizView extends BorderPane {
  public Label l;
  public Label s;
  public Label label;
  public Label label2=new Label();
  public Integer startTime=0;
  public Integer seconds=startTime;
  public Integer minutes=0;
  public QuizPresenter presenter;
  public  Model model=new Model();
  public RadioButton s1,s2,s3,s4;
	public Timeline t=new Timeline();
  
  Media m=new Media(new File("sel.m4a").toURI().toString());
  Media m2=new Media(new File("gameover.m4a").toURI().toString());
  
  ImageView imv=new ImageView(new Image("quiz2.jpg"));
  
  MediaPlayer ma=new MediaPlayer(m);
  MediaPlayer ma2=new MediaPlayer(m2);
  
  
 
  
 
  

  
  public Button nextQuestion,beforeQuestion;
  
  public Button start,stop,start2;
  
  TextField tfName=new TextField();
	TextField tfAddress=new TextField();
	Button b=new Button("Anmelden");
  

  public ToggleGroup toggleGroup;
  
 
  
  QuizHandler qu;
 public GridPane gp=new GridPane();

Stage stage;
  public QuizView(Stage stage) {
	  this.stage=stage;

	  qu=new QuizHandler(this);
      initView();
  


      
  }
  

  
 
  

  
  public void doTime() {
  
  	t.setCycleCount(Timeline.INDEFINITE);
  	
  	if(t!=null) {
  		t.stop();
  	}
  	
  	
  	KeyFrame frame=new KeyFrame(Duration.seconds(1),new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				seconds+=1;
				label.setText("Zeit : "+minutes.toString() + " : " + " 0" +seconds.toString());
				label.setFont(Font.font(175));
				label.setTextFill(Color.RED);
			
				
				
			   if(seconds>=59) {
				   minutes++;
				   seconds=0;
				   label.setText("Zeit : "  +minutes.toString() + " : " +seconds.toString());
			   }
			     
				
				
				if(minutes<10) {
					label.setText("Zeit : "  +minutes.toString() + " : " +seconds.toString());
				}
				
				

				
				
				
				
				
				if(seconds<10) {
					label.setText("Zeit : "+minutes.toString() + " : " + " 0" +seconds.toString());
				}
				
				if(seconds>=11) {
					label.setText("Zeit : "+minutes.toString() + " : " +seconds.toString());
				}
				
				
			}
  		
  	});
  	t.getKeyFrames().add(frame);
  
  
  	
  

  }


  public void initView() {
     gp. setHgap(10);
     gp. setVgap(10);
     gp.setBackground(new Background(new BackgroundImage(imv.getImage(),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));
     toggleGroup = new ToggleGroup();

      l = new Label();
      s=new Label();
      s.setText("Bitte beantworten sie die Fragen");
      s.setFont(Font.font(100));
      s.setTextFill(Color.BLACK);
      gp.add(s, 0, 0);
      gp.add(l, 0, 1);

      s1 = new RadioButton();
  
      gp.add(s1, 0, 2);
      s2 = new RadioButton();
      gp.add(s2, 0, 3);
      s3 = new RadioButton();
      gp.add(s3, 0, 4);
     
    
     
      toggleGroup.getToggles().addAll(s1, s2, s3);

      nextQuestion = new Button("Nächste Frage");
      nextQuestion.setUserData("Weiter");
      nextQuestion.setOnAction(qu);
      
      beforeQuestion=new Button("Vorherige Frage");
      beforeQuestion.setUserData("Before");
      beforeQuestion.setOnAction(qu);
      
      label=new Label();
      doTime();
      
      start=new Button("Start");
      start.setOnAction(e ->{
    	  t.play();
      });
      
     
      
      start2=new Button("Reset");
      start2.setOnAction(e ->{
    	  minutes=0;
    	  seconds=-1;
    	  t.playFromStart();
      });
      gp.add(start, 0, 10);

      gp.add(start2, 2, 10);
         
      gp.add(nextQuestion, 0, 6);
      gp.add(label, 0,9);
      
     
    
      gp.setAlignment(Pos.TOP_CENTER);
      setCenter(gp);
       
   

     
  }

  
 

  public void setLabelText(String question) {
      l.setText(question);
      l.setWrapText(true);
      l.setTextFill(Color.RED);
      l.setFont(Font.font(50));
      l.setLayoutX(100);
      
  }
  

  
  public void setPresenter(QuizPresenter presenter) {
      this.presenter = presenter;
  }
  
  public void showQuestion(Question question) {
      if (question != null) {
          setLabelText(presenter.getQuestion());
          setRadioButtonText(presenter.getPossibleAnswers());
          enableRadioButton();
          nextQuestion.setDisable(false);
          t.playFromStart();
      } else {
         setLabelText("Congratulation you complete this Quiz");
         l.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(50), null, null)));
          l.setFont(Font.font(80));
          l.setTextFill(Color.RED);
         
          
    

          
          ma.play();
    
          t.stop();
          disableRadioButton();
          nextQuestion.setDisable(true);


      }
  }

  public void setRadioButtonText(String[] possibleAnswers) {
      s1.setText(possibleAnswers[0]);
      s2.setText(possibleAnswers[1]);
      s3.setText(possibleAnswers[2]);
  }

  public void disableRadioButton() {
      s1.setVisible(false);
      s2.setVisible(false);
      s3.setVisible(false);
  }

  public void enableRadioButton() {
      s1.setVisible(true);
      s2.setVisible(true);
      s3.setVisible(true);
  }
  
  public void disable2() {
	  s1.setDisable(true);
	  s2.setDisable(true);
	  s3.setDisable(true);
  }
  
 
 
  public void setSelected() {
      toggleGroup.selectToggle(null);
  }

}


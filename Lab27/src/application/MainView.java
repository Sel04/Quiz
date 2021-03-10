package application;

import java.util.Optional;



import application.control.MainHandler;
import application.view.QuizView;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainView extends BorderPane {
    public MainPresenter presenter;
   
    private MenuItem quizStart;

	public MenuItem quizContinue;

	private MenuItem quizOverview;

	private MenuItem quizEditor;
	
	public MenuItem listView,start2,before;
	
	public FadeTransition ani=new FadeTransition();
	
    private MenuItem quizStart2;

	public MenuItem quizContinue2;

	private MenuItem quizOverview2;

	private MenuItem quizEditor2;
	
	public MenuItem listView2,start3,before2;
	
   public void setPresenter1(MainPresenter presenter) {
	   this.presenter=presenter;
   }
	
	public Label l=new Label();
    public MainHandler mv;
    Stage stage;
    public MainView(Stage stage) {
    	this.stage=stage;
    	mv=new MainHandler(this);
        initView();
        
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
    
  
    
  

    private void initView() {
    	MenuBar mb=new MenuBar();

    	 Menu m = new Menu("POS");
    	 Menu m3=new Menu("Mathe");
    	 quizStart2=new MenuItem("Starten");
    	 quizStart2.setUserData("Start2");
    	 quizStart2.setOnAction(mv);

    	 quizStart2.setMnemonicParsing(true);
    	 
    	 quizContinue2=new MenuItem("Continue");
    	 quizContinue2.setUserData("Continue2");
    	 quizContinue2.setOnAction(mv);
    	 
    	 quizOverview2=new MenuItem("OverView");
    	 quizOverview2.setUserData("Überblick2");
    	 quizOverview2.setOnAction(mv);
    	 
    	 quizEditor2=new MenuItem("Bearbeiten");
    	 quizEditor2.setUserData("Bearbeiten2");
    	 quizEditor2.setOnAction(mv);
    	 
    	 before2=new MenuItem("Vorherige Frage");
    	 before2=new MenuItem("Zurück");
    	 
    	 
    	 Menu m1=new Menu("TableView");
    	 Menu m2=new Menu("Bearbeiten");

         quizStart = new MenuItem("Starten");
         quizStart.setUserData("Start");
         quizStart.setOnAction(mv);
        
         start2=new MenuItem("Quiz");
         start2.setUserData("Starten");
         start2.setOnAction(mv);
         before=new MenuItem("BeforeQuestion");
         before.setUserData("Before");
         before.setOnAction(mv);
         quizContinue = new MenuItem("Quiz fortsetzen");
         ImageView a= new ImageView(new Image("start.jpg"));
         a.setFitWidth(50);
         a.setFitHeight(50);
         quizStart.setGraphic(a);
         ImageView a2= new ImageView(new Image("continue.png"));
         a2.setFitWidth(50);
         a2.setFitHeight(50);
         quizContinue.setUserData("continue");
         quizContinue.setGraphic(a2);
         quizContinue.setOnAction(mv);
         quizOverview = new MenuItem("Überblick");
         ImageView a3= new ImageView(new Image("überblick.png"));
         a3.setFitWidth(50);
         a3.setFitHeight(50);
         quizOverview.setUserData("tableView");
         quizOverview.setGraphic(a3);
         quizOverview.setOnAction(mv);

         quizEditor = new MenuItem("Quiz Bearbeiten");
         ImageView a4= new ImageView(new Image("bearbeiten.png"));
         a4.setFitWidth(50);
         a4.setFitHeight(50);
         quizEditor.setUserData("Bearbeiten");
         quizEditor.setGraphic(a4);
         quizEditor.setOnAction(mv); 
         listView=new MenuItem("ListView");
         listView.setUserData("ListView");
         listView.setOnAction(mv);
         m.getItems().addAll(quizStart,quizContinue,before);
         m1.getItems().addAll(quizOverview);
         m2.getItems().addAll(quizEditor);
         
         mb.getMenus().addAll(m,m1,m2);
         setTop(mb);

     }

    

    public void setContent(Pane content) {
        setCenter(content);
        setMargin(content, new Insets(20, 20, 20, 20));
    }

    public void disableQuizContinue() {
        quizContinue.setDisable(false);
    }
    
    public void disableQuizContinue2() {
        quizContinue2.setDisable(false);
    }
}
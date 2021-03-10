package application.view;

import application.control.OverViewHandler;
import application.model.Question;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OverView extends VBox {


	 public OverViewPresenter presenter;

	    private Label overviewLabel;

	    private TableView<Question> overviewTable;

	    private TableColumn<Question, String> questionCol;

	    private TableColumn<Question, Integer> answersGivenCol;

	    private TableColumn<Question, Integer> answersCorrectGivenCol;
	    
	    private RadioButton anzahlPunkte;
	    
	    ImageView imv=new ImageView(new Image("hn.jpg"));
	    
	    private Button note=new Button("note");
	    
	    private ToggleGroup a=new ToggleGroup();

	    public TableView<Question> getOverviewList() {
	        return overviewTable;
	    }

	    public void setOverviewTable(TableView<Question> overviewTable) {
	        this.overviewTable = overviewTable;
	    }

	    private Button deleteHistory;
        OverViewHandler ov;
	    public OverView() {
	    	ov=new OverViewHandler(this);
	        initOverviewView();
	    }

	    private void initOverviewView() {
	    	setBackground(new Background(new BackgroundImage(imv.getImage(),BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,null,null)));
	    	 overviewLabel = new Label("Überblick:");
	    	 overviewLabel.setTextFill(Color.RED);
	    	 overviewLabel.setFont(Font.font(40));
	         getChildren().add(overviewLabel);

	         overviewTable = new TableView<>();
	    
	         overviewTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	         getChildren().add(overviewTable);

	         questionCol = new TableColumn<Question, String>("Fragen");
	         questionCol.setUserData("questionCol");
	         questionCol.setCellValueFactory(item -> item.getValue().getQuestionProperty());
	         overviewTable.getColumns().add(questionCol);
	         answersGivenCol = new TableColumn<Question, Integer>("Abgegebene Antworten");
	         answersGivenCol.setId("totalAnswerCol");
	         answersGivenCol.setCellValueFactory(item -> item.getValue().getSumOfAllAnswersGiven().asObject());
	         overviewTable.getColumns().add(answersGivenCol);
	         answersCorrectGivenCol = new TableColumn<Question, Integer>("Richtige Antworten");
	         answersCorrectGivenCol.setId("correctAnswerCol");
	         answersCorrectGivenCol.setCellValueFactory(item -> item.getValue().getCorrectAnswersGiven().asObject());
	         overviewTable.getColumns().add(answersCorrectGivenCol);
             anzahlPunkte=new RadioButton("SortNachQuestion");
             anzahlPunkte.setToggleGroup(a);
            
	         deleteHistory = new Button("Ergebnisse löschen");
	         deleteHistory.setUserData("deleteHistory");
	         deleteHistory.setOnAction(ov);
	        
	         getChildren().addAll(deleteHistory,anzahlPunkte);
	         
	    }

	    public void setPresenter(OverViewPresenter presenter) {
	        this.presenter = presenter;
	    }
	    
	    

	    public void clearListView() {
	        overviewTable.getItems().clear();
	    }
	}

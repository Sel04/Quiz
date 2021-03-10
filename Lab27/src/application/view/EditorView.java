package application.view;

import java.io.File;
import java.util.Optional;

import application.control.EditorHandler;
import application.model.Model;
import application.model.Question;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class EditorView extends BorderPane {

	private EditorPresenetr presenter;

    private Label label;
    
    public Model model;

    private ListView<Question> editorList;

    private Button addQuestion, editQuestion, deleteQuestion,ausgeben;
    
   public  Media m2=new Media(new File("error.m4a").toURI().toString());

   public void setModel(Model model) {
	   this.model=model;
   }
   
   
    
  public   MediaPlayer ma2=new MediaPlayer(m2);
  PauseTransition ps=new PauseTransition(new Duration(30));

    private TextArea dialogQuestion;

    private Label dialogLabel;
   public  EditorHandler ev;
    public EditorView() {
    	ev=new EditorHandler(this);
        initEditorView();
    }

    public void initEditorView() {
        HBox bottomArea = new HBox();
        VBox centerArea = new VBox();
        label = new Label("Editor");
        editorList = new ListView<>();
        editorList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        addQuestion = new Button("Fragen hinzufügen");
        addQuestion.setUserData("adden");
        addQuestion.setOnAction(ev);
        editQuestion = new Button("Fragen bearbeiten");
        editQuestion.setUserData("bearbeiten");
        editQuestion.setOnAction(ev);
        deleteQuestion = new Button("Fragen löschen");
        deleteQuestion.setUserData("löschen");
        deleteQuestion.setOnAction(ev);
        ausgeben=new Button("Ausgeben");
        ausgeben.setUserData("Ausgeben");
        ausgeben.setOnAction(ev);
        bottomArea.getChildren().addAll(addQuestion, editQuestion, deleteQuestion,ausgeben);
        centerArea.getChildren().addAll(label, editorList, bottomArea);
        setCenter(centerArea);

    }

    public void setPresenter(EditorPresenetr presenter) {
        this.presenter = presenter;
    }

    public ListView<Question> getEditorList() {
        return editorList;
    }
    

    public void clearEditorList() {
        editorList.getItems().clear();
    }

    public void questionDelete() {
        if (!editorList.getSelectionModel().isEmpty()) {
            Alert alertConfirmation = new Alert(AlertType.CONFIRMATION);
            alertConfirmation.setTitle("Frage");
            alertConfirmation.setHeaderText(null);
            alertConfirmation.setContentText("Are you sure about to delete this Question??");
            Optional<ButtonType> result = alertConfirmation.showAndWait();
            if (result.get() == ButtonType.OK) {
                presenter.deleteQuestion();
                presenter.updateListView();
            }
        } else {
            Alert alertInformation = new Alert(AlertType.ERROR);
            alertInformation.setTitle("Information");
            alertInformation.setHeaderText(null);
            alertInformation.setContentText("Bitte eine Frage wählen!");
           
            alertInformation.showAndWait();
            
        }
    }

    public void questionEdit() {
        if (!editorList.getSelectionModel().isEmpty()) {
            ButtonType questionEdit = new ButtonType("Bestätigen", ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
            Alert dialog = new Alert(AlertType.CONFIRMATION);
            DialogPane dialogPane = dialog.getDialogPane();

            dialog.getButtonTypes().clear();
            dialog.getButtonTypes().add(cancel);
            dialog.getButtonTypes().add(questionEdit);

            dialogQuestion = new TextArea();
            dialogQuestion.setEditable(true);
            dialogQuestion.setText(editorList.getSelectionModel().getSelectedItem().getQuestion());

            dialogLabel = new Label("Frage:");
            ToggleGroup tg = new ToggleGroup();

            RadioButton rb0 = new RadioButton();
           
            RadioButton rb1 = new RadioButton();
            RadioButton rb2 = new RadioButton();
            tg.getToggles().addAll(rb0, rb1, rb2);
            tg.getToggles().get(editorList.getSelectionModel().getSelectedItem().getIndexOfCorrectAnswer()).setSelected(true);

            TextField tf0 = new TextField(presenter.getAnswerAt(0));
            TextField tf1 = new TextField(presenter.getAnswerAt(1));
            TextField tf2 = new TextField(presenter.getAnswerAt(2));

            HBox hb0 = new HBox();
            HBox hb1 = new HBox();
            HBox hb2 = new HBox();
            hb0.getChildren().addAll(rb0, tf0);
            hb1.getChildren().addAll(rb1, tf1);
            hb2.getChildren().addAll(rb2, tf2);

            GridPane gp = new GridPane();
            gp.add(dialogLabel, 0, 0);
            gp.add(dialogQuestion, 0, 1);
            gp.add(hb0, 0, 2);
            gp.add(hb1, 0, 3);
            gp.add(hb2, 0, 4);

            dialog.getDialogPane().setContent(gp);
            dialog.setTitle("Dialog");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            dialog.setDialogPane(dialogPane);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == questionEdit) {
                if (tg.getToggles().isEmpty()) {
                	
                    Alert warning = new Alert(AlertType.ERROR);
                    warning.setTitle("Information");
                    warning.setHeaderText(null);
                    warning.setContentText("Bitte eine Antwort auswählen!!");
                    ma2.play();
                    warning.showAndWait();
                    dialog.showAndWait();

                } else {
                    String[] possibleAnswers = { tf0.getText(), tf1.getText(), tf2.getText()};
                    Question tmp = new Question(dialogQuestion.getText(), possibleAnswers, tg.getToggles().indexOf(tg.getSelectedToggle()));

                    if (presenter.getQuestionAtKey(editorList.getSelectionModel().getSelectedIndex()) != tmp) {
                        presenter.overwriteQuestion(tmp, editorList.getSelectionModel().getSelectedIndex());
                        presenter.updateListView();
                    }

                }
            }
        } else {
            Alert alertInformation = new Alert(AlertType.ERROR);
            alertInformation.setTitle("Information");
            alertInformation.setHeaderText(null);
            alertInformation.setContentText("Es muss eine Frage ausgewählt werden!");
            ma2.play();
            alertInformation.showAndWait();
       
        }
    }

    public void questionAdd() {
        ButtonType buttonAdd = new ButtonType("Hinzuf\u00fcgen", ButtonData.OK_DONE);
        ButtonType buttonCancel = new ButtonType("Abbrechen", ButtonData.CANCEL_CLOSE);
        Alert dialog = new Alert(AlertType.ERROR);
        dialog.getButtonTypes().clear();
        dialog.getButtonTypes().add(buttonAdd);
        dialog.getButtonTypes().add(buttonCancel);
        dialog.setTitle("Dialog");

        dialogLabel = new Label("Frage:");
        dialogQuestion = new TextArea();
        dialogQuestion.setEditable(true);
        dialogQuestion.setId("dialogQuestion");
        ToggleGroup tg = new ToggleGroup();

        RadioButton rb0 = new RadioButton();
        RadioButton rb1 = new RadioButton();
        RadioButton rb2 = new RadioButton();
        tg.getToggles().addAll(rb0, rb1, rb2);

        TextField tf0 = new TextField();
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
      
        
      

        HBox hb0 = new HBox();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();

        hb0.getChildren().addAll(rb0, tf0);
        hb1.getChildren().addAll(rb1, tf1);
        hb2.getChildren().addAll(rb2, tf2);

        GridPane gp = new GridPane();
        gp.add(dialogLabel, 0, 0);
        gp.add(dialogQuestion, 0, 1);
        gp.add(hb0, 0, 2);
        gp.add(hb1, 0, 3);
        gp.add(hb2, 0, 4);
        
        
        
        

        dialog.getDialogPane().setContent(gp);
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        Optional<ButtonType> result = dialog.showAndWait();
      
        	if(result.isPresent()) {
            if (tg.getSelectedToggle()==null) {
                Alert warning = new Alert(AlertType.ERROR);
                warning.setTitle("Information");
                warning.setHeaderText(null);
                warning.setContentText("Es muss eine Antwort ausgew\u00e4hlt werden!");
                ma2.play();
                warning.showAndWait();
                dialog.showAndWait();
           
            } else {
                String[] possibleAnswers = { tf0.getText(), tf1.getText(), tf2.getText()};
                Question tmp = new Question(dialogQuestion.getText(), possibleAnswers, tg.getToggles().indexOf(tg.getSelectedToggle()));
                presenter.addNewQuestion(tmp);
                presenter.updateListView();
            }
            
            
    }
    }

            
            
        public void ausgeben() {
        	Alert a=new Alert(AlertType.INFORMATION);
        	if(editorList.getSelectionModel().getSelectedItem()!=null) {
        		a.setHeaderText("Ausgebe");
        		a.setContentText( "Frage : " +editorList.getSelectionModel().getSelectedItem().getQuestion()  + " Antwort = " + editorList.getSelectionModel().getSelectedItem().getCorrectAnswer());
        		a.showAndWait();
        	}
        }
  

    

    public TextArea getDialogTextArea() {
        return dialogQuestion;
    }

    public Label getDialogLabel() {
        return dialogLabel;
    }

    public ListView<Question> getListView() {
        return editorList;
    }

}
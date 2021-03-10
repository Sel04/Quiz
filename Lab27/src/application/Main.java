package application;

import java.io.IOException;

import application.model.Model;
import application.view.EditorPresenetr;
import application.view.EditorView;
import application.view.OverView;
import application.view.OverViewPresenter;
import application.view.QuizPresenter;
import application.view.QuizView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception {
    	MainPresenter mainPresenter = initApplication(primaryStage);
        Scene scene = new Scene(mainPresenter.getView(), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sel-Quiz");
        primaryStage.getIcons().add(new Image("quiz.png"));
        primaryStage.show();
    }

    private MainPresenter initApplication(Stage stage) throws IOException {
        Model model = ModelInitializer.initModel("t2.txt");
        MainPresenter mainPresenter = new MainPresenter();
        MainView mainView=new MainView(stage);
   
        QuizPresenter quizPresenter = new QuizPresenter();
        QuizView quizView = new QuizView(stage);
        
        OverViewPresenter overviewPresenter = new OverViewPresenter();
        OverView overviewView = new OverView();
        EditorPresenetr editorPresenter = new EditorPresenetr();
        EditorView editorView = new EditorView();

        mainPresenter.setView(mainView);
        mainPresenter.setQuizPresenter(quizPresenter);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainView.setPresenter(mainPresenter);
        mainPresenter.setModel(model);
        mainPresenter.setEditorPresenter(editorPresenter);

        quizPresenter.setView(quizView);
        quizPresenter.setMainPresenter(mainPresenter);

        quizPresenter.setModel(model);
        quizView.setPresenter(quizPresenter);
        quizView.setLabelText(model.getQuestion());
        quizView.setRadioButtonText(model.getPossibleAnswers());
        
  

        overviewPresenter.setView(overviewView);
        overviewPresenter.setMainPresenter(mainPresenter);
        overviewPresenter.setModel(model);
        overviewView.setPresenter(overviewPresenter);

        editorPresenter.setView(editorView);
        editorPresenter.setMainPresenter(mainPresenter);
        editorPresenter.setModel(model);
        editorView.setPresenter(editorPresenter);

        mainPresenter.showQuizView(model.getQuestionType());

        return mainPresenter;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
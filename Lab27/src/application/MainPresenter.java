package application;

import application.model.Model;
import application.model.Question;
import application.view.EditorPresenetr;
import application.view.OverViewPresenter;
import application.view.QuizPresenter;
import javafx.scene.layout.BorderPane;




public class MainPresenter extends BorderPane {
    private MainView view;

    private OverViewPresenter overviewPresenter;

    private QuizPresenter quizPresenter;

    private EditorPresenetr editorPresenter;

    private Model model;

    public MainPresenter() {

    }

    public void setView(MainView view) {
        this.view = view;
    }

    public MainView getView() {
        return view;
    }
    
   
    public void setOverviewPresenter(OverViewPresenter overviewPresenter) {
        this.overviewPresenter = overviewPresenter;
    }

    public void setQuizPresenter(QuizPresenter quizPresenter) {
        this.quizPresenter = quizPresenter;
    }

    public void setEditorPresenter(EditorPresenetr editorPresenter) {
        this.editorPresenter = editorPresenter;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void showQuizView(Question question) {
        quizPresenter.setQuestion(question);
        view.setContent(quizPresenter.getView());
    }

    public void showOverviewView() {
        view.setContent(overviewPresenter.getView());
    }

    public void showEditorView() {
    	view.setContent(editorPresenter.getView());
      
    }
    

    public void startQuizView() {
        model.setCounter();
    }
    
    public void beforeQuestion() {
    	model.before();
    }

    public Question getQuestionType() {
        return model.getQuestionType();
    }

    public String getQuestion() {
        return model.getQuestion();
    }

    public String[] getPossibleAnswers() {
        return model.getPossibleAnswers();
    }

    public int getIndexOfCorrectAnswer() {
        return model.getIndexOfCorrectAnswer();
    }
    
 
  
    public void nextQuestion() {
        model.nextQuestion();
    }

}
package application.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.MainPresenter;
import application.model.Model;
import application.model.Question;
import javafx.scene.control.RadioButton;

public class QuizPresenter {
    private QuizView view;

    private MainPresenter mainPresenter;

    private Model model;

    public QuizPresenter() {

    }

    public void setView(QuizView view) {
        this.view = view;
    }

    public QuizView getView() {
        return view;
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setQuestion(Question question) {
        view.showQuestion(question);
    }

    public Question getQuestionType() {
        return mainPresenter.getQuestionType();
    }

    public String getQuestion() {
        return mainPresenter.getQuestion();
    }

    public String[] getPossibleAnswers() {
        return mainPresenter.getPossibleAnswers();
    }

    public int getIndexOfCorrectAnswer() {
        return mainPresenter.getIndexOfCorrectAnswer();
    }
    
  

    public void nextQuestion() {
        mainPresenter.nextQuestion();
    }
    
    

    public void validateAnswer(RadioButton rb) {
        if (rb == null) {
            model.getQuestions().get(model.getCounter()).answerGiven(false);
            return;
        }

        String answer = rb.getText();

        if (answer.equals(model.getQuestions().get(model.getCounter()).getCorrectAnswer())) {
            model.getQuestions().get(model.getCounter()).answerGiven(true);
            
        } else {
            model.getQuestions().get(model.getCounter()).answerGiven(false);
        }

    }

}

package application.model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Model {
    private int key = 0;
    
    public Question r=new Question();

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
  boolean a=true;

    private int counter = 0;
    
    private int ergebnis;

    public int getCounter() {
        return counter;
    }

    private Map<Integer, Question> questions;
    
    private ObservableList<Question> qu=FXCollections.observableArrayList();

    public Map<Integer, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, Question> questions) {
        this.questions = questions;
    }

    public Model() {
        questions = new HashMap<>();
    }

    public void addQuestion(Question question) {
        question.setKey(key);
        questions.put(key, question);
        key++;
    }

    public Question getQuestionType() {
        return questions.get(counter);
    }

    public String getQuestion() {
        return questions.get(counter).getQuestionProperty().get();
    }

    public String[] getPossibleAnswers() {
        return questions.get(counter).getPossibleAnswers();
    }

    public int getIndexOfCorrectAnswer() {
        return questions.get(counter).getIndexOfCorrectAnswer();
    
    }
    
  

    public void nextQuestion() {
        counter++;
    }
    
    public void before() {
    	counter--;
    }

    public void setCounter() {
        this.counter = 0;

    }
    
    public int setErgebnis() {
    
    	 return ergebnis+= r.getCorrectAnswersGiven().get();
    
    }
    
    

    public Question getNextQuestion() {
        nextQuestion();
        return questions.get(counter);
    }

    public String getAnswerAt(int i) {
        return (String) Array.get(getPossibleAnswers(), i);
    }

    public Question getQuestionAtKey(int k) {
        return questions.get(k);
    }

    public void overwriteQuestion(Question q, int k) {
        questions.put(k, q);
    }
    
    public void ausgeben() {
    	for(Question s:questions.values()) {
    		Alert a=new Alert(AlertType.INFORMATION);
    		a.setContentText(s.toString());
    		}
    	}
    }


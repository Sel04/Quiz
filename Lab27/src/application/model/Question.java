package application.model;

import java.util.Collections;
import java.util.Comparator;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Question {
	 private SimpleStringProperty question = new SimpleStringProperty();
        ObservableList<Question> s=FXCollections.observableArrayList();
	    private int key;
	    
	    public int sumCorrectAnswers=0;

	    public int getKey() {
	        return key;
	    }

	    public void setKey(int key) {
	        this.key = key;
	    }

	    private String[] possibleAnswers;

	    private int indexOfCorrectAnswer;

	    private SimpleIntegerProperty correctAnswersGiven = new SimpleIntegerProperty();

	    private SimpleIntegerProperty sumOfAllAnswersGiven = new SimpleIntegerProperty();

	    // weitere Attribute nach Bedarf
	    public Question(String question, String[] possibleAnswers, int indexOfCorrectAnswer) {
	       
	            setQuestion(question);
	            setPossibleAnswers(possibleAnswers);
	            this.indexOfCorrectAnswer = indexOfCorrectAnswer;
	       
	    }
	    
	    public Question() {
	    	
	    }
	    
	 

	    public String getQuestion() {
	        return question.get();
	    }

	    public SimpleStringProperty getQuestionProperty() {
	        return question;
	    }
	    
	    public void setQuestion(String question) {
	    	this.question.set(question);
	    }

	    public String[] getPossibleAnswers() {
	        return possibleAnswers;
	    }
	    
	    public void setPossibleAnswers(String[] possibleAnswers) {
	    	this.possibleAnswers=possibleAnswers;
	    }

	    public int getIndexOfCorrectAnswer() {
	        return indexOfCorrectAnswer;
	    }

	    public String getCorrectAnswer() {
	        return possibleAnswers[indexOfCorrectAnswer];
	    }

	    public void answerGiven(boolean correctness) {
	        if (correctness) {
	            setCorrectAnswersGiven(getCorrectAnswersGiven().get() + 1);

	        }

	        setSumOfAllAnswersGiven(getSumOfAllAnswersGiven().get() + 1);
	    }

	    public void clearAllAnswersGiven() {
	        setCorrectAnswersGiven(setSumOfAllAnswersGiven(0));
	    }
	 

	    public SimpleIntegerProperty getCorrectAnswersGiven() {
	        return correctAnswersGiven;
	    }
	    
	   
	    public void setCorrectAnswersGiven(int correctAnswersGiven) {
	        this.correctAnswersGiven.set(correctAnswersGiven);
	    }

	    public SimpleIntegerProperty getSumOfAllAnswersGiven() {
	        return sumOfAllAnswersGiven;
	    }

	    public int setSumOfAllAnswersGiven(int s) {
	        this.sumOfAllAnswersGiven.set(s);
	        return sumOfAllAnswersGiven.get();
	    }

	    @Override
	    public String toString() {
	        return getQuestionProperty().get();
	    }

	
}


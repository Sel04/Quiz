package application.view;

import application.MainPresenter;
import application.model.Model;
import application.model.Question;

public class OverViewPresenter {
	 private OverView view;

	    private MainPresenter mainPresenter;

	    private Model model;

	    public OverViewPresenter() {

	    }

	    public void setView(OverView view) {
	        this.view = view;
	    }

	    public OverView getView() {
	        updateListView();
	        return view;
	    }

	    public void setMainPresenter(MainPresenter mainPresenter) {
	        this.mainPresenter = mainPresenter;
	    }

	    public void setModel(Model model) {
	        this.model = model;
	    }

	    public int getIndexOfCorrectAnswer() {
	        return mainPresenter.getIndexOfCorrectAnswer();
	    }
	    
	  

	    public void questionSelected() {
	        mainPresenter.getQuestion();
	    }

	    public void updateListView() {

	        view.clearListView();

	        for (int i = 0; i < model.getKey(); i++) {

	            Question q = model.getQuestions().get(i);
	            view.getOverviewList().getItems().add(q);


	        }

	    }

	    public void deleteHistory() {

	        for (int i = 0; i < model.getKey(); i++) {

	            Question q = model.getQuestions().get(i);
	            q.clearAllAnswersGiven();
	        }
	        
	        updateListView();


	    }
	}
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import application.model.Model;
import application.model.Question;
import application.view.QuizView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class ModelInitializer {

    private static final char ANSWER_STARTS_SYMBOL = '{';

    private static final char ANSWER_ENDS_SYMBOL = '}';

    private static final char ANSWER_SEPERATION_SYMBOL = ';';
  Stage stage;
    QuizView qu=new QuizView(null);
  
    public static Model initModel(String pathName) throws IOException {
        Model m = new Model();
       
        
        try (BufferedReader in = new BufferedReader(new FileReader(pathName))) {
        	
            String line;
            while ((line = in.readLine()) != null) {
                try {
                    Question q = readQuestion(line);
                 
                    m.addQuestion(q);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                 e.printStackTrace();
                }

            }
        }

        return m;
    }

    private static Question readQuestion(String line) {
        int indexAnswerStart = line.indexOf(ANSWER_STARTS_SYMBOL);
        int indexAnswerEnds = line.indexOf(ANSWER_ENDS_SYMBOL, indexAnswerStart);

        checkIndices(indexAnswerStart, indexAnswerEnds);

        String question = getQuestion(line, indexAnswerStart);
        String[] possibleAnswers = getPossibleAnswers(line.substring(indexAnswerStart + 1, indexAnswerEnds + 1));
        int indexOfCorrectAnswer = getIndexOfCorrectAnswer(line.substring(indexAnswerEnds + 1));

        return new Question(question, possibleAnswers, indexOfCorrectAnswer);
    }

    private static void checkIndices(int indexAnswerStart, int indexAnswerEnds) {
        if (indexAnswerStart <= 0) {
            throw new IndexOutOfBoundsException("Es konnte kein (oder nur an erster Stelle) Symbol \"" + ANSWER_STARTS_SYMBOL + "\" zur Kennzeichnung des Antwortenbeginns gefunden werden. " + "Demzufolge k�nnen weder Frage noch Antwortm�glichkeiten klar voneinander abgegrenzt werden.");
        }
        if (indexAnswerEnds < 0 || indexAnswerEnds <= indexAnswerStart) {
            throw new IndexOutOfBoundsException("Es konnte kein Symbol \"" + ANSWER_ENDS_SYMBOL + "\" zur Kennzeichnung des Endes der Antwortm�glichkeiten hinter den Antworten gefunden werden.");
        }
    }

    private static String getQuestion(String line, int questionEndsIndex) {
        return line.substring(0, questionEndsIndex).trim();
    }

    private static String[] getPossibleAnswers(String answerString) {
        int index=0;
        List<String> answers = new LinkedList<String>();
        while ((index = answerString.indexOf(ANSWER_SEPERATION_SYMBOL)) != -1) {
            answers.add(answerString.substring(0, index).trim());
            answerString = answerString.substring(index+2);
        }
        answerString = answerString.substring(0, answerString.length() - 1);
        answers.add(answerString.trim());
        return answers.toArray(new String[answers.size()]);
    }

    private static int getIndexOfCorrectAnswer(String s) {
        return Integer.parseInt(s.replaceAll(",", "").trim());
    }
}

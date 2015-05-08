package pt.challenge_it.android.flag.millionaire.model;

import android.content.ContentValues;

/**
 * Class that defines the Question object model.
 *
 * @author Challenge.IT
 */
public class Question
{
    private static final int MAX_ANSWERS = 4;

    private final int _identifier;
    private final String _text;
    private final Answer[] _answers;
    private int _count;

    /**
     * Creates an instance of Question.
     * @param identifier The question's identifier.
     * @param text The question's text.
     */
    public Question(int identifier, String text)
    {
        _identifier = identifier;
        _text       = text;
        _answers    = new Answer[MAX_ANSWERS];
    }

    /**
     * @return The question's identifier.
     */
    public int getIdentifier() { return _identifier; }

    /**
     * @return The question's text.
     */
    public String getText() { return _text; }

    /**
     * @return The question's possible answers.
     */
    public Answer[] getAnswers() { return _answers; }

    /**
     * Adds one possible answer to the question.
     * This method is idempotent.
     * @param answer The possible answer reference.
     */
    public void addAnswer(Answer answer)
    {
        if(_count >= MAX_ANSWERS)
            return;

        _answers[_count++] = answer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(_identifier).append(" Text: ").append(_text).append(" Answers: ");
        for(Answer answer : _answers)
            sb.append(answer.toString());
        return sb.toString();
    }

    /**
     * Class that defines one possible question Answer.
     */
    public static class Answer
    {
        private final char _identifier;
        private final String _text;
        private final boolean _isCorrect;

        /**
         * Creates an instance of Answer.
         * @param identifier The answer's identifier.
         * @param text The answer's text.
         * @param isCorrect If true, this answer is correct.
         */
        public Answer(char identifier, String text, boolean isCorrect)
        {
            _identifier = identifier;
            _text       = text;
            _isCorrect  = isCorrect;
        }

        /**
         * @return The answer's identifier.
         */
        public char getIdentifier() { return _identifier; }

        /**
         * @return The answer's text.
         */
        public String getText() { return _text; }

        /**
         * @return True i this answer is correct, otherwise returns False.
         */
        public boolean isCorrect() { return _isCorrect; }

        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            return sb.append("ID: ").append(_identifier).append(" Text: ").append(_text).append(" Correct: ").append(_isCorrect).append(" ").toString();
        }
    }

    public static final String ID = "ID";
    public static final String TEXT = "TEXT";
    public static final String ANSWER_ID_1 = "ANSWER_ID_1";
    public static final String ANSWER_TEXT_1 = "ANSWER_TEXT_1";
    public static final String ANSWER_CORRECT_1 = "ANSWER_CORRECT_1";
    public static final String ANSWER_ID_2 = "ANSWER_ID_2";
    public static final String ANSWER_TEXT_2 = "ANSWER_TEXT_2";
    public static final String ANSWER_CORRECT_2 = "ANSWER_CORRECT_2";
    public static final String ANSWER_ID_3 = "ANSWER_ID_3";
    public static final String ANSWER_TEXT_3 = "ANSWER_TEXT_3";
    public static final String ANSWER_CORRECT_3 = "ANSWER_CORRECT_3";
    public static final String ANSWER_ID_4 = "ANSWER_ID_4";
    public static final String ANSWER_TEXT_4 = "ANSWER_TEXT_4";
    public static final String ANSWER_CORRECT_4 = "ANSWER_CORRECT_4";

    /**
     * Auxiliary method to create and initialize a {@link ContentValues} object.
     * @return {@link ContentValues} with current object properties.
     */
    public ContentValues getValues()
    {
        ContentValues values = new ContentValues();
        values.put(ID, _identifier);
        values.put(TEXT, _text);
        values.put(ANSWER_ID_1, Character.toString(_answers[0].getIdentifier()));
        values.put(ANSWER_TEXT_1, _answers[0].getText());
        values.put(ANSWER_CORRECT_1, _answers[0].isCorrect());
        values.put(ANSWER_ID_2, Character.toString(_answers[1].getIdentifier()));
        values.put(ANSWER_TEXT_2, _answers[1].getText());
        values.put(ANSWER_CORRECT_2, _answers[1].isCorrect());
        values.put(ANSWER_ID_3, Character.toString(_answers[2].getIdentifier()));
        values.put(ANSWER_TEXT_3, _answers[2].getText());
        values.put(ANSWER_CORRECT_3, _answers[2].isCorrect());
        values.put(ANSWER_ID_4, Character.toString(_answers[3].getIdentifier()));
        values.put(ANSWER_TEXT_4, _answers[3].getText());
        values.put(ANSWER_CORRECT_4, _answers[3].isCorrect());
        return values;
    }
}

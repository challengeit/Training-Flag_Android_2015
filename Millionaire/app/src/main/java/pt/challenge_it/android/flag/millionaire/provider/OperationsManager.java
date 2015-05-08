package pt.challenge_it.android.flag.millionaire.provider;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pt.challenge_it.android.flag.millionaire.model.Question;

/**
 * Helper class to define auxiliary methods that perform operations into {@link android.content.ContentProvider}.
 * @author Challenge.IT
 */
public class OperationsManager
{
    private final Context _context;

    /**
     * Constructor to build object with current {@link Context}.
     * @param context current application {@link Context}.
     */
    public OperationsManager(Context context)
    {
        _context = context;
    }

    /**
     * Operation to save one {@link Question}.
     * @param question to save.
     */
    public void save(Question question)
    {

        _context.getContentResolver().insert(QuestionContract.CONTENT_PROVIDER, question.getValues());
    }

    /**
     * Operation to get one {@link Question} by it's identifier.
     * @param id {@link Question} identifier.
     * @return {@link Question}
     */
    public Question getQuestionById(int id)
    {
        Cursor cursor = _context.getContentResolver().query(ContentUris.withAppendedId(QuestionContract.CONTENT_PROVIDER, id), null, null, null, null);
        cursor.moveToNext();
        Question question = new Question(cursor.getInt(cursor.getColumnIndex(QuestionContract.ID)), cursor.getString(cursor.getColumnIndex(QuestionContract.QUESTION)));
        cursor.close();

        cursor = _context.getContentResolver().query(ContentUris.withAppendedId(AnswerContract.CONTENT_PROVIDER, id), null, null, null, null);
        while(cursor.moveToNext())
        {
            question.addAnswer(new Question.Answer(cursor.getString(cursor.getColumnIndex(AnswerContract.ID)).charAt(0),
                    cursor.getString(cursor.getColumnIndex(AnswerContract.ANSWER)),
                    cursor.getInt(cursor.getColumnIndex(AnswerContract.CORRECT)) == 1));
        }
        cursor.close();
        return question;
    }

    /**
     * Operation to get all {@link Question}s available in database.
     * @return list of {@link Question}.
     */
    public List<Question> getAllQuestions()
    {
        List<Question> questions = new ArrayList<>();
        Cursor cursor = _context.getContentResolver().query(QuestionContract.CONTENT_PROVIDER, null, null, null, null);
        while (cursor.moveToNext())
        {
            Question question = new Question(cursor.getInt(cursor.getColumnIndex(QuestionContract.ID)), cursor.getString(cursor.getColumnIndex(QuestionContract.QUESTION)));
            Cursor answersCursor = _context.getContentResolver().query(ContentUris.withAppendedId(AnswerContract.CONTENT_PROVIDER, question.getIdentifier()), null, null, null, null);
            while (answersCursor.moveToNext())
            {
                question.addAnswer(new Question.Answer(answersCursor.getString(answersCursor.getColumnIndex(AnswerContract.ID)).charAt(0),
                        answersCursor.getString(answersCursor.getColumnIndex(AnswerContract.ANSWER)),
                        answersCursor.getInt(answersCursor.getColumnIndex(AnswerContract.CORRECT)) == 1));
            }
            questions.add(question);
            answersCursor.close();
        }
        cursor.close();
        return questions;
    }

    /**
     * Operation to delete all {@link Question} from database.
     */
    public void deleteAll()
    {
        _context.getContentResolver().delete(AnswerContract.CONTENT_PROVIDER, null, null);
        _context.getContentResolver().delete(QuestionContract.CONTENT_PROVIDER, null, null);
    }
}

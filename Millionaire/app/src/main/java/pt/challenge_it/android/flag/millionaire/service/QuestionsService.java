package pt.challenge_it.android.flag.millionaire.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import pt.challenge_it.android.flag.millionaire.http.QuestionHttpRequest;
import pt.challenge_it.android.flag.millionaire.model.Question;
import pt.challenge_it.android.flag.millionaire.provider.OperationsManager;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * @author Challenge.IT
 */
public class QuestionsService extends IntentService
{
    private OperationsManager operationsManager;

    public QuestionsService()
    {
        super("QuestionsService");
        operationsManager = new OperationsManager(this);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        try 
        {
            Question[] questions = QuestionHttpRequest.getAll();
            for (Question question : questions)
                operationsManager.save(question);

        } catch (Exception e)
        {
            Log.i("QuestionService", e.getMessage());
        }
    }
}

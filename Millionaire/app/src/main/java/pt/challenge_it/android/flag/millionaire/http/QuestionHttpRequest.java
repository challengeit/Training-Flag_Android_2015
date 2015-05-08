package pt.challenge_it.android.flag.millionaire.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import pt.challenge_it.android.flag.millionaire.model.Question;

/**
 * Class that exports the operation for get the questions from the
 * server via HTTP protocol.
 *
 * @author Challenge.IT
 */
public class QuestionHttpRequest
{
    private static final String URL_STR = "http://54.187.166.51:81/questions";

    /**
     * This method is not asynchronous.
     * @return The questions collection from the server.
     * @throws java.lang.Exception
     */
    public static Question[] getAll() throws Exception
    {
        URL url = new URL(URL_STR);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String res = "";
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null)
            res += line;

        JSONArray questionsJSON = new JSONArray(res);
        Question[] questions = new Question[questionsJSON.length()];

        for (int i = 0; i < questionsJSON.length(); i++)
        {
            JSONObject questionJSON = questionsJSON.getJSONObject(i);
            Question question = new Question(questionJSON.getInt("id"), questionJSON.getString("question"));

            JSONArray answersJSON = questionJSON.getJSONArray("answers");
            for(int j = 0; j < answersJSON.length(); j++)
            {
                JSONObject answerJSON = answersJSON.getJSONObject(j);
                question.addAnswer(new Question.Answer(answerJSON.getString("id").charAt(0), answerJSON.getString("answer"), answerJSON.getBoolean("correct")));
            }

            questions[i] = question;
        }

        return questions;
    }
}

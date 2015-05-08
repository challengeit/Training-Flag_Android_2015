package pt.challenge_it.android.flag.millionaire.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Challenge.IT
 */
public class AnswerContract
{
    // table name
    public static final String TABLE = "ANSWER";

    // columns names
    public static final String _ID = BaseColumns._ID;
    public static final String ID = "ID";
    public static final String ANSWER = "ANSWER";
    public static final String CORRECT = "CORRECT";
    public static final String QUESTION_ID = "QUESTION_ID";

    // columns statement
    private static final String columns =
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID + " TEXT NOT NULL, " +
            ANSWER + " TEXT NOT NULL, " +
            CORRECT + " INTEGER NOT NULL, " +
            QUESTION_ID + " INTEGER NOT NULL, " +
            "FOREIGN KEY (" + QUESTION_ID + ") REFERENCES " + QuestionContract.TABLE + " (" + QuestionContract._ID + ")";

    // create table statement
    public static final String CREATE_TABLE_ANSWER = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + columns + ")";

    // content URI for subset of provided data from answers provider.
    public static Uri CONTENT_PROVIDER = Uri.withAppendedPath(AnswersProvider.CONTENT_URI, TABLE);
}

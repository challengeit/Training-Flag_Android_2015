package pt.challenge_it.android.flag.millionaire.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author Challenge.IT
 */
public class QuestionContract
{
    // table name
    public static final String TABLE = "QUESTION";

    // columns names
    public static final String _ID = BaseColumns._ID;
    public static final String ID = "ID";
    public static final String QUESTION = "QUESTION";

    // columns statement
    private static final String columns = _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID + " INTEGER NOT NULL, " + QUESTION + " TEXT NOT NULL";

    // create table statement
    public static final String CREATE_TABLE_QUESTION = "CREATE TABLE IF NOT EXISTS " + TABLE + " (" + columns + ")";

    // content URI for subset of provided data from questions provider.
    public static Uri CONTENT_PROVIDER = Uri.withAppendedPath(QuestionsProvider.CONTENT_URI, TABLE);
}

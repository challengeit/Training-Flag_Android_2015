package pt.challenge_it.android.flag.millionaire.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Challenge.IT
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "millionaire.sql";
    private static final int DB_VERSION = 1;
    private static DataBaseHelper _instance;

    private DataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Basic singleton approach to create an unique instance of this class.
     * @param context Application context.
     * @return single SQLite Helper reference.
     */
    public static DataBaseHelper getInstance(Context context)
    {
        if(_instance == null)
            _instance = new DataBaseHelper(context.getApplicationContext());
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(QuestionContract.CREATE_TABLE_QUESTION);
        db.execSQL(AnswerContract.CREATE_TABLE_ANSWER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // no need to implement this method, just when we want to update the database
    }
}

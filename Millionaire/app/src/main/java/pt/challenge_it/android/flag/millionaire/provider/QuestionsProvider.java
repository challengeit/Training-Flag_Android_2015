package pt.challenge_it.android.flag.millionaire.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import pt.challenge_it.android.flag.millionaire.model.Question;

/**
 * @author Challenge.IT
 */
public class QuestionsProvider extends ContentProvider
{
    // provider identifier
    private static final String AUTHORITY = "pt.challenge_it.android.flag.millionaire.provider.questionsprovider";

    // The content: scheme identifies the URI as a content URI pointing to an Android content provider.
    public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);

    // Matcher for see if the type is one element or all elements.
    private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUESTION_ID  = 1;
    private static final int QUESTION_ALL = 2;

    private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.pt.challenge_it.android.flag.millionaire.provider." + QuestionContract.TABLE;
    private static final String MIME_ONE = "vnd.android.cursor.item/vnd.pt.challenge_it.android.flag.millionaire.provider." + QuestionContract.TABLE;

    // DB Helper instance for access to SQLite DB.
    private SQLiteOpenHelper helper;

    static
    {
        URIMATCHER.addURI(AUTHORITY, QuestionContract.TABLE+"/#", QUESTION_ID);
        URIMATCHER.addURI(AUTHORITY, QuestionContract.TABLE, QUESTION_ALL);
    }

    @Override
    public boolean onCreate()
    {
        helper = DataBaseHelper.getInstance(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri)
    {
        return URIMATCHER.match(uri) == QUESTION_ALL ? MIME_ALL : MIME_ONE;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();
        try
        {
            ContentValues question = getQuestionValues(values);
            long row = db.insert(QuestionContract.TABLE, null, question);
            for(int i = 1; i <= 4; i++) {
                ContentValues answer = getAnswerValues(values, i, row);
                db.insert(AnswerContract.TABLE, null, answer);
            }
            db.setTransactionSuccessful();
            return (row == -1) ? null : ContentUris.withAppendedId(uri, row);
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(QuestionContract.TABLE);

        String type = getType(uri);
        if(!type.equals(MIME_ONE))
            queryBuilder.appendWhere( QuestionContract.ID + "=" + uri.getLastPathSegment());

        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        throw new UnsupportedOperationException("No need to implement this operation.");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try
        {
            return db.delete(QuestionContract.TABLE, selection, selectionArgs);
        }
        finally
        {
            db.close();
        }
    }

    /**
     * Helper method to obtain the {@link ContentValues} of {@link Question} to use in Database.
     * @param values Container with {@link Question} values.
     * @return new container.
     */
    private ContentValues getQuestionValues(ContentValues values)
    {
        ContentValues question = new ContentValues();
        question.put(QuestionContract.ID, values.getAsInteger(Question.ID));
        question.put(QuestionContract.QUESTION, values.getAsString(Question.TEXT));
        return question;
    }

    /**
     * Helper method to obtain the {@link ContentValues} of {@link Question.Answer} to use in Database.
     * @param values Container with {@link Question.Answer} values.
     * @param answerNum Number of question to parse into {@link ContentValues}.
     * @param questionId {@link Question} identifier.
     * @return new container.
     */
    private ContentValues getAnswerValues(ContentValues values, int answerNum, long questionId)
    {
        ContentValues answer = new ContentValues();
        answer.put(AnswerContract.ID, values.getAsString("ANSWER_ID_" + answerNum));
        answer.put(AnswerContract.ANSWER, values.getAsString("ANSWER_TEXT_" + answerNum));
        answer.put(AnswerContract.CORRECT, values.getAsBoolean("ANSWER_CORRECT_" + answerNum) ? 1 : 0);
        answer.put(AnswerContract.QUESTION_ID, questionId);
        return answer;
    }
}

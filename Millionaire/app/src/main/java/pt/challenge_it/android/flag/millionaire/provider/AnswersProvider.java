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
public class AnswersProvider extends ContentProvider
{
    // provider identifier
    private static final String AUTHORITY = "pt.challenge_it.android.flag.millionaire.provider.answersprovider";

    // The content: scheme identifies the URI as a content URI pointing to an Android content provider.
    public static final Uri CONTENT_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY);

    // Matcher for see if the type is one element or all elements.
    private static UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUESTION_ID  = 1;
    private static final int QUESTION_ALL = 2;

    private static final String MIME_ALL = "vnd.android.cursor.dir/vnd.pt.challenge_it.android.flag.millionaire.provider." + AnswerContract.TABLE;
    private static final String MIME_ONE = "vnd.android.cursor.item/vnd.pt.challenge_it.android.flag.millionaire.provider." + AnswerContract.TABLE;

    // DB Helper instance for access to SQLite DB.
    private SQLiteOpenHelper helper;

    static
    {
        URIMATCHER.addURI(AUTHORITY, AnswerContract.TABLE+"/#", QUESTION_ID);
        URIMATCHER.addURI(AUTHORITY, AnswerContract.TABLE, QUESTION_ALL);
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
        throw new UnsupportedOperationException("No need to implement this operation.");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(AnswerContract.TABLE);

        switch (getType(uri))
        {
            case MIME_ALL:
                break;
            case MIME_ONE:
                queryBuilder.appendWhere( AnswerContract.QUESTION_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

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
            return db.delete(AnswerContract.TABLE, selection, selectionArgs);
        }
        finally
        {
            db.close();
        }
    }
}

package android.flag.pt.challenge_it.myfirstapplication;

import android.content.Context;

/**
 * Class that holds one reference for one Context.
 * The purpose of this class is for demonstrate the
 * possibles memory leaks when use the Activity context vs Application context.
 *
 * @author Challenge.IT
 */
public class MemoryLeak
{
    private final Context _context;

    /**
     * Creates an instance of MemoryLeak.
     * @param context
     */
    public MemoryLeak(Context context)
    {
        _context = context;
    }

    /**
     * @return The reference for the context.
     */
    public Context getContext()
    {
        return _context;
    }
}

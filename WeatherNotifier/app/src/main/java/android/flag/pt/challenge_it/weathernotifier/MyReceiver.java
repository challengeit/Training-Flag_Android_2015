package android.flag.pt.challenge_it.weathernotifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Receiver que fica à escuta da alteração à conectividade.
 * Nos BroadcastReceivers, o ciclo de vida resume-se ao método onReceive. Este método
 * corre na thread main!
 *
 * @author Challenge.IT
 */
public class MyReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.v("MY_RECEIVER","On Receive");
    }
}

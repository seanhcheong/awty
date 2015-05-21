package cheongs.washigton.edu.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by cheongs on 5/14/15.
 */
public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("passedMessage");
        String phoneNumber = intent.getStringExtra("passedPhone");
        Toast.makeText(context, phoneNumber + ": " + message, Toast.LENGTH_SHORT).show();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }
}


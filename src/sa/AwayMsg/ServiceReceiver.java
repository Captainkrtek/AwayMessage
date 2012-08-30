package sa.AwayMsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ServiceReceiver extends BroadcastReceiver {

	public static final String TAG = "AwayMsg";

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i(TAG, "ServiceReceiver: onReceive()");

		MyPhoneStateListener phoneListener = new MyPhoneStateListener();
		TelephonyManager telephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	}
}
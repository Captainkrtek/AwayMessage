package sa.AwayMsg;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class MyPhoneStateListener extends PhoneStateListener {

	public static int incoming_call_count = 0;
	public static final String TAG = "AwayMsg";
	public static final String DEFAULT_AWAY_MESSAGE = "Sorry, I'm currently away from my phone - (Sent Via AwayMessage)";

	public static int ENABLED = 1;
	public static int DISABLED = 2;
	public static int run_mode = DISABLED;

	public void onCallStateChanged(int state, String incomingNumber) {

		Log.i(TAG,
				"MyPhoneStateListener: onCallStateChanged(); incomingNumber = "
						+ incomingNumber);
		incoming_call_count = 0;

		if (run_mode == ENABLED) {

			Log.i(TAG,
					"MyPhoneStateListener: onCallStateChanged(); run_mode is ENABLED...checking state...");
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				Log.i(TAG, "IDLE");
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Log.i(TAG, "OFFHOOK");
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				Log.i(TAG, "RINGING");
				incoming_call_count++;
				break;
			}

		} else {
			Log.i(TAG,
					"MyPhoneStateListener: onCallStateChanged(); run_mode is DISABLED...ignoring call...");
		}

		if (incoming_call_count > 0) {
			// if user has entered a custom message, send that
				Log.i(TAG, "Trying to send Custom Message");
				try {
					send_text_message(incomingNumber,
							(String) AwayMsg.customMessage.getText());
				} catch (NullPointerException e) {
					send_text_message(incomingNumber,
							(String) DEFAULT_AWAY_MESSAGE);
				}
				
			
		}

	}

	private void send_text_message(String phoneNumber, String message) {
		if (phoneNumber.length() > 0 && message.length() > 0) {
			Log.i(TAG,
					"MyPhoneStateListener: onCallStateChanged(); test text sent to "
							+ phoneNumber);
			sendSMS(phoneNumber, message);
		} else {
			Log.i(TAG,
					"MyPhoneStateListener: onCallStateChanged(); test text was NOT sent to "
							+ phoneNumber);
		}
	}

	@SuppressWarnings("deprecation")
	private void sendSMS(String phoneNumber, String message) {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, null, null);
		incoming_call_count = 0;
	}

}
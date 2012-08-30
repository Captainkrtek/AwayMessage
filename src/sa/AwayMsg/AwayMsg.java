package sa.AwayMsg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AwayMsg extends Activity {
	/** Called when the activity is first created. */

	public static final String TAG = "AwayMsg";

	private TextView result; // size of the file in bytes
	private TextView version;
	private TextView madeBy;
	public static TextView customMessage;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		result = (TextView) findViewById(R.id.result);

		Log.i(TAG, "phonedemo: onCreate()");

		Button enableServiceButton = (Button) findViewById(R.id.btn_Enable);
		enableServiceButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.i(TAG,
						"phonedemo: enableServiceButton.setOnClickListener()");
				MyPhoneStateListener.run_mode = MyPhoneStateListener.ENABLED;
				result.setText("Away message notification enabled.");

			}
		});

		Button disableServiceButton = (Button) findViewById(R.id.btn_Disable);
		disableServiceButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.i(TAG,
						"phonedemo: disableServiceButton.setOnClickListener()");
				MyPhoneStateListener.run_mode = MyPhoneStateListener.DISABLED;
				result.setText("Away message notification disabled.");
			}
		});

		Button stopAndExitButton = (Button) findViewById(R.id.btn_StopAndExit);
		stopAndExitButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.i(TAG, "phonedemo: stopAndExitButton.setOnClickListener()");
				finish();
			}
		});

	}
}
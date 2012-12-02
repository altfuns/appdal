package cr.quarks.appdal.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cr.quarks.appdal.android.AppDalApp;
import cr.quarks.appdal.android.MainActivity;
import cr.quarks.appdal.android.R;
import cr.quarks.appdal.android.util.BackgroundTask;

public class AppLauncher extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_laucher);

		new BackgroundTask() {

			@Override
			public void work() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}

			@Override
			public void done() {
				startNextActivity();
			}
		};
	}

	public void startNextActivity() {
		if (AppDalApp.getInstance().getUserCommunityId() == -1) {
			startActivity(new Intent(this, CommunitySelectionActivity.class));
		} else {
			startActivity(new Intent(this, MainActivity.class));
		}

		finish();
	}
}

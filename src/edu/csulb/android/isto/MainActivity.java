package edu.csulb.android.isto;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requesting to turn the title OFF
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// set our MainGamePanel as the View
		setContentView(new MainGamePanel(this));

		final Handler handler = new Handler();
		Runnable runable = new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try {

					final AlertDialog _winningDialog = new AlertDialog.Builder(
							MainActivity.this).create();
					_winningDialog.setTitle("Do you want to Start Over?");
					_winningDialog.setButton2("New Game",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions

									Intent _newGame = new Intent(
											MainActivity.this,
											IstoMainActivity.class);
									MainActivity.this.startActivity(_newGame);
									MainActivity.this.finish();
								}
							});
					_winningDialog.setButton3("Exit",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									onDestroy();
								}
							});
					_winningDialog.show();
					// do your code here
					// also call the same runnable
					handler.postDelayed(this, 5000);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					// also call the same runnable
					handler.postDelayed(this, 5000);
				}
			}
		};
		handler.postDelayed(runable, 1000);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		onDestroy();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.finish();
	}
}
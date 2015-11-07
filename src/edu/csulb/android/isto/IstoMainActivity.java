package edu.csulb.android.isto;

import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class IstoMainActivity extends ActionBarActivity {
	private Button _bStart, _bHelp, _bAbout, _bExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Display _display = ((WindowManager) getSystemService(WINDOW_SERVICE))
				.getDefaultDisplay();
		@SuppressWarnings("deprecation")
		final int _width = ((_display.getWidth()) / (2));

		_bStart = (Button) findViewById(R.id.bStart);
		_bStart.setWidth(_width);

		_bHelp = (Button) findViewById(R.id.bHelp);
		_bHelp.setWidth(_width);
		
		_bAbout = (Button) findViewById(R.id.bAbout);
		_bAbout.setWidth(_width);

		_bExit = (Button) findViewById(R.id.bExit);
		_bExit.setWidth(_width);

		_bStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/** Start a new Activity Board.java */
				Intent _iStart = new Intent(IstoMainActivity.this, Board.class);
				startActivity(_iStart);
				finish();	
			}
		});
		_bHelp.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alertDialog = new AlertDialog.Builder(IstoMainActivity.this)
				.create();
		alertDialog.setTitle("Isto Help");
		alertDialog.setMessage("-> Each player rolls the die, the highest roller begins the game. The players alternate turns in an anti-clockwise direction."
				+ "\n" + "-> Each player has a token on his home tile with an associated color."
				+ "\n" + "-> When the dice rolls, a random number is displayed, and the token moves corresponding number of tiles."
				+ "\n" + "-> When the token moves to the tile before the home tile, it enters the inner loop."
				+ "\n" + "-> In the inner loop, the token moves in a clockwise direction." 
				+ "\n" + "-> Winning condition: token enters the central tile."
				+ "\n" + "-> If any player hits by another player at any location except the Home location, then the hitted player has to be died."
				);
		alertDialog.setButton2("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						// here you can add functions
					}
				});
		alertDialog.show();
			}
		});
		_bAbout.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog alertDialog = new AlertDialog.Builder(IstoMainActivity.this)
				.create();
		alertDialog.setTitle("About Us");
		alertDialog.setMessage("Jaydeep Lunagariya"
				+ "\n\n" + "Student at CatState Long Beach"
				+ "\n" + "Email: jaydeep.999@gmail.com" + "\n\n"
				+ "\n" + "Jubin Sukhadia"
				+ "\n\n" + "Student at CatState Long Beach" 
				+ "\n" + "Email: jubinsukhadia@yahoo.in");
		alertDialog.setButton2("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int which) {
						// here you can add functions
					}
				});
		alertDialog.show();
			}
		});
		_bExit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				IstoMainActivity.this.finish();
			}
		});
	}
}
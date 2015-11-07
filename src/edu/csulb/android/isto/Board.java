package edu.csulb.android.isto;

import java.util.Random;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Board extends ActionBarActivity {

	private ImageView[] _DynamicImage;
	private int _blueCurrLoc = 0;
	private boolean _bluePlayer = false;
	private int _diceResult;
	@SuppressWarnings("deprecation")
	private SoundPool _diceSound = new SoundPool(1, 3, 0);
	private int _finalDest = 24;
	@SuppressWarnings("unused")
	private Random _genRandomNumber = new Random();
	private GridLayout _gl;
	private int _greenCurrLoc = 0;
	private boolean _greenPlayer = false;
	private TextView _player1;
	private int _redCurrLoc = 0;
	private boolean _redPlayer = false;
	private int _soundID;
	private TextView _turn;
	private TextView _turn2;
	private TextView _turn3;
	private TextView _turn4;

	private IstoCountDownTimer countDownTimer;

	private TextView _timeCount;
	private int _yellowCurrLoc = 0;
	private boolean _yellowPlayer = false;
	private ImageView dice_picture;

	private final long startTime = 30000;
	private final long interval = 1000;
	private String currentPlayer = "currentPlayer";
	private String nextPlayer = "nextPlayer";

	// private enum playerTurn {GREEN, RED, BLUE, BROWN};

	int _redCoin[] = { 23, 24, 19, 14, 9, 4, 3, 2, 1, 0, 5, 10, 15, 20, 21, 16,
			11, 6, 7, 8, 13, 18, 17, 12 };
	int _greenCoin[] = { 9, 4, 3, 2, 1, 0, 5, 10, 15, 20, 21, 22, 23, 24, 19,
			18, 17, 16, 11, 6, 7, 8, 13, 12 };
	int _blueCoin[] = { 1, 0, 5, 10, 15, 20, 21, 22, 23, 24, 19, 14, 9, 4, 3,
			8, 13, 18, 17, 16, 11, 6, 7 };
	int _yellowCoin[] = { 15, 20, 21, 22, 23, 24, 19, 14, 9, 4, 3, 2, 1, 0, 5,
			6, 7, 8, 13, 18, 17, 16, 11, 12 };

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.board);
		_turn = (TextView) findViewById(R.id.Turn);
		_turn2 = (TextView) findViewById(R.id.Turn2);
		_turn3 = (TextView) findViewById(R.id.Turn3);
		_turn4 = (TextView) findViewById(R.id.Turn4);
		_timeCount = (TextView) findViewById(R.id.TimeCount);

		_gl = (GridLayout) findViewById(R.id.gl);
		_player1 = (TextView) findViewById(R.id.redPlayer);

		Display localDisplay = ((WindowManager) getSystemService("window"))
				.getDefaultDisplay();
		int _width = localDisplay.getWidth() / 5;
		int _height = localDisplay.getHeight() / 15;

		_DynamicImage = new ImageView[25];
		for (int i = 0; i < 25; i++) {
			if (i == 2) {
				_DynamicImage[i] = new ImageView(this);
				_DynamicImage[i].setId(i);
				_DynamicImage[i].setMinimumWidth(_width);
				_DynamicImage[i].setMinimumHeight(_height);
				_DynamicImage[i].setPadding(5, 5, 5, 5);
				_DynamicImage[i].setImageResource(R.drawable.blue_coin);
				_gl.addView(_DynamicImage[i]);
			} else if (i == 10) {
				_DynamicImage[i] = new ImageView(this);
				_DynamicImage[i].setId(i);
				_DynamicImage[i].setMinimumWidth(_width);
				_DynamicImage[i].setMinimumHeight(_height);
				_DynamicImage[i].setPadding(5, 5, 5, 5);
				_DynamicImage[i].setImageResource(R.drawable.yellow_coin);
				_gl.addView(_DynamicImage[i]);
			} else if (i == 12) {
				_DynamicImage[i] = new ImageView(this);
				_DynamicImage[i].setId(i);
				_DynamicImage[i].setMinimumWidth(_width);
				_DynamicImage[i].setMinimumHeight(_height);
				_DynamicImage[i].setPadding(5, 5, 5, 5);
				_DynamicImage[i].setImageResource(R.drawable.center);
				_gl.addView(_DynamicImage[i]);
			} else if (i == 14) {
				_DynamicImage[i] = new ImageView(this);
				_DynamicImage[i].setId(i);
				_DynamicImage[i].setMinimumWidth(_width);
				_DynamicImage[i].setMinimumHeight(_height);
				_DynamicImage[i].setPadding(5, 5, 5, 5);
				_DynamicImage[i].setImageResource(R.drawable.green_coin);
				_gl.addView(_DynamicImage[i]);

			} else if (i == 22) {
				_DynamicImage[i] = new ImageView(this);
				_DynamicImage[i].setId(i);
				_DynamicImage[i].setMinimumWidth(_width);
				_DynamicImage[i].setMinimumHeight(_height);
				_DynamicImage[i].setPadding(5, 5, 5, 5);
				_DynamicImage[i].setImageResource(R.drawable.red_coin);
				_gl.addView(_DynamicImage[i]);
			} else {
				_DynamicImage[i] = new ImageView(this);
				_DynamicImage[i].setId(i);
				_DynamicImage[i].setMinimumWidth(_width);
				_DynamicImage[i].setMinimumHeight(_height);
				_DynamicImage[i].setPadding(5, 5, 5, 5);
				_DynamicImage[i].setImageResource(R.drawable.white);
				_gl.addView(_DynamicImage[i]);
			}
		}

		// load dice sound
		_soundID = _diceSound.load(this, R.raw.shake_dice, 1);
		// get reference to image widget
		dice_picture = (ImageView) findViewById(R.id.dice);
		_redPlayer = true;
		countDownTimer = new IstoCountDownTimer(startTime, interval);
		turnPlayer1();
	}

	// CountDownTimer class
	public class IstoCountDownTimer extends CountDownTimer {
		public IstoCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			if (currentPlayer == "Player Red") {
				_redPlayer = false;
				_greenPlayer = true;
				_bluePlayer = false;
				_yellowPlayer = false;
				turnPlayer1();
			} else if (currentPlayer == "Player Green") {
				_redPlayer = false;
				_greenPlayer = false;
				_bluePlayer = true;
				_yellowPlayer = false;
				turnPlayer1();
			} else if (currentPlayer == "Player Blue") {
				_redPlayer = false;
				_greenPlayer = false;
				_bluePlayer = false;
				_yellowPlayer = true;
				turnPlayer1();
			} else if (currentPlayer == "Player Brown") {
				_redPlayer = true;
				_greenPlayer = false;
				_bluePlayer = false;
				_yellowPlayer = false;
				turnPlayer1();
			}
		}

		@Override
		public void onTick(long millisUntilFinished) {
			if (currentPlayer == "Player Red") {
				_timeCount.setTextColor(0xFFFF0000);
			} else if (currentPlayer == "Player Green") {
				_timeCount.setTextColor(0xFF008000);
			} else if (currentPlayer == "Player Blue") {
				_timeCount.setTextColor(0xFF0000FF);
			} else if (currentPlayer == "Player Brown") {
				_timeCount.setTextColor(0xFFE2A76F);
			}
			_timeCount.setText(currentPlayer + ",   " + millisUntilFinished
					/ 1000 + " seconds remaining!");
		}
	}

	public void turnPlayer1() {
		if (_redPlayer) {
			// timeCounter("Player Red", "Player Green");
			currentPlayer = "Player Red";
			nextPlayer = "Player Green";
			countDownTimer.start();

			dice_picture.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					diceClick("Player Red");
				}
			});
		} else if (_greenPlayer) {
			// timeCounter("Player Green", "Player Blue");
			currentPlayer = "Player Green";
			nextPlayer = "Player Blue";
			countDownTimer.start();

			dice_picture.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					diceClick("Player Green");
				}
			});
		} else if (_bluePlayer) {
			// timeCounter("Player Blue", "Player Brown");
			currentPlayer = "Player Blue";
			nextPlayer = "Player Brown";
			countDownTimer.start();

			dice_picture.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					diceClick("Player Blue");
				}
			});
		}
		if (_yellowPlayer) {
			// timeCounter("Player Brown", "Player Red");
			currentPlayer = "Player Brown";
			nextPlayer = "Player Red";
			countDownTimer.start();

			dice_picture.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					diceClick("Player Brown");
				}
			});
		}
	}

	@SuppressLint("NewApi")
	public void diceClick(String player) {

		if (player == "Player Red") {
			_diceResult = 0;
			_diceResult = new Random(System.currentTimeMillis()).nextInt(6) + 1;
			_diceSound.play(_soundID, 1.0f, 1.0f, 0, 0, 1.0f);
			rollDice();
			imageTranslation(_diceResult, player);
			countDownTimer.cancel();
			_redPlayer = false;
			_greenPlayer = true;
			_bluePlayer = false;
			_yellowPlayer = false;
			turnPlayer1();
		} else if (player == "Player Green") {
			_diceResult = 0;
			_diceResult = new Random(System.currentTimeMillis()).nextInt(6) + 1;
			_diceSound.play(_soundID, 1.0f, 1.0f, 0, 0, 1.0f);
			rollDice();
			imageTranslation(_diceResult, player);
			countDownTimer.cancel();
			_redPlayer = false;
			_greenPlayer = false;
			_bluePlayer = true;
			_yellowPlayer = false;
			turnPlayer1();
		} else if (player == "Player Blue") {
			_diceResult = 0;
			_diceResult = new Random(System.currentTimeMillis()).nextInt(6) + 1;
			_diceSound.play(_soundID, 1.0f, 1.0f, 0, 0, 1.0f);
			rollDice();
			imageTranslation(_diceResult, player);
			countDownTimer.cancel();
			_redPlayer = false;
			_greenPlayer = false;
			_bluePlayer = false;
			_yellowPlayer = true;
			turnPlayer1();
		}
		if (player == "Player Brown") {
			_diceResult = 0;
			_diceResult = new Random(System.currentTimeMillis()).nextInt(6) + 1;
			_diceSound.play(_soundID, 1.0f, 1.0f, 0, 0, 1.0f);
			rollDice();
			imageTranslation(_diceResult, player);
			countDownTimer.cancel();
			_redPlayer = true;
			_greenPlayer = false;
			_bluePlayer = false;
			_yellowPlayer = false;
			turnPlayer1();
		}
	}

	public void imageTranslation(int _diceCount, String playerName) {
		// image translation

		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(400);
		anim.setStartOffset(20);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(3);

		Intent localIntent = new Intent(this, MainActivity.class);
		if (playerName == "Player Red") {

			if ((_redCurrLoc + _diceCount) == _finalDest) {
				_DynamicImage[_redCoin[_redCurrLoc - 1]]
						.setImageResource(R.drawable.white);
				_DynamicImage[12].setImageResource(R.drawable.red_coin);
				_DynamicImage[12].startAnimation(anim);
				_turn.setText(playerName + " won the game! "
						+ String.valueOf(_redCurrLoc + _diceCount));
				countDownTimer.cancel();
				startActivity(localIntent);
				finish();
				dice_picture.setClickable(false);
			} else if ((_redCurrLoc + _diceCount) > _finalDest) {
				Toast.makeText(
						getApplicationContext(),
						playerName + " got too many: "
								+ (_redCurrLoc + _diceCount),
						Toast.LENGTH_SHORT).show();
			} else {
				for (int i = 0; i < _diceCount; i++) {

					int k = _redCoin[_redCurrLoc];
					if (k == 23) {
						if (_greenCurrLoc > 0
								&& _greenCoin[_greenCurrLoc - 1] == 22) {
							_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if (_blueCurrLoc > 0
								&& _blueCoin[_blueCurrLoc - 1] == 22) {
							_DynamicImage[_blueCoin[_blueCurrLoc - 1]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if (_yellowCurrLoc > 0
								&& _yellowCoin[_yellowCurrLoc - 1] == 22) {
							_DynamicImage[_yellowCoin[_yellowCurrLoc - 1]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else {
							_DynamicImage[22]
									.setImageResource(R.drawable.red_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						}
					} else if (k == 9) {
						if (_greenCurrLoc == 0) {
							_DynamicImage[_redCoin[(_redCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if ((_blueCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _blueCoin[(_blueCurrLoc - 1)])) {
							_DynamicImage[_redCoin[(_redCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if ((_yellowCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _yellowCoin[(_yellowCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else {
							_DynamicImage[14]
									.setImageResource(R.drawable.green_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						}
					} else if (k == 1) {
						if (_blueCurrLoc == 0) {
							_DynamicImage[_redCoin[(_redCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if ((_greenCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _greenCoin[(_greenCurrLoc - 1)])) {
							_DynamicImage[_redCoin[(_redCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if ((_yellowCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _yellowCoin[(_yellowCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else {
							_DynamicImage[2]
									.setImageResource(R.drawable.blue_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						}
					} else if (k == 15) {
						if (_yellowCurrLoc == 0) {
							_DynamicImage[_redCoin[(_redCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if ((_greenCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _greenCoin[(_greenCurrLoc - 1)])) {
							_DynamicImage[_redCoin[(_redCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else if ((_blueCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _blueCoin[(_blueCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						} else {
							_DynamicImage[10]
									.setImageResource(R.drawable.yellow_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.red_coin);
						}
					} else {
						if (_redCurrLoc > 0) {
							if ((_greenCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])) {
								_DynamicImage[_redCoin[_redCurrLoc - 1]]
										.setImageResource(R.drawable.green_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.red_coin);
							} else if ((_blueCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _blueCoin[_blueCurrLoc - 1])) {
								_DynamicImage[_redCoin[_redCurrLoc - 1]]
										.setImageResource(R.drawable.blue_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.red_coin);
							} else if ((_yellowCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _yellowCoin[_yellowCurrLoc - 1])) {
								_DynamicImage[_redCoin[_redCurrLoc - 1]]
										.setImageResource(R.drawable.yellow_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.red_coin);
							} else {
								_DynamicImage[_redCoin[_redCurrLoc - 1]]
										.setImageResource(R.drawable.white);
								_DynamicImage[k]
										.setImageResource(R.drawable.red_coin);
							}
						}
					}
					_redCurrLoc++;
				}
				_DynamicImage[_redCoin[_redCurrLoc - 1]].startAnimation(anim);
				_turn.setText(playerName + " is at: "
						+ String.valueOf(_redCurrLoc));
				if ((_greenCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])
						&& (_redCoin[(_redCurrLoc - 1)] != 14)
						&& (_redCoin[(_redCurrLoc - 1)] != 2)
						&& (_redCoin[(_redCurrLoc - 1)] != 10 && (_redCoin[(_redCurrLoc - 1)] != 22))) {
					_greenCurrLoc = 0;
					_DynamicImage[14].setImageResource(R.drawable.green_coin);
					_DynamicImage[14].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Red Killed Green!", Toast.LENGTH_SHORT).show();
					_turn4.setText("Player Green is at: "
							+ String.valueOf(_greenCurrLoc));
				}
				if ((_blueCurrLoc > 0 && _redCoin[(_redCurrLoc - 1)] == _blueCoin[_blueCurrLoc - 1])
						&& (_redCoin[(_redCurrLoc - 1)] != 2)
						&& (_redCoin[(_redCurrLoc - 1)] != 10)
						&& (_redCoin[(_redCurrLoc - 1)] != 14)
						&& (_redCoin[(_redCurrLoc - 1)] != 22)) {
					_blueCurrLoc = 0;
					_DynamicImage[2].setImageResource(R.drawable.blue_coin);
					_DynamicImage[2].startAnimation(anim);
					Toast.makeText(getApplicationContext(), "Red Killed Blue!",
							Toast.LENGTH_SHORT).show();
					this._turn2.setText("Player Blue is at: "
							+ String.valueOf(this._blueCurrLoc));
				}

				if ((_yellowCurrLoc > 0 && _redCoin[(-1 + _redCurrLoc)] == _yellowCoin[_yellowCurrLoc - 1])
						&& (_redCoin[(-1 + _redCurrLoc)] != 10)
						&& (_redCoin[(_redCurrLoc - 1)] != 2)
						&& (_redCoin[(_redCurrLoc - 1)] != 22)
						&& (_redCoin[(_redCurrLoc - 1)] != 14)) {
					_yellowCurrLoc = 0;
					_DynamicImage[10].setImageResource(R.drawable.yellow_coin);
					_DynamicImage[10].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Red Killed Brown!", Toast.LENGTH_SHORT).show();
					_turn3.setText("Player Brown is at: "
							+ String.valueOf(_yellowCurrLoc));
				}

//				Toast.makeText(getApplicationContext(),
//						"Hey! Player Green, its your turn! ",
//						Toast.LENGTH_SHORT).show();

			}
			_diceCount = 0;
		} else if (playerName == "Player Green") {

			if ((_greenCurrLoc + _diceCount) == _finalDest) {
				_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
						.setImageResource(R.drawable.white);
				_DynamicImage[12].setImageResource(R.drawable.green_coin);
				_DynamicImage[12].startAnimation(anim);
				_turn4.setText(playerName + " won the game! "
						+ String.valueOf(_greenCurrLoc + _diceCount));
				countDownTimer.cancel();
				startActivity(localIntent);
				finish();
				dice_picture.setClickable(false);
			} else if ((_greenCurrLoc + _diceCount) > _finalDest) {
				Toast.makeText(
						getApplicationContext(),
						playerName + " got too many: "
								+ (_greenCurrLoc + _diceCount),
						Toast.LENGTH_SHORT).show();
			} else {
				for (int i = 0; i < _diceCount; i++) {
					// if (_redCurrLoc != 12 || _blueCurrLoc != 4
					// || _yellowCurrLoc != 8) {
					// _DynamicImage[14]
					// .setImageResource(R.drawable.green_moved);
					// }
					int k = _greenCoin[_greenCurrLoc];
					if (k == 23) {
						if (_redCurrLoc == 0) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if ((_blueCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _blueCoin[(_blueCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if ((_yellowCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _yellowCoin[(_yellowCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else {
							_DynamicImage[22]
									.setImageResource(R.drawable.red_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						}
					} else if (k == 9) {
						if (_redCurrLoc > 0 && _redCoin[_redCurrLoc - 1] == 14) {
							_DynamicImage[_redCoin[_redCurrLoc - 1]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if (_blueCurrLoc > 0
								&& _blueCoin[_blueCurrLoc - 1] == 14) {
							_DynamicImage[_blueCoin[_blueCurrLoc - 1]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if (_yellowCurrLoc > 0
								&& _yellowCoin[_yellowCurrLoc - 1] == 14) {
							_DynamicImage[_yellowCoin[_yellowCurrLoc - 1]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else {
							_DynamicImage[14]
									.setImageResource(R.drawable.green_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						}
					} else if (k == 1) {
						if (_blueCurrLoc == 0) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if ((_redCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _redCoin[(_redCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if ((_yellowCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _yellowCoin[(_yellowCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else {
							_DynamicImage[2]
									.setImageResource(R.drawable.blue_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						}
					} else if (k == 15) {
						if (_yellowCurrLoc == 0) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if ((_blueCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _blueCoin[(_blueCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else if ((_redCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _redCoin[(_redCurrLoc - 1)])) {
							_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						} else {
							_DynamicImage[10]
									.setImageResource(R.drawable.yellow_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.green_coin);
						}
					} else {
						if (_greenCurrLoc > 0) {
							if ((_redCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
								_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
										.setImageResource(R.drawable.red_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.green_coin);
							} else if ((_blueCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _blueCoin[_blueCurrLoc - 1])) {
								_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
										.setImageResource(R.drawable.blue_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.green_coin);
							} else if ((_yellowCurrLoc > 0 && _greenCoin[(_greenCurrLoc - 1)] == _yellowCoin[_yellowCurrLoc - 1])) {
								_DynamicImage[_greenCoin[(_greenCurrLoc - 1)]]
										.setImageResource(R.drawable.yellow_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.green_coin);
							} else {
								_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
										.setImageResource(R.drawable.white);
								_DynamicImage[k]
										.setImageResource(R.drawable.green_coin);
							}
						}
					}
					_greenCurrLoc++;
				}
				_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
						.startAnimation(anim);
				_turn4.setText(playerName + " is at: "
						+ String.valueOf(_greenCurrLoc));
				if ((_redCurrLoc > 0 && _greenCoin[_greenCurrLoc - 1] == _redCoin[_redCurrLoc - 1])
						&& (_greenCoin[(_greenCurrLoc - 1)] != 2)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 10)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 14)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 22)) {
					_redCurrLoc = 0;
					_DynamicImage[22].setImageResource(R.drawable.red_coin);
					_DynamicImage[22].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Green Killed Red!", Toast.LENGTH_SHORT).show();
					this._turn.setText("Player Red is at: "
							+ String.valueOf(this._redCurrLoc));
				}
				if ((_blueCurrLoc > 0 && _greenCoin[_greenCurrLoc - 1] == _blueCoin[_blueCurrLoc - 1])
						&& (_greenCoin[(_greenCurrLoc - 1)] != 2)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 10)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 14)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 22)) {
					_blueCurrLoc = 0;
					_DynamicImage[2].setImageResource(R.drawable.blue_coin);
					_DynamicImage[2].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Green Killed Blue!", Toast.LENGTH_SHORT).show();
					this._turn2.setText("Player Blue is at: "
							+ String.valueOf(this._blueCurrLoc));
				}

				if ((_yellowCurrLoc > 0 && _greenCoin[_greenCurrLoc - 1] == _yellowCoin[_yellowCurrLoc - 1])
						&& (_greenCoin[(_greenCurrLoc - 1)] != 2)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 10)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 14)
						&& (_greenCoin[(_greenCurrLoc - 1)] != 22)) {
					_yellowCurrLoc = 0;
					_DynamicImage[10].setImageResource(R.drawable.yellow_coin);
					_DynamicImage[10].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Green Killed Brown!", Toast.LENGTH_SHORT).show();
					_turn3.setText("Player Brown is at: "
							+ String.valueOf(_yellowCurrLoc));
				}

//				Toast.makeText(getApplicationContext(),
//						"Hey! Player Blue, its your turn! ", Toast.LENGTH_SHORT)
//						.show();

			}
			_diceCount = 0;
		}
		if (playerName == "Player Blue") {

			if ((_blueCurrLoc + _diceCount) == _finalDest) {
				_DynamicImage[_blueCoin[_blueCurrLoc - 1]]
						.setImageResource(R.drawable.white);
				_DynamicImage[12].setImageResource(R.drawable.blue_coin);
				_DynamicImage[12].startAnimation(anim);
				_turn2.setText(playerName + " won the game! "
						+ String.valueOf(_blueCurrLoc + _diceCount));
				countDownTimer.cancel();
				startActivity(localIntent);
				finish();
				dice_picture.setClickable(false);
			} else if ((_blueCurrLoc + _diceCount) > _finalDest) {
				Toast.makeText(
						getApplicationContext(),
						playerName + " got too many: "
								+ (_blueCurrLoc + _diceCount),
						Toast.LENGTH_SHORT).show();
			} else {
				for (int i = 0; i < _diceCount; i++) {

					int k = _blueCoin[_blueCurrLoc];
					if (k == 23) {
						if (_redCurrLoc == 0) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if ((_greenCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if ((_yellowCurrLoc > 0 && _yellowCoin[_yellowCurrLoc - 1] == _blueCoin[(_blueCurrLoc - 1)])) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else {
							_DynamicImage[22]
									.setImageResource(R.drawable.red_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						}
					} else if (k == 9) {
						if (_greenCurrLoc == 0) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if ((_redCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if ((_yellowCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _yellowCoin[(_yellowCurrLoc - 1)])) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else {
							_DynamicImage[14]
									.setImageResource(R.drawable.green_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						}
					} else if (k == 1) {
						if (_redCurrLoc > 0 && _redCoin[_redCurrLoc - 1] == 2) {
							_DynamicImage[_redCoin[_redCurrLoc - 1]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if (_greenCurrLoc > 0
								&& _greenCoin[_greenCurrLoc - 1] == 2) {
							_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if (_yellowCurrLoc > 0
								&& _yellowCoin[_yellowCurrLoc - 1] == 2) {
							_DynamicImage[_yellowCoin[_yellowCurrLoc - 1]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else {
							_DynamicImage[2]
									.setImageResource(R.drawable.blue_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						}
					} else if (k == 15) {
						if (_yellowCurrLoc == 0) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.yellow_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if ((_redCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else if ((_greenCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _greenCoin[(_greenCurrLoc - 1)])) {
							_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						} else {
							_DynamicImage[10]
									.setImageResource(R.drawable.yellow_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.blue_coin);
						}
					} else {
						if (_blueCurrLoc > 0) {
							if ((_redCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
								_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
										.setImageResource(R.drawable.red_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.blue_coin);
							} else if ((_greenCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])) {
								_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
										.setImageResource(R.drawable.green_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.blue_coin);
							} else if ((_yellowCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _yellowCoin[_yellowCurrLoc - 1])) {
								_DynamicImage[_blueCoin[(_blueCurrLoc - 1)]]
										.setImageResource(R.drawable.yellow_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.blue_coin);
							} else {
								_DynamicImage[_blueCoin[_blueCurrLoc - 1]]
										.setImageResource(R.drawable.white);
								_DynamicImage[k]
										.setImageResource(R.drawable.blue_coin);
							}
						}
					}
					_blueCurrLoc++;
				}
				_DynamicImage[_blueCoin[_blueCurrLoc - 1]].startAnimation(anim);
				_turn2.setText(playerName + " is at: "
						+ String.valueOf(_blueCurrLoc));
				if ((_redCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])
						&& (_blueCoin[(_blueCurrLoc - 1)] != 22)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 2)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 10)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 14)) {
					_redCurrLoc = 0;
					_DynamicImage[22].setImageResource(R.drawable.red_coin);
					_DynamicImage[22].startAnimation(anim);
					Toast.makeText(getApplicationContext(), "Blue Killed Red",
							Toast.LENGTH_SHORT).show();
					_turn.setText("Player Red is at: "
							+ String.valueOf(_redCurrLoc));
				}
				if ((_greenCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])
						&& (_blueCoin[(_blueCurrLoc - 1)] != 22)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 2)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 10)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 14)) {
					_greenCurrLoc = 0;
					_DynamicImage[14].setImageResource(R.drawable.green_coin);
					_DynamicImage[14].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Blue Killed Green!", Toast.LENGTH_SHORT).show();
					this._turn4.setText("Player Green is at: "
							+ String.valueOf(this._greenCurrLoc));
				}

				if ((_yellowCurrLoc > 0 && _blueCoin[(_blueCurrLoc - 1)] == _yellowCoin[_yellowCurrLoc - 1])
						&& (_blueCoin[(_blueCurrLoc - 1)] != 22)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 2)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 10)
						&& (_blueCoin[(_blueCurrLoc - 1)] != 14)) {
					_yellowCurrLoc = 0;
					_DynamicImage[10].setImageResource(R.drawable.yellow_coin);
					_DynamicImage[10].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Blue Killed Brown!", Toast.LENGTH_SHORT).show();
					_turn3.setText("Player Brown is at: "
							+ String.valueOf(_yellowCurrLoc));
				}

//				Toast.makeText(getApplicationContext(),
//						"Hey! Player Brown, its your turn! ",
//						Toast.LENGTH_SHORT).show();

			}
			_diceCount = 0;
		} else if (playerName == "Player Brown") {

			if ((_yellowCurrLoc + _diceCount) == _finalDest) {
				_DynamicImage[_yellowCoin[_yellowCurrLoc - 1]]
						.setImageResource(R.drawable.white);
				_DynamicImage[12].setImageResource(R.drawable.yellow_coin);
				_DynamicImage[12].startAnimation(anim);
				_turn3.setText(playerName + " won the game! "
						+ String.valueOf(_yellowCurrLoc + _diceCount));
				countDownTimer.cancel();
				startActivity(localIntent);
				finish();
				dice_picture.setClickable(false);
			} else if ((_yellowCurrLoc + _diceCount) > _finalDest) {
				Toast.makeText(
						getApplicationContext(),
						playerName + " got too many: "
								+ (_yellowCurrLoc + _diceCount),
						Toast.LENGTH_SHORT).show();
			} else {
				for (int i = 0; i < _diceCount; i++) {
					int k = _yellowCoin[_yellowCurrLoc];
					if (k == 23) {
						if (_redCurrLoc == 0) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if ((_greenCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if ((_blueCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _blueCoin[_blueCurrLoc - 1])) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else {
							_DynamicImage[22]
									.setImageResource(R.drawable.red_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						}
					} else if (k == 9) {
						if (_greenCurrLoc == 0) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if ((_redCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if ((_blueCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _blueCoin[_blueCurrLoc - 1])) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else {
							_DynamicImage[14]
									.setImageResource(R.drawable.green_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						}
					} else if (k == 1) {
						if (_blueCurrLoc == 0) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if ((_redCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if ((_greenCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])) {
							_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else {
							_DynamicImage[2]
									.setImageResource(R.drawable.blue_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						}
					} else if (k == 15) {
						if (_redCurrLoc > 0 && _redCoin[_redCurrLoc - 1] == 10) {
							_DynamicImage[_redCoin[_redCurrLoc - 1]]
									.setImageResource(R.drawable.red_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if (_greenCurrLoc > 0
								&& _greenCoin[_greenCurrLoc - 1] == 10) {
							_DynamicImage[_greenCoin[_greenCurrLoc - 1]]
									.setImageResource(R.drawable.green_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else if (_blueCurrLoc > 0
								&& _blueCoin[_blueCurrLoc - 1] == 10) {
							_DynamicImage[_blueCoin[_blueCurrLoc - 1]]
									.setImageResource(R.drawable.blue_coin);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						} else {
							_DynamicImage[10]
									.setImageResource(R.drawable.yellow_moved);
							_DynamicImage[k]
									.setImageResource(R.drawable.yellow_coin);
						}
					} else {
						if (_yellowCurrLoc > 0) {
							if ((_redCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _redCoin[_redCurrLoc - 1])) {
								_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
										.setImageResource(R.drawable.red_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.yellow_coin);
							} else if ((_greenCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _greenCoin[_greenCurrLoc - 1])) {
								_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
										.setImageResource(R.drawable.green_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.yellow_coin);
							} else if ((_blueCurrLoc > 0 && _yellowCoin[(_yellowCurrLoc - 1)] == _blueCoin[_blueCurrLoc - 1])) {
								_DynamicImage[_yellowCoin[(_yellowCurrLoc - 1)]]
										.setImageResource(R.drawable.blue_coin);
								_DynamicImage[k]
										.setImageResource(R.drawable.yellow_coin);
							} else {
								_DynamicImage[_yellowCoin[_yellowCurrLoc - 1]]
										.setImageResource(R.drawable.white);
								_DynamicImage[k]
										.setImageResource(R.drawable.yellow_coin);
							}
						}
					}
					_yellowCurrLoc++;
				}
				_DynamicImage[_yellowCoin[_yellowCurrLoc - 1]]
						.startAnimation(anim);
				_turn3.setText(playerName + " is at: "
						+ String.valueOf(_yellowCurrLoc));
				if ((_greenCurrLoc > 0 && _yellowCoin[_yellowCurrLoc - 1] == _greenCoin[_greenCurrLoc - 1])
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 2)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 22)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 10)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 14)) {
					_greenCurrLoc = 0;
					_DynamicImage[14].setImageResource(R.drawable.green_coin);
					_DynamicImage[14].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Brown Killed Green!", Toast.LENGTH_SHORT).show();
					_turn4.setText("Player Green is at: "
							+ String.valueOf(_greenCurrLoc));
				}
				if ((_blueCurrLoc > 0 && _yellowCoin[_yellowCurrLoc - 1] == _blueCoin[_blueCurrLoc - 1])
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 2)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 22)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 10)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 14)) {
					_blueCurrLoc = 0;
					_DynamicImage[2].setImageResource(R.drawable.blue_coin);
					_DynamicImage[2].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Brown Killed Blue!", Toast.LENGTH_SHORT).show();
					_turn2.setText("Player Blue is at: "
							+ String.valueOf(this._blueCurrLoc));
				}

				if ((_redCurrLoc > 0 && _yellowCoin[_yellowCurrLoc - 1] == _redCoin[_redCurrLoc - 1])
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 2)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 22)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 10)
						&& (_yellowCoin[(_yellowCurrLoc - 1)] != 14)) {
					_redCurrLoc = 0;
					_DynamicImage[22].setImageResource(R.drawable.red_coin);
					_DynamicImage[22].startAnimation(anim);
					Toast.makeText(getApplicationContext(),
							"Brown Killed Red!", Toast.LENGTH_SHORT).show();
					_turn.setText("Player Red is at: "
							+ String.valueOf(_redCurrLoc));
				}
//
//				Toast.makeText(getApplicationContext(),
//						"Hey! Player Red, its your turn! ", Toast.LENGTH_SHORT)
//						.show();
			}
			_diceCount = 0;
		}
	}

	public void rollDice() {
		switch (_diceResult) {
		case 1:
			dice_picture.setImageResource(R.drawable.one);
			break;
		case 2:
			dice_picture.setImageResource(R.drawable.two);
			break;
		case 3:
			dice_picture.setImageResource(R.drawable.three);
			break;
		case 4:
			dice_picture.setImageResource(R.drawable.four);
			break;
		case 5:
			dice_picture.setImageResource(R.drawable.five);
			break;
		case 6:
			dice_picture.setImageResource(R.drawable.six);
			break;
		}
	}

	// Clean up
	@Override
	protected void onPause() {
		super.onPause();
		_diceSound.pause(_soundID);
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		countDownTimer.cancel();
		super.onDestroy();
		Board.this.finish();
	}

}
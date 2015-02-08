package com.patterns.davidshalom.switchers;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextSwitcher;
import com.patterns.davidshalom.switchers.view.SendCommentButton;


public class MainActivity extends ActionBarActivity {

	TextSwitcher textSwitcher;
	Button button;
	int x = 0;
	SendCommentButton btnSendComment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textSwitcher = (TextSwitcher) findViewById(R.id.myTextSwitcher);
		button = (Button) findViewById(R.id.button);
		btnSendComment = (SendCommentButton) findViewById(R.id.btnSendComment);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				textSwitcher.setText("" + x++);
				btnSendComment.setCurrentState(SendCommentButton.STATE_DONE);


			}
		});

		if (savedInstanceState == null) {

			textSwitcher.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				@Override
				public boolean onPreDraw() {
					textSwitcher.getViewTreeObserver().removeOnPreDrawListener(this);
					startIntroAnimation();
					return false;
				}
			});
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);

		return true;
	}

	private void startIntroAnimation() {

		int[] startingLocation = new int[2];
		textSwitcher.getLocationOnScreen(startingLocation);

		textSwitcher.setTranslationY(-startingLocation[1]);
		btnSendComment.setTranslationY(Utils.getScreenHeight(this)-startingLocation[1]);

		button.getLocationOnScreen(startingLocation);
		button.setTranslationY(-startingLocation[1]);


		textSwitcher.animate()
				.setInterpolator(new DecelerateInterpolator())
				.translationY(0)
				.setDuration(500)
				.setStartDelay(100);

		btnSendComment.animate()
				.setInterpolator(new DecelerateInterpolator())
				.translationY(0)
				.setDuration(600)
				.setStartDelay(160);

		button.animate()
				.setInterpolator(new DecelerateInterpolator())
				.translationY(0)
				.setDuration(500)
				.setStartDelay(220);



	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}

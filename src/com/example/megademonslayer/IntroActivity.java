package com.example.megademonslayer;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class IntroActivity extends Activity {
	
	public GestureDetector mGestureDetector;
	
	protected int damage;
	protected int demonHealth = 100;
/*	private SoundPool mSoundPool;
	private HashMap<Integer, Integer> mSoundPoolMap; 
	private  Context mContext;
	 private  Vector<Integer> mAvailibleSounds = new Vector<Integer>();
	 private  Vector<Integer> mKillSoundQueue = new Vector<Integer>();
	 private  Handler mHandler = new Handler();
	 private AudioManager mAudioManager;
*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		mGestureDetector = new GestureDetector(this).setBaseListener(mBaseListener);

	}
	@Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mGestureDetector.onMotionEvent(event);
    }

    private GestureDetector.BaseListener mBaseListener = new GestureDetector.BaseListener() {
        @Override
        public boolean onGesture(Gesture gesture) {

            switch (gesture) {

                case SWIPE_RIGHT:
                    attackRight();
                    demonHealth = demonHealth - damage;
                    if (demonHealth <= 0 ) {
                        userWins();
                    }
                    return true;
                case SWIPE_LEFT:
                    attackLeft();
                    demonHealth = demonHealth - damage;
                    if (demonHealth <= 0 ) {
                        userWins();
                    }
                    return true;

                default:
                    return false;

            }

        }
    };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/* private void playSwipe() {
        MediaPlayer swipeSound = MediaPlayer.create(this, R.raw.sword_swipe);
        swipeSound.start();
        swipeSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
        });
    } 
    */
    private int attackRight() {
        // TODO: add swipe right animation and sound
  //      playSwipe();

        // Generate a random number for damage
        Random randomGenerator = new Random();
        damage = randomGenerator.nextInt(8);
        TextView statusText = (TextView) findViewById(R.id.statusText);
        statusText.setText(damage + " damage!");
        return damage;
    }

    private int attackLeft() {
        // TODO: add swipe left animation and sound
  //      playSwipe();

        // Generate a random number for damage
        Random randomGenerator = new Random();
        damage = randomGenerator.nextInt(12);
        TextView statusText = (TextView) findViewById(R.id.statusText);
        statusText.setText(damage + " damage!");
        return damage;
    }

    private void userWins() {
    	TextView statusText = (TextView) findViewById(R.id.statusText);
    	ImageView demon = (ImageView) findViewById(R.id.Demon);
        demon.setVisibility(View.INVISIBLE);
        statusText.setText("You Win! Swipe Down to exit!");
        MediaPlayer winSound = MediaPlayer.create(this, R.raw.fanfare);
        winSound.start();
        winSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
        public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp.reset();
            }
        });

    }
    
/*    public void SoundManager(){}

    public void initSounds(Context theContext) { 
      mContext = theContext;
         mSoundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0); 
         mSoundPoolMap = new HashMap<Integer, Integer>();       
    } 

    public void addSound(int Index, int SoundID)
    {
     mAvailibleSounds.add(Index);
     mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));

    }

    public void playSound(int index) { 
     // dont have a sound for this obj, return.
     if(mAvailibleSounds.contains(index)){

         int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
         int soundId = mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, 1f);

         mKillSoundQueue.add(soundId);

         // schedule the current sound to stop after set milliseconds
         mHandler.postDelayed(new Runnable() {
          public void run() {
           if(!mKillSoundQueue.isEmpty()){
            mSoundPool.stop(mKillSoundQueue.firstElement());
           }
             }
         }, 3000);
     }
    }
    */



}

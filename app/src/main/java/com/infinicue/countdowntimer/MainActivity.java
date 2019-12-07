package com.infinicue.countdowntimer;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import java.net.URI;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {

    URI uri;
    Ringtone r;
    private static final long START_TIME_IN_MILLIS = 18000;

//creating objects <o...o>


  //  private static final int one_second = 2000;

    private TextView mTextViewCounDown;
    private Button mButtonStartStop;
    private Button mButtonReset;
    private Button mButtonplay;
    private TextView Attention;
    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private static final  double zero_seconds=1000;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCounDown = findViewById(R.id.text_view_countdown);

        mButtonStartStop = findViewById(R.id.button_start_stop);
        mButtonReset = findViewById(R.id.button_reset);


       TextView Attention=findViewById(R.id.text_view_attention);



        mButtonStartStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTimerRunning) {


                    stopTimer();

                } else {

                    startTimer();
                }

                         }

        });




        mButtonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

    }



//timer starts if its completed 180seconds hits alerts with notification or else goes to stop or reset

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {


            //timer if it completes 180sec then gives notification and alert message

            public void onTick(long millisUntilFinished) {

           if (millisUntilFinished <1000) {





                   //for beep sound
                //  ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 800);
                 //  toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);


                    //for notification


                   Intent i=new Intent(MainActivity.this,CardnotBack.class);
                   startActivity(i);

                    try{
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                        r.play();
                        r.play();

                        } catch (Exception e) {
                          e.printStackTrace();
                    }

                //    ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 800);

                 //  toneG.startTone(ToneGenerator.TONE_PROP_BEEP2);

                    //------0^-^0------

                    //for alert message
                    Intent intent = new Intent(MainActivity.this, CardnotBack.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("alert_icon_res_id", android.R.drawable.ic_dialog_info);
                    bundle.putString("alert_title", "ALERT..!!");
                    bundle.putString("alert_message", "Your card is not back..!!");
                    intent.putExtras(bundle);
                    startActivity(intent);

                }


//counter gets updated

            {

                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }



        }

        //after 180sec timer goes back to start timer

                public void onFinish() {
                mTimerRunning = false;
                mButtonStartStop.setText("Start");
                mButtonStartStop.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }


        }.start();

//for stopping timer
        mTimerRunning = true;
        mButtonStartStop.setText("Stop");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

//code for stopping  timer
    private void stopTimer() {
        mCountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mTimerRunning = false;
        mButtonStartStop.setText("Start");
        mButtonReset.setText("Reset");
        mButtonReset.setVisibility(View.VISIBLE);
    }



//resets timer

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartStop.setVisibility(View.VISIBLE);

    }


    //updating timer count like reverse countdown 9,8,7,6......0
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCounDown.setText(timeLeftFormatted);

    }


    }









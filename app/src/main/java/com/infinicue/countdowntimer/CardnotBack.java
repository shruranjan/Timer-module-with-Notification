package com.infinicue.countdowntimer;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;






        public class CardnotBack extends AppCompatActivity {


            private TextView Attention;
            //private Button Stop;


            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);


                Attention = findViewById(R.id.text_view_attention);

//getting message from main activity

                Bundle b = getIntent().getExtras();
                if (b != null && b.containsKey("alert_icon_res_id")) {
                    int icon = b.getInt("alert_icon_res_id");
                    String title = b.getString("alert_title");
                    String message = b.getString("alert_message");
                    new AlertDialog.Builder(this).setIcon(icon)
                            .setTitle(title).setMessage(message)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();


                }
            }
        }


















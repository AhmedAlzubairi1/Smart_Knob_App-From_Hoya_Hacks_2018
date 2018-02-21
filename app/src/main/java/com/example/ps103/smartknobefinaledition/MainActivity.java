package com.example.ps103.smartknobefinaledition;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
int value;
int endLocation;
int startLocation;
ImageView knob;
CountDownTimer x;
    TextView timer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("condition");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (TextView) findViewById(R.id.timeText);

       ////***************************HOW TO SET A VALUE********//////////
       // myRef.setValue(233);


        //************************WHEN IT IS READING******/////////
        myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            ///********CONTINUIUS READING******////

                                            value = dataSnapshot.getValue(int.class);

                                            Log.d("w", "Value is: " + value);
                                            //value is losing its value here
                                            // attack();
                                            Log.d("w", "BOB is: " + value);

                                            startLocation=0;
                                            endLocation=0;
                                            if((int)value==1){
                                                endLocation=90;
                                            }
                                            else if((int)value==2){
                                                endLocation=180;
                                            }
                                            else if((int)value==3){
                                                endLocation=270;

                                            }

                                            ObjectAnimator initialAnimtion =ObjectAnimator.ofFloat(knob,"rotation",startLocation,endLocation);
                                            AnimatorSet animatorSetInital = new AnimatorSet();
                                            animatorSetInital.playTogether(initialAnimtion);
                                            animatorSetInital.start();


                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w("u", "Failed to read value.", error.toException());
                                        }

                                    }
        );

        //stringValue=value.toString();

       // box.setText(stringValue);


    }


    public void goToFutureUpdate(View view){
        Context context = getApplicationContext();

        CharSequence text = "To be implemented in the future";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_SHORT).show();
    }
    public void activateSoup(View view){
        Context context = getApplicationContext();

        CharSequence text = "Soup Initiate ";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_SHORT).show();

        knob = (ImageView) findViewById(R.id.knobID);
        endLocation=180;
        startLocation=0;
        ObjectAnimator initialAnimtion =ObjectAnimator.ofFloat(knob,"rotation",startLocation,endLocation);
        AnimatorSet animatorSetInital = new AnimatorSet();
        animatorSetInital.playTogether(initialAnimtion);
        animatorSetInital.start();
        myRef.setValue(2);




       x =  new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                timer.setText(String.valueOf(millisUntilFinished/1000));
            }
            public  void onFinish(){
                timer.setText("0:00");
                Toast.makeText(getApplicationContext(), "FINISH SOUP", Toast.LENGTH_SHORT).show();
                endLocation=0;
                startLocation=180;
                ObjectAnimator returnAnimtion =ObjectAnimator.ofFloat(knob,"rotation",startLocation,endLocation);
                AnimatorSet returnSetInital = new AnimatorSet();
                returnSetInital.playTogether(returnAnimtion);
                returnSetInital.start();
            }
        }.start();

       // myRef.setValue(0);





    }

    public void turnOffApp(View view){


        if(endLocation==180 || endLocation==90){
            startLocation=endLocation;
            endLocation=0;
            ObjectAnimator returnAnimtion =ObjectAnimator.ofFloat(knob,"rotation",startLocation,endLocation);
            AnimatorSet returnSetInital = new AnimatorSet();
            returnSetInital.playTogether(returnAnimtion);
            returnSetInital.start();
            myRef.setValue(0);
        }
        else{
            startLocation=endLocation;
            endLocation=180;
            ObjectAnimator returnAnimtion =ObjectAnimator.ofFloat(knob,"rotation",startLocation,endLocation);
            AnimatorSet returnSetInital = new AnimatorSet();
            returnSetInital.playTogether(returnAnimtion);
            returnSetInital.start();
            myRef.setValue(2);
            endLocation=0;
            startLocation=180;

            ObjectAnimator returnAnimtion2 =ObjectAnimator.ofFloat(knob,"rotation",startLocation,endLocation);
            AnimatorSet returnSetInital2 = new AnimatorSet();
            returnSetInital2.playTogether(returnAnimtion2);
            returnSetInital2.start();
            myRef.setValue(0);


        }
        x.cancel();
        timer.setText("0:00");
        /*
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                timer.setText("0:00");
            }
            public  void onFinish(){
               timer.setText("0:00");
            }
        }.start();
        */
            }

}

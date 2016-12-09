package com.example.harshil.braintrainer;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static com.example.harshil.braintrainer.R.id.activity_main;
import static com.example.harshil.braintrainer.R.id.answer;
import static com.example.harshil.braintrainer.R.id.button;
import static com.example.harshil.braintrainer.R.id.rel1;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button start,button0,button1,button2,button3,playAgainButton;
    TextView resultTextView,sumTextView,finalAnswer;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    private String[] spinOptions={"Easy","Medium","Hard"};
    int locationofcorrectanswer;
    TextView pointsTextView;
    int score=0;
    String Item;
    LinearLayout startPage;
    int numberofQuestions=0;
    TextView timerTextView;
    RelativeLayout rel1,rel2;
    int highscore=0;
    Spinner spinner;
    public void playAgainGo(View view){
        startPage.setVisibility(View.VISIBLE);
        rel2.setVisibility(View.INVISIBLE);
        rel1.setVisibility(View.INVISIBLE);
    }
    public void playAgain(View view)
    {
        score=0;
        numberofQuestions=0;
        MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.clock);
        mediaPlayer.start();
        rel1.setVisibility(View.VISIBLE);
        rel2.setVisibility(View.INVISIBLE);
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        resultTextView.setTextSize(50f);
        generateQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+" sec");

            }

            @Override
            public void onFinish() {
                float accuracy;
                playAgainButton.setVisibility(View.VISIBLE);
                rel1.setVisibility(View.INVISIBLE);
                rel2.setVisibility(View.VISIBLE);
                String textfinal="Well Tried :)";
                accuracy=(float)score*100/numberofQuestions;
                String stringAccuracy;
                stringAccuracy=String.format("Accuracy: %.1f",accuracy);
                if(score>highscore&&accuracy>=60){highscore=score;textfinal="Congratulations :D";}
                timerTextView.setText("0s");
                if(score==0 && numberofQuestions==0)
                {                finalAnswer.setText("\nYour Score : " + Integer.toString(score)+"/"+Integer.toString(numberofQuestions) + "\n\n" + "Accuracy : 0.0%\n\n"+" High Score : "+Integer.toString(highscore)+"\n\n"+textfinal+"\n");
                }
                else{
                finalAnswer.setText("\nYour Score : " + Integer.toString(score)+"/"+Integer.toString(numberofQuestions) + "\n\n" + stringAccuracy +"%\n\n"+" High Score : "+Integer.toString(highscore)+"\n\n"+textfinal+"\n");
            }}
        }.start();
    }

    public void generateQuestion(){
        Random rand=new Random();
        Random rand1=new Random();
        int a,b;
        if(Item.equals("Easy")){
        a=rand.nextInt(21)+1;
        b=rand.nextInt(21)+1;}
        else if(Item.equals("Hard")){
            a=rand.nextInt(21)+70;
            b=rand.nextInt(21)+70;
        }
        else{
            a=rand.nextInt(21)+40;
            b=rand.nextInt(21)+40;
        }
        int op=rand.nextInt(4);
        locationofcorrectanswer=rand1.nextInt(4);
        answers.clear();
        int incorrectanswer;
        if(op==0) {
            sumTextView.setText("Q. "+Integer.toString(a) + " + " + Integer.toString(b)+" ?");
            for (int i = 0; i < 4; i++) {
                if (i == locationofcorrectanswer) {
                    answers.add(a + b);
                } else {
                    incorrectanswer = rand.nextInt(141)+50;
                    while (incorrectanswer == a + b) {

                        incorrectanswer = rand.nextInt(141)+50;
                    }
                    answers.add(incorrectanswer);
                }
            }
        }
        else if(op==1) {
            sumTextView.setText("Q. "+ Integer.toString(a) + "-" + Integer.toString(b)+" ?");
            for (int i = 0; i < 4; i++) {
                if (i == locationofcorrectanswer) {
                    answers.add(a-b);
                } else {
                    incorrectanswer = rand.nextInt(91);
                    while (incorrectanswer == a-b) {

                        incorrectanswer = rand.nextInt(91);
                    }
                    answers.add(-1*incorrectanswer);
                }
            }
        }
        else if(a*b<100&&op==2) {
            sumTextView.setText("Q. "+Integer.toString(a) + " X " + Integer.toString(b)+ " ?");
            for (int i = 0; i < 4; i++) {
                if (i == locationofcorrectanswer) {
                    answers.add(a*b);
                } else {
                    incorrectanswer = rand.nextInt(3000)+1001;
                    while (incorrectanswer == a*b) {

                        incorrectanswer = rand.nextInt(3000)+1001;
                    }
                    answers.add(incorrectanswer);
                }
            }
        }
        else if(a*b>100&&op==2) {
            sumTextView.setText("Q. "+Integer.toString(a) + " - " + Integer.toString(b)+" ?");
            for (int i = 0; i < 4; i++) {
                if (i == locationofcorrectanswer) {
                    answers.add(a-b);
                } else {
                    incorrectanswer = rand.nextInt(91);
                    while (incorrectanswer == a-b) {

                        incorrectanswer = rand.nextInt(91);
                    }
                    answers.add(-1*incorrectanswer);
                }
            }
        }
        else{
            sumTextView.setText("Q. "+Integer.toString(a) + " ÷ " + Integer.toString(b)+" ?");
            for (int i = 0; i < 4; i++) {
                if (i == locationofcorrectanswer) {
                    answers.add(a/b);
                } else {
                    incorrectanswer = rand.nextInt(41);
                    while (incorrectanswer == a/b) {

                        incorrectanswer = rand.nextInt(41);
                    }
                    answers.add(incorrectanswer);
                }
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofcorrectanswer)))
        {
         score++;
            numberofQuestions++;

            resultTextView.setText("Correct :)");
            resultTextView.setTextColor(Color.rgb(124, 179, 66));
        }
        else{
            numberofQuestions++;
            resultTextView.setText("Wrong :(");
            resultTextView.setTextColor(Color.RED);
        }
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberofQuestions));
        generateQuestion();
    }
public void start(View view){
    startPage.setVisibility(View.INVISIBLE);
    playAgain(findViewById(R.id.playAgainButton));

}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=(Button)findViewById(R.id.button);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        rel1=(RelativeLayout)findViewById(R.id.rel1);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        pointsTextView=(TextView)findViewById(R.id.pointsTextView);
        button0=(Button)findViewById(R.id.button0);
        startPage=(LinearLayout)findViewById(R.id.startPage);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        resultTextView=(TextView)findViewById(R.id.answer);
        rel2=(RelativeLayout)findViewById(R.id.rel2);
        finalAnswer=(TextView)findViewById(R.id.finalAnswer);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinOptions);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setPrompt("Select Difficulty");
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Item=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(this,"Selected : "+Item,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

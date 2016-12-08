package com.example.harshil.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static com.example.harshil.braintrainer.R.id.activity_main;
import static com.example.harshil.braintrainer.R.id.answer;
import static com.example.harshil.braintrainer.R.id.rel1;

public class MainActivity extends AppCompatActivity {
    Button start,button0,button1,button2,button3,playAgainButton;
    TextView resultTextView,sumTextView,finalAnswer;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationofcorrectanswer;
    TextView pointsTextView;
    int score=0;
    int numberofQuestions=0;
    TextView timerTextView;
    RelativeLayout rel1,rel2;
    int highscore=0;
    public void playAgain(View view)
    {
        score=0;
        numberofQuestions=0;
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
                playAgainButton.setVisibility(View.VISIBLE);
                rel1.setVisibility(View.INVISIBLE);
                rel2.setVisibility(View.VISIBLE);
                String textfinal="Well Tried :)";
                if(score>highscore){highscore=score;textfinal="Congratulations :D";}
                timerTextView.setText("0s");
                if(score==0 && numberofQuestions==0)
                {                finalAnswer.setText("\nYour Score : " + Integer.toString(score)+"/"+Integer.toString(numberofQuestions) + "\n\n" + "Accuracy : 0%\n\n"+" High Score : "+Integer.toString(highscore)+"\n\n"+textfinal+"\n");
                }
                else{
                finalAnswer.setText("\nYour Score : " + Integer.toString(score)+"/"+Integer.toString(numberofQuestions) + "\n\n" + "Accuracy : "+ Float.toString(score*100/numberofQuestions)+"%\n\n"+" High Score : "+Integer.toString(highscore)+"\n\n"+textfinal+"\n");
            }}
        }.start();
    }

    public void generateQuestion(){
        Random rand=new Random();
        Random rand1=new Random();
        int a=rand.nextInt(31);
        int b=rand.nextInt(31);
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
                    incorrectanswer = rand.nextInt(41);
                    while (incorrectanswer == a + b) {

                        incorrectanswer = rand.nextInt(41);
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
                    incorrectanswer = rand.nextInt(41);
                    while (incorrectanswer == a-b) {

                        incorrectanswer = rand.nextInt(41);
                    }
                    answers.add(incorrectanswer);
                }
            }
        }
        else if(a*b<100&&op==2) {
            sumTextView.setText("Q. "+Integer.toString(a) + " X " + Integer.toString(b)+ " ?");
            for (int i = 0; i < 4; i++) {
                if (i == locationofcorrectanswer) {
                    answers.add(a*b);
                } else {
                    incorrectanswer = rand.nextInt(41);
                    while (incorrectanswer == a*b) {

                        incorrectanswer = rand.nextInt(41);
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
                    incorrectanswer = rand.nextInt(41);
                    while (incorrectanswer == a-b) {

                        incorrectanswer = rand.nextInt(41);
                    }
                    answers.add(incorrectanswer);
                }
            }
        }
        else{
            sumTextView.setText("Q. "+Integer.toString(a) + " / " + Integer.toString(b)+" ?");
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
    start.setVisibility(View.INVISIBLE);
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
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        resultTextView=(TextView)findViewById(R.id.answer);
        rel2=(RelativeLayout)findViewById(R.id.rel2);
        finalAnswer=(TextView)findViewById(R.id.finalAnswer);

    }
}

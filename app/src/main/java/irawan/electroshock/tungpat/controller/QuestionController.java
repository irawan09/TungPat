package irawan.electroshock.tungpat.controller;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.R;
import irawan.electroshock.tungpat.databinding.FragmentAdditionBinding;

public class QuestionController {
    FragmentAdditionBinding bindingAddition;
    private final ArrayList<Integer> answer = new ArrayList<>();
    private int numberOfQuestions=0;
    private int score=0;

    private int locationOfCorrectAnswer, Tag;

    private void generateAdditionQuestion(Context context){
        bindingAddition.button0.setVisibility(View.VISIBLE);
        bindingAddition.button1.setVisibility(View.VISIBLE);
        bindingAddition.button2.setVisibility(View.VISIBLE);
        bindingAddition.button3.setVisibility(View.VISIBLE);

        Random random = new Random();
        int a = random.nextInt(101);
        int b = random.nextInt(101);
        int incorrectAnswer;
        locationOfCorrectAnswer = random.nextInt(4);
        bindingAddition.sumTextView.setText(context.getResources().getString(R.string.plus, a, b));

        answer.clear();
        for(int i=0;i<4;i++){
            if(i == locationOfCorrectAnswer){
                answer.add(a + b);
            } else{
                incorrectAnswer = random.nextInt(201);
                while(incorrectAnswer == a + b){
                    incorrectAnswer = random.nextInt(201);
                }
                answer.add(incorrectAnswer);
            }
        }

        bindingAddition.button0.setText(String.valueOf(answer.get(0)));
        bindingAddition.button1.setText(String.valueOf(answer.get(1)));
        bindingAddition.button2.setText(String.valueOf(answer.get(2)));
        bindingAddition.button3.setText(String.valueOf(answer.get(3)));
        bindingAddition.pointsTextView2.setText(context.getResources().getString(R.string.results, score, numberOfQuestions));
        numberOfQuestions++;

//        countDownTimer();

        bindingAddition.button0.setOnClickListener(view1 -> {
            Tag = 0;
            chooseAnswerAddition(context, Tag);
        });

        bindingAddition.button1.setOnClickListener(view2 -> {
            Tag = 1;
            chooseAnswerAddition(context, Tag);
        });
        bindingAddition.button2.setOnClickListener(view3 -> {
            Tag = 2;
            chooseAnswerAddition(context, Tag);
        });
        bindingAddition.button3.setOnClickListener(view12 -> {
            Tag = 3;
            chooseAnswerAddition(context, Tag);

        });
    }

    private void chooseAnswerAddition(Context context, int TAG){
        bindingAddition.correctTextView.setVisibility(View.VISIBLE);
        if (TAG == locationOfCorrectAnswer){
            score++;
            bindingAddition.correctTextView.setText(context.getResources().getString(R.string.correct));
            bindingAddition.correctTextView.setTextColor(context.getResources().getColor(R.color.lime));
        } else {
            bindingAddition.correctTextView.setText(context.getResources().getString(R.string.wrong));
            bindingAddition.correctTextView.setTextColor(context.getResources().getColor(R.color.red));
        }
//        countDown.cancel();
//        play();
    }
}

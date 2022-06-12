package irawan.electroshock.tungpat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Random random;
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions =0;
    int TAG;
    TextView question, correct, timer, points;
    Button button0, button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidgets(binding);

        binding.startButton.setOnClickListener(view -> {
            binding.startButton.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.VISIBLE);
            points.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);
            button0.setVisibility(View.VISIBLE);
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            questionGenerator();

            button0.setOnClickListener(view1 -> {
                TAG = 0;
                chooseAnswer(TAG);
            });
            button1.setOnClickListener(view2 -> {
                TAG = 1;
                chooseAnswer(TAG);
            });
            button3.setOnClickListener(view3 -> {
                TAG = 2;
                chooseAnswer(TAG);
            });
            button3.setOnClickListener(view12 -> {
                TAG = 3;
                chooseAnswer(TAG);
            });
        });
    }
    private void initWidgets(ActivityMainBinding binding){
        question = binding.sumTextView;
        button0 = binding.button0;
        button1 = binding.button1;
        button2 = binding.button2;
        button3 = binding.button3;
        correct = binding.correctTextView;
        timer = binding.timerTextView;
        points = binding.pointsTextView2;

        question.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        correct.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        points.setVisibility(View.INVISIBLE);
    }

    private void questionGenerator(){
        random = new Random();
        int a = random.nextInt(101);
        int b = random.nextInt(101);
        int incorrectAnswer;
        locationOfCorrectAnswer = random.nextInt(4);

        question.setText(getString(R.string.plus, a, b));

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

        button0.setText(String.valueOf(answer.get(0)));
        button1.setText(String.valueOf(answer.get(1)));
        button2.setText(String.valueOf(answer.get(2)));
        button3.setText(String.valueOf(answer.get(3)));
    }

    private void chooseAnswer(int TAG){
        correct.setVisibility(View.VISIBLE);
        if (TAG == locationOfCorrectAnswer){
            score++;
            correct.setText(getString(R.string.correct));
        } else {
            correct.setText(getString(R.string.wrong));
        }
        questionGenerator();
    }
}
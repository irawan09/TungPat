package irawan.electroshock.tungpat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Random random = new Random();
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView question, correct, timer, points;
    Button button0, button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initWidgets(binding);
        questionGenerator();

        binding.startButton.setOnClickListener(view -> {
            binding.startButton.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.VISIBLE);
            points.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);
            button0.setVisibility(View.VISIBLE);
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);

            button0.setText(String.valueOf(answer.get(0)));
            button1.setText(String.valueOf(answer.get(1)));
            button2.setText(String.valueOf(answer.get(2)));
            button3.setText(String.valueOf(answer.get(3)));
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
    }
}
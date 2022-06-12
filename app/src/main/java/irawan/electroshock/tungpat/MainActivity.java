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

    Random random = new Random();
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView question = binding.sumTextView;
        Button button0 = binding.button0;
        Button button1 = binding.button1;
        Button button2 = binding.button2;
        Button button3 = binding.button3;
        TextView correct = binding.correctTextView;
        TextView timer = binding.timerTextView;
        TextView points = binding.pointsTextView2;


        int a = random.nextInt(101);
        int b = random.nextInt(101);
        randomAnswerGenerator(a,b);

        question.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        correct.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        points.setVisibility(View.INVISIBLE);

        binding.startButton.setOnClickListener(view -> {
            binding.startButton.setVisibility(View.INVISIBLE);
            timer.setVisibility(View.VISIBLE);
            points.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);
            button0.setVisibility(View.VISIBLE);
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
            question.setText(getString(R.string.plus, a, b));
            button0.setText(String.valueOf(answer.get(0)));
            button1.setText(String.valueOf(answer.get(1)));
            button2.setText(String.valueOf(answer.get(2)));
            button3.setText(String.valueOf(answer.get(3)));
        });
    }

    private void randomAnswerGenerator(int a, int b){
        int incorrectAnswer;

        locationOfCorrectAnswer = random.nextInt(4);
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
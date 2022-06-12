package irawan.electroshock.tungpat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

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
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new HomeFragment());
        transaction.commit();
//        binding.startButton.setOnClickListener(view -> {
//            binding.startButton.setVisibility(View.INVISIBLE);
//            timer.setVisibility(View.VISIBLE);
//            points.setVisibility(View.VISIBLE);
//            question.setVisibility(View.VISIBLE);
//            button0.setVisibility(View.VISIBLE);
//            button1.setVisibility(View.VISIBLE);
//            button2.setVisibility(View.VISIBLE);
//            button3.setVisibility(View.VISIBLE);
//            questionGenerator();
//


//        });
    }
}
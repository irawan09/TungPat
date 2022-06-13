package irawan.electroshock.tungpat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.databinding.FragmentAdditionBinding;

public class AdditionFragment extends Fragment {

    FragmentAdditionBinding binding;
    Random random;
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer, Tag;
    int score=0;
    int numberOfQuestions=0;
    TextView question, correct, timer, points;
    int secondsLeft = 0;
    CountDownTimer countDown;

    public AdditionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdditionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidgets(binding);
        play();
    }

    private void initWidgets(FragmentAdditionBinding binding){
        question = binding.sumTextView;
        correct = binding.correctTextView;
        timer = binding.timerTextView;
        points = binding.pointsTextView2;
        correct.setVisibility(View.INVISIBLE);
        binding.retry.setVisibility(View.INVISIBLE);
        binding.mainMenu.setVisibility(View.INVISIBLE);
    }

    private void play(){
        generateQuestion();
    }

    private void generateQuestion(){
        binding.button0.setVisibility(View.VISIBLE);
        binding.button1.setVisibility(View.VISIBLE);
        binding.button2.setVisibility(View.VISIBLE);
        binding.button3.setVisibility(View.VISIBLE);

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

        binding.button0.setText(String.valueOf(answer.get(0)));
        binding.button1.setText(String.valueOf(answer.get(1)));
        binding.button2.setText(String.valueOf(answer.get(2)));
        binding.button3.setText(String.valueOf(answer.get(3)));
        binding.pointsTextView2.setText(getString(R.string.results, score, numberOfQuestions));
        numberOfQuestions++;

        countDownTimer();

        binding.button0.setOnClickListener(view1 -> {
            Tag = 0;
            chooseAnswer(Tag);
        });

        binding.button1.setOnClickListener(view2 -> {
            Tag = 1;
            chooseAnswer(Tag);
        });
        binding.button2.setOnClickListener(view3 -> {
            Tag = 2;
            chooseAnswer(Tag);
        });
        binding.button3.setOnClickListener(view12 -> {
            Tag = 3;
            chooseAnswer(Tag);

        });
    }

    private void chooseAnswer(int TAG){
        correct.setVisibility(View.VISIBLE);
        if (TAG == locationOfCorrectAnswer){
            score++;
            correct.setText(getString(R.string.correct));
            correct.setTextColor(getResources().getColor(R.color.lime));
        } else {
            correct.setText(getString(R.string.wrong));
            correct.setTextColor(getResources().getColor(R.color.red));
        }
        countDown.cancel();
        play();
    }

    private void countDownTimer(){
        countDown = new CountDownTimer( 10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (Math.round((float)millisUntilFinished / 1000.0f) != secondsLeft)
                {
                    secondsLeft = Math.round((float)millisUntilFinished / 1000.0f);
                }
                Log.i("Timer", String.valueOf(secondsLeft));
                Thread ui = new Thread(() -> requireActivity().runOnUiThread(() -> {
                    timer.setText(getString(R.string.timer, millisUntilFinished/1000));
                    question.setVisibility(View.VISIBLE);
                }));
                ui.start();

            }

            @Override
            public void onFinish() {
                countDown.cancel();
                Thread ui = new Thread(() -> requireActivity().runOnUiThread(() -> {
                    binding.retry.setVisibility(View.VISIBLE);
                    binding.mainMenu.setVisibility(View.VISIBLE);
                    timer.setText(getString(R.string._0s));
                    question.setVisibility(View.INVISIBLE);
                    binding.button0.setVisibility(View.INVISIBLE);
                    binding.button1.setVisibility(View.INVISIBLE);
                    binding.button2.setVisibility(View.INVISIBLE);
                    binding.button3.setVisibility(View.INVISIBLE);
                    correct.setVisibility(View.INVISIBLE);
                    points.setText(getString(R.string.final_score, score, numberOfQuestions));

                    binding.retry.setOnClickListener(view -> {
                        score=0;
                        numberOfQuestions=0;
                        play();
                    });

                    binding.mainMenu.setOnClickListener(view -> {
                        FragmentTransaction transaction = requireActivity()
                                .getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.frameLayout, new HomeFragment());
                        transaction.commit();
                    });
                }));
                ui.start();

            }
        };
        countDown.start();
    }
}
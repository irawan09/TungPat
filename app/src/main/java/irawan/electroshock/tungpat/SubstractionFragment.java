package irawan.electroshock.tungpat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.databinding.FragmentSubstractionBinding;

public class SubstractionFragment extends Fragment {

    FragmentSubstractionBinding binding;
    private final ArrayList<Integer> answer = new ArrayList<>();
    private int locationOfCorrectAnswer, Tag;
    private int score=0;
    private int numberOfQuestions=0;
    TextView question, correct, timer, points;
    Button button0, button1, button2, button3, retry, mainMenu;
    private int secondsLeft =0;
    private CountDownTimer countDown;

    public SubstractionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubstractionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidgets(binding);
        play();
    }

    private void play() {
        generateQuestion();
    }

    private void generateQuestion() {
        binding.buttonSubs0.setVisibility(View.VISIBLE);
        binding.buttonSubs1.setVisibility(View.VISIBLE);
        binding.buttonSubs2.setVisibility(View.VISIBLE);
        binding.buttonSubs3.setVisibility(View.VISIBLE);

        Random random = new Random();
        int a = random.nextInt(101);
        int b = random.nextInt(101);
        while(a <= b){
            b = random.nextInt(101);
        }

        int incorrectAnswer;
        locationOfCorrectAnswer = random.nextInt(4);
        question.setText(getString(R.string.substract, a, b));

        answer.clear();
        for(int i=0;i<4;i++){
            if(i == locationOfCorrectAnswer){
                answer.add(a - b);
            } else{
                incorrectAnswer = random.nextInt(201);
                while(incorrectAnswer == a - b){
                    incorrectAnswer = random.nextInt(201);
                }
                answer.add(incorrectAnswer);
            }
        }

        binding.buttonSubs0.setText(String.valueOf(answer.get(0)));
        binding.buttonSubs1.setText(String.valueOf(answer.get(1)));
        binding.buttonSubs2.setText(String.valueOf(answer.get(2)));
        binding.buttonSubs3.setText(String.valueOf(answer.get(3)));
        binding.pointsSubsTextView2.setText(getString(R.string.substract, score, numberOfQuestions));
        numberOfQuestions++;

        countDownTimer();

        binding.buttonSubs0.setOnClickListener(view1 -> {
            Tag = 0;
            chooseAnswer(Tag);
        });

        binding.buttonSubs1.setOnClickListener(view2 -> {
            Tag = 1;
            chooseAnswer(Tag);
        });
        binding.buttonSubs2.setOnClickListener(view3 -> {
            Tag = 2;
            chooseAnswer(Tag);
        });
        binding.buttonSubs3.setOnClickListener(view12 -> {
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

    private void countDownTimer() {
        countDown = new CountDownTimer( 8000, 1000) {

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
                    retry.setVisibility(View.VISIBLE);
                    mainMenu.setVisibility(View.VISIBLE);
                    timer.setText(getString(R.string._0s));
                    question.setVisibility(View.INVISIBLE);
                    button0.setVisibility(View.INVISIBLE);
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    correct.setVisibility(View.INVISIBLE);
                    points.setText(getString(R.string.final_score, score, numberOfQuestions));

                    retry.setOnClickListener(view -> {
                        score=0;
                        numberOfQuestions=0;
                        play();
                    });

                    mainMenu.setOnClickListener(view -> {
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

    private void initWidgets(FragmentSubstractionBinding binding) {
        question = binding.subsTextView;
        correct = binding.correctSubsTextView;
        timer = binding.timerSubsTextView;
        points = binding.pointsSubsTextView2;
        button0 = binding.buttonSubs0;
        button1 = binding.buttonSubs1;
        button2 = binding.buttonSubs2;
        button3 = binding.buttonSubs3;
        retry = binding.subsRetry;
        mainMenu = binding.subsMainMenu;
        correct.setVisibility(View.INVISIBLE);
        retry.setVisibility(View.INVISIBLE);
        mainMenu.setVisibility(View.INVISIBLE);
    }
}
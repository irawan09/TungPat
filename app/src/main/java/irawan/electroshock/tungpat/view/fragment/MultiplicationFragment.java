package irawan.electroshock.tungpat.view.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;

import irawan.electroshock.tungpat.R;
import irawan.electroshock.tungpat.databinding.AlertDialogTextEntryBinding;
import irawan.electroshock.tungpat.databinding.FragmentMultiplicationBinding;
import irawan.electroshock.tungpat.model.UsersScore;
import irawan.electroshock.tungpat.model.database.CRUDRecords;

public class MultiplicationFragment extends Fragment {

    FragmentMultiplicationBinding binding;
    private final ArrayList<Integer> answer = new ArrayList<>();
    private int locationOfCorrectAnswer, Tag;
    private int score=0;
    private int numberOfQuestions=0;
    TextView question, correct, timer, points;
    Button button0, button1, button2, button3;
    ImageButton retry, mainMenu, save;
    private int secondsLeft=0;
    private CountDownTimer countDown;
    
    public MultiplicationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMultiplicationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidgets(binding);
        play();
    }

    private void play() {
        retry.setVisibility(View.GONE);
        mainMenu.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
        generateQuestion();
    }

    private void generateQuestion() {
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        Random random = new Random();
        int a = random.nextInt(101);
        int b = random.nextInt(101);
        int incorrectAnswer;
        locationOfCorrectAnswer = random.nextInt(4);
        question.setText(getString(R.string.multiply, a, b));

        answer.clear();
        for(int i=0;i<4;i++){
            if(i == locationOfCorrectAnswer){
                answer.add(a * b);
            } else{
                incorrectAnswer = random.nextInt(2001);
                while(incorrectAnswer == a * b){
                    incorrectAnswer = random.nextInt(2001);
                }
                answer.add(incorrectAnswer);
            }
        }

        button0.setText(String.valueOf(answer.get(0)));
        button1.setText(String.valueOf(answer.get(1)));
        button2.setText(String.valueOf(answer.get(2)));
        button3.setText(String.valueOf(answer.get(3)));
        points.setText(getString(R.string.results, score, numberOfQuestions));
        numberOfQuestions++;

        countDownTimer();

        button0.setOnClickListener(view1 -> {
            Tag = 0;
            chooseAnswer(Tag);
        });

        button1.setOnClickListener(view2 -> {
            Tag = 1;
            chooseAnswer(Tag);
        });
        button2.setOnClickListener(view3 -> {
            Tag = 2;
            chooseAnswer(Tag);
        });
        button3.setOnClickListener(view12 -> {
            Tag = 3;
            chooseAnswer(Tag);

        });
    }

    private void chooseAnswer(int tag) {
        correct.setVisibility(View.VISIBLE);
        if (tag == locationOfCorrectAnswer){
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
                    save.setVisibility(View.VISIBLE);
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

                    save.setOnClickListener(view ->{
                        saveAlertDialog();
                    });
                }));
                ui.start();

            }
        };
        countDown.start();
    }

    private void saveAlertDialog(){
        // This example shows how to add a custom layout to an AlertDialog
        final AlertDialog.Builder alert = new AlertDialog.Builder(this.requireContext());
        LayoutInflater factory = LayoutInflater.from(this.requireContext());
        AlertDialogTextEntryBinding dialogBinding = AlertDialogTextEntryBinding.inflate(factory);
        alert.setView(dialogBinding.getRoot());

        alert.setIcon(R.drawable.ic_baseline_save);
        alert.setTitle(R.string.alert_dialog_text_entry);
        alert.setPositiveButton(R.string.alecrt_dialog_ok, (dialog, whichButton) -> {
            /* User clicked OK so do some stuff */
            String name =  dialogBinding.username.getText().toString().trim()+"";
            Log.i("Alert Dialog", name);
            UsersScore usersScore = new UsersScore();
            usersScore.setUsername(name);
            usersScore.setScore(String.valueOf(score));
            usersScore.setNumberOfQuestions(String.valueOf(numberOfQuestions));

            CRUDRecords database = new CRUDRecords(requireContext());
            database.insertUser(usersScore);
            dialog.dismiss();

        });
        alert.setNegativeButton(R.string.alert_dialog_cancel, (dialog, whichButton) -> {
            /* User clicked cancel so do some stuff */
            dialog.cancel();
        });
        alert.create();
        alert.show();
    }

    private void initWidgets(FragmentMultiplicationBinding binding) {
        question = binding.multiplyTextView;
        correct = binding.multiplyCorrectTextView;
        timer = binding.multiplyTimerTextView;
        points = binding.multiplyPointsTextView;
        button0 = binding.multiplyButton0;
        button1 = binding.multiplyButton1;
        button2 = binding.multiplyButton2;
        button3 = binding.multiplyButton3;
        retry = binding.multiplyRetry;
        mainMenu = binding.multiplyMainMenu;
        save = binding.multiplySave;
        correct.setVisibility(View.INVISIBLE);
        retry.setVisibility(View.GONE);
        mainMenu.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
    }
}
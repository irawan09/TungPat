package irawan.electroshock.tungpat.controller;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

import irawan.electroshock.tungpat.R;
import irawan.electroshock.tungpat.databinding.AlertDialogTextEntryBinding;
import irawan.electroshock.tungpat.databinding.FragmentAdditionBinding;
import irawan.electroshock.tungpat.model.UsersScore;
import irawan.electroshock.tungpat.model.database.CRUDRecords;
import irawan.electroshock.tungpat.view.Activity.MainActivity;
import irawan.electroshock.tungpat.view.fragment.HomeFragment;

public class TimerController {

    FragmentAdditionBinding binding;
    private int secondsLeft=0;
    CountDownTimer countDown;
    private int score;
    private int numberOfQuestions;
    MainActivity activity;

    public TimerController(int score, int numberOfQuestions) {
        this.score = score;
        this.numberOfQuestions = numberOfQuestions;
        AppController app = new AppController();
        this.activity = (MainActivity)  app.getApplicationContext();
    }

    private void countDownTimer(Context context){
        countDown = new CountDownTimer( 8000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (Math.round((float)millisUntilFinished / 1000.0f) != secondsLeft)
                {
                    secondsLeft = Math.round((float)millisUntilFinished / 1000.0f);
                }
                Log.i("Timer", String.valueOf(secondsLeft));
                Thread ui = new Thread(() -> activity.runOnUiThread(() -> {
                    binding.timerTextView.setText(context.getResources().getString(R.string.timer, millisUntilFinished/1000));
                    binding.sumTextView.setVisibility(View.VISIBLE);
                }));
                ui.start();

            }

            @Override
            public void onFinish() {
                countDown.cancel();
                Thread ui = new Thread(() -> activity.runOnUiThread(() -> {
                    binding.retry.setVisibility(View.VISIBLE);
                    binding.mainMenu.setVisibility(View.VISIBLE);
                    binding.save.setVisibility(View.VISIBLE);
                    binding.timerTextView.setText(activity.getApplicationContext().getResources().getString(R.string._0s));
                    binding.sumTextView.setVisibility(View.INVISIBLE);
                    binding.button0.setVisibility(View.INVISIBLE);
                    binding.button1.setVisibility(View.INVISIBLE);
                    binding.button2.setVisibility(View.INVISIBLE);
                    binding.button3.setVisibility(View.INVISIBLE);
                    binding.correctTextView.setVisibility(View.INVISIBLE);
                    binding.pointsTextView2.setText(activity.getApplicationContext().getResources().getString(R.string.final_score, score, numberOfQuestions));

                    binding.retry.setOnClickListener(view -> {
                        score=0;
                        numberOfQuestions=0;
//                        play();
                    });

                    binding.mainMenu.setOnClickListener(view -> {
                        FragmentTransaction transaction = activity
                                .getSupportFragmentManager()
                                .beginTransaction();

                        transaction.replace(R.id.frameLayout, new HomeFragment());
                        transaction.commit();
                    });

                    binding.save.setOnClickListener(view -> saveAlertDialog(score, numberOfQuestions));
                }));
                ui.start();

            }
        };
        countDown.start();
    }

    private void saveAlertDialog(int score, int numberOfQuestions){
        // This example shows how to add a custom layout to an AlertDialog
        final AlertDialog.Builder alert = new AlertDialog.Builder(this.activity);
        LayoutInflater factory = LayoutInflater.from(this.activity.getApplicationContext());
        AlertDialogTextEntryBinding dialogBinding = AlertDialogTextEntryBinding.inflate(factory);
        alert.setView(dialogBinding.getRoot());

        alert.setIcon(R.drawable.ic_baseline_save);
        alert.setTitle(R.string.alert_dialog_text_entry);
        alert.setPositiveButton(R.string.alecrt_dialog_ok, (dialog, whichButton) -> {
            String name =  Objects.requireNonNull(dialogBinding.username.getText()).toString().trim()+"";
            Log.i("Alert Dialog", name);

            UsersScore usersScore = new UsersScore();
            usersScore.setUsername(name);
            usersScore.setScore(String.valueOf(score));
            usersScore.setNumberOfQuestions(String.valueOf(numberOfQuestions));

            CRUDRecords database = new CRUDRecords(activity.getApplicationContext());
            database.insertUser(usersScore);
            dialog.dismiss();

        });
        alert.setNegativeButton(R.string.alert_dialog_cancel, (dialog, whichButton) -> dialog.cancel());
        alert.create();
        alert.show();
    }
}

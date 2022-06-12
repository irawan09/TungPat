package irawan.electroshock.tungpat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

import irawan.electroshock.tungpat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Random random = new Random();

        int a = random.nextInt(101);
        int b = random.nextInt(101);

        binding.startButton.setOnClickListener(view -> {
            binding.startButton.setVisibility(View.INVISIBLE);

            binding.sumTextView.setText(a +getResources().getString(R.string.plus)+ b);
        });


    }
}
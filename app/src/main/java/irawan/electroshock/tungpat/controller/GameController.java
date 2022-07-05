package irawan.electroshock.tungpat.controller;

import android.content.Context;
import android.view.View;

import irawan.electroshock.tungpat.databinding.FragmentAdditionBinding;

public class GameController {
    FragmentAdditionBinding binding;

    public void play(Context context){
        binding.retry.setVisibility(View.GONE);
        binding.mainMenu.setVisibility(View.GONE);
        binding.save.setVisibility(View.GONE);
        QuestionController question = new QuestionController();
        question.generateAdditionQuestion(context);
    }
}

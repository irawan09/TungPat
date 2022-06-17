package irawan.electroshock.tungpat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import irawan.electroshock.tungpat.R;
import irawan.electroshock.tungpat.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.additionButton.setOnClickListener(view1 -> {

            FragmentTransaction transaction = this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.frameLayout, new AdditionFragment());
            transaction.commit();

        });

        binding.substractionButton.setOnClickListener(view12 -> {
            FragmentTransaction transaction = this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.frameLayout, new SubstractionFragment());
            transaction.commit();
        });

        binding.multiplicationButton.setOnClickListener(view13 -> {
            FragmentTransaction transaction = this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.frameLayout, new MultiplicationFragment());
            transaction.commit();
        });

        binding.divisionButton.setOnClickListener(view14 -> {
            FragmentTransaction transaction = this.requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.frameLayout, new DivisionFragment());
            transaction.commit();
        });
    }
}
package irawan.electroshock.tungpat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import irawan.electroshock.tungpat.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
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
            Toast.makeText(requireActivity().getBaseContext(), "Fitur ini sedang di kembangkan", Toast.LENGTH_LONG).show();
        });

        binding.multiplicationButton.setOnClickListener(view13 -> {
            Toast.makeText(requireActivity().getBaseContext(), "Fitur ini sedang di kembangkan", Toast.LENGTH_LONG).show();
        });

        binding.divisionButton.setOnClickListener(view14 -> {
            Toast.makeText(requireActivity().getBaseContext(), "Fitur ini sedang di kembangkan", Toast.LENGTH_LONG).show();
        });
    }
}
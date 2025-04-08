package com.example.tema_quiz_19;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StartQuizFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start_quiz, container, false);

        Button startQuizButton = view.findViewById(R.id.Calea_Gabriela_start_button);
        startQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), QuizActivity.class);
            startActivity(intent);
        });

        return view;
    }
}

package com.example.tema_quiz_19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    private EditText etFirstName, etLastName, etBirthdate;
    private Spinner spinnerProfile;
    private Button btnSave;

    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        etFirstName = view.findViewById(R.id.Calea_Gabriela_et_first_name);
        etLastName = view.findViewById(R.id.Calea_Gabriela_et_last_name);
        etBirthdate = view.findViewById(R.id.Calea_Gabriela_et_birthdate);
        spinnerProfile = view.findViewById(R.id.Calea_Gabriela_spinner_profile);
        btnSave = view.findViewById(R.id.Calea_Gabriela_btn_save);


        mainActivity = (MainActivity) getActivity();


        loadUserProfile();

        btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                saveUserProfile();
            }
        });

        view.findViewById(R.id.Calea_Gabriela_fab_home_user).setOnClickListener(v -> {
            ((MainActivity) requireActivity()).goToStartQuizFragment();
        });

        return view;
    }

    private void loadUserProfile() {

        if (MainActivity.currentUser != null) {
            etFirstName.setText(MainActivity.currentUser.getFirstName());
            etLastName.setText(MainActivity.currentUser.getLastName());
            etBirthdate.setText(MainActivity.currentUser.getBirthdate());
            String profile = MainActivity.currentUser.getProfile();
            int profilePosition = getProfilePosition(profile);
            spinnerProfile.setSelection(profilePosition);
        }
    }

    private int getProfilePosition(String profile) {
        String[] profiles = getResources().getStringArray(R.array.profile_options);
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].equals(profile)) {
                return i;
            }
        }
        return 0;
    }

    private void saveUserProfile() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();


        String profile = spinnerProfile.getSelectedItem() != null ? spinnerProfile.getSelectedItem().toString() : "";

        MainActivity.currentUser = new User(firstName, lastName, birthdate, profile);

        Toast.makeText(getContext(), "Datele tale au fost salvate", Toast.LENGTH_SHORT).show();
    }

    private boolean validateFields() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();


        if (firstName.length() < 3) {
            Toast.makeText(getContext(), "Numele trebuie să aibă cel puțin 3 caractere", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lastName.length() < 3) {
            Toast.makeText(getContext(), "Prenumele trebuie să aibă cel puțin 3 caractere", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!DateValidator.isValidDate(birthdate)) {
            Toast.makeText(getContext(), "Data nu este validă. Folosește formatul dd/MM/yyyy", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}

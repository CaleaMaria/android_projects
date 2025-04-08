package com.example.tema_quiz_19;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQFragment extends Fragment {

    private ExpandableListView faqListView;
    private FAQAdapter faqAdapter;
    private List<String> questionList;
    private List<String> filteredQuestions;
    private HashMap<String, List<String>> answerMap;
    private HashMap<String, List<String>> filteredAnswerMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f_a_q, container, false);


        faqListView = view.findViewById(R.id.Calea_Gabriela_faq_list);

        prepareFAQData();

        faqAdapter = new FAQAdapter(requireContext(), questionList, answerMap);
        faqListView.setAdapter(faqAdapter);


        SearchView searchView = view.findViewById(R.id.Calea_Gabriela_search_faq);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterFAQData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterFAQData(newText);
                return true;
            }
        });


        view.findViewById(R.id.Calea_Gabriela_fab_home_faq).setOnClickListener(v -> {
            ((MainActivity) requireActivity()).goToStartQuizFragment();
        });

        return view;
    }

    private void prepareFAQData() {
        questionList = new ArrayList<>();
        answerMap = new HashMap<>();


        questionList.add("Ce este un model matematic aplicat?");
        questionList.add("Cum se folosește algebra liniară în matematică aplicată?");
        questionList.add("Ce este analiza numerică?");
        questionList.add("Ce sunt funcțiile matematice?");
        questionList.add("Cum se rezolvă ecuațiile diferențiale?");
        questionList.add("Ce sunt matricele și cum se folosesc?");

        List<String> answer1 = new ArrayList<>();
        answer1.add("Este o reprezentare matematică a unui sistem real utilizată pentru a înțelege și prezice comportamentul acestuia.");

        List<String> answer2 = new ArrayList<>();
        answer2.add("Algebra liniară este folosită în optimizare, procesarea imaginilor și simularea fenomenelor fizice.");

        List<String> answer3 = new ArrayList<>();
        answer3.add("Este o ramură a matematicii aplicate care dezvoltă algoritmi pentru rezolvarea problemelor numerice.");

        List<String> answer4 = new ArrayList<>();
        answer4.add("Funcțiile matematice sunt relații între elemente care iau valori în domeniu și produc valori în co-domeniu.");

        List<String> answer5 = new ArrayList<>();
        answer5.add("Ecuațiile diferențiale sunt folosite pentru a modela comportamentele dinamice și se rezolvă prin metode analitice sau numerice.");

        List<String> answer6 = new ArrayList<>();
        answer6.add("Matricele sunt tabele de numere care pot fi folosite în diverse calcule, inclusiv în soluționarea sistemelor liniare și transformări geometrice.");

        answerMap.put(questionList.get(0), answer1);
        answerMap.put(questionList.get(1), answer2);
        answerMap.put(questionList.get(2), answer3);
        answerMap.put(questionList.get(3), answer4);
        answerMap.put(questionList.get(4), answer5);
        answerMap.put(questionList.get(5), answer6);


        filteredQuestions = new ArrayList<>(questionList);
        filteredAnswerMap = new HashMap<>(answerMap);
    }

    private void filterFAQData(String query) {
        if (TextUtils.isEmpty(query)) {
            filteredQuestions = new ArrayList<>(questionList);
            filteredAnswerMap = new HashMap<>(answerMap);
        } else {
            filteredQuestions.clear();
            filteredAnswerMap.clear();

            for (String question : questionList) {
                if (question.toLowerCase().contains(query.toLowerCase())) {
                    filteredQuestions.add(question);
                    filteredAnswerMap.put(question, answerMap.get(question));
                }
            }
        }

        faqAdapter = new FAQAdapter(requireContext(), filteredQuestions, filteredAnswerMap);
        faqListView.setAdapter(faqAdapter);
    }
}

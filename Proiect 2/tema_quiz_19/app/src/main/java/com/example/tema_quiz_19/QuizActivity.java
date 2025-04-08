package com.example.tema_quiz_19;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private Button nextButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        questionTextView = findViewById(R.id.Calea_Gabriela_question_text);
        optionsGroup = findViewById(R.id.Calea_Gabriela_answers_group);
        nextButton = findViewById(R.id.Calea_Gabriela_next_button);


        prepareQuestions();

        displayQuestion();


        nextButton.setOnClickListener(v -> {

            int selectedOption = optionsGroup.getCheckedRadioButtonId();
            if (selectedOption == -1) {
                Toast.makeText(this, "Te rog să selectezi un răspuns înainte de a continua.", Toast.LENGTH_SHORT).show();
                return;
            }

            checkAnswer();

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayQuestion();
            } else {
                showFinalScore();
            }
        });
    }


    private void prepareQuestions() {
        questions = new ArrayList<>();

        questions.add(new Question("Ce reprezintă un model matematic aplicat?",
                Arrays.asList("O formulă simplă pentru calcule rapide.",
                        "O reprezentare matematică a unui sistem real utilizată pentru a înțelege și prezice comportamentul acestuia.",
                        "O metodă de rezolvare a ecuațiilor simple.",
                        "Un algoritm pentru procesare de date."),
                1));
        questions.add(new Question("Care este soluția ecuației liniare: 2x + 3 = 7?",
                Arrays.asList("x = 2", "x = 1", "x = 3", "x = 4"),
                0));
        questions.add(new Question("Calculați valoarea derivatei funcției: f(x) = x^3 - 2x + 5 la x = 2",
                Arrays.asList("10", "11", "12", "13"),
                1));
        questions.add(new Question("Găsiți valoarea expresiei: lim(x->0)(sin(x)/x)",
                Arrays.asList("1", "0", "∞", "-∞"),
                0));
        questions.add(new Question("Care este răspunsul corect pentru: ∫(0,1)(2x)dx?",
                Arrays.asList("1", "0.5", "2", "0.25"),
                0));
        questions.add(new Question("Cum este utilizată algebra liniară în matematică aplicată?",
                Arrays.asList("Pentru rezolvarea integralelor complexe.",
                        "Pentru a înțelege serii infinite.",
                        "În optimizare, procesarea imaginilor și simularea fenomenelor fizice.",
                        "Pentru a calcula derivate multiple."),
                2));
        questions.add(new Question("Calculați valoarea determinantului matricei: A = [2, 3; 1, 4]",
                Arrays.asList("2", "5", "10", "8"),
                1));
        questions.add(new Question("Rezolvați ecuația diferențială simplă: dy/dx = 3x, y(0) = 2",
                Arrays.asList("y = 3x^2 + 2", "y = x^2 + 2", "y = 3x^2 - 2", "y = 3x + 2"),
                1));
        questions.add(new Question("Găsiți soluția sistemului de ecuații liniare: x + y = 5; x - y = 1",
                Arrays.asList("x = 3, y = 2", "x = 4, y = 1", "x = 2, y = 3", "x = 1, y = 4"),
                0));
        questions.add(new Question("Calculați valoarea integralului: ∫(0,2)(3x^2)dx",
                Arrays.asList("12", "8", "16", "6"),
                2));
    }


    private void displayQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);

        questionTextView.setText(currentQuestion.getQuestionText());
        optionsGroup.clearCheck();
        optionsGroup.removeAllViews();

        for (int i = 0; i < currentQuestion.getOptions().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(currentQuestion.getOptions().get(i));
            radioButton.setId(i);


            radioButton.setTextSize(22);
            radioButton.setPadding(26, 32, 26, 32);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
            ));

            optionsGroup.addView(radioButton);
        }
    }


    private void checkAnswer() {
        int selectedOption = optionsGroup.getCheckedRadioButtonId();
        if (selectedOption == questions.get(currentQuestionIndex).getCorrectAnswerIndex()) {
            score++;
        }
    }

    private void showFinalScore() {
        Toast.makeText(this, "Scorul final: " + score + "/" + questions.size(), Toast.LENGTH_LONG).show();
        finish();
    }
}

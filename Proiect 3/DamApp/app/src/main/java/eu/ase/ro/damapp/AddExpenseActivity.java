package eu.ase.ro.damapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import eu.ase.ro.damapp.model.DateConverter;
import eu.ase.ro.damapp.model.Expense;

public class AddExpenseActivity extends AppCompatActivity {
    public static final String EXPENSE_KEY = "expense_key";

    private TextInputEditText tietDate;
    private TextInputEditText tietAmount;
    private TextInputEditText tietDescription;
    private Spinner spnCategory;
    private Button btnSave;
    private ActivityResultLauncher<Intent> launcher;


    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponents();
        intent = getIntent();

        Expense receivedExpense = intent.getParcelableExtra(EXPENSE_KEY);
        if (receivedExpense != null) {
            populateViews(receivedExpense);
        }

    }

    private void populateViews(Expense expense) {
        tietDate.setText(DateConverter.fromDate(expense.getDate()));
        tietAmount.setText(String.valueOf(expense.getAmount()));
        tietDescription.setText(expense.getDescription());


        String[] categories = getResources().getStringArray(R.array.categories);
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(expense.getCategory())) {
                spnCategory.setSelection(i);
                break;
            }
        }
    }
    private void initComponents() {
        tietDate = findViewById(R.id.add_tiet_date);
        tietAmount = findViewById(R.id.add_tiet_amount);
        tietDescription = findViewById(R.id.add_tiet_description);
        spnCategory = findViewById(R.id.add_spn_category);
        btnSave = findViewById(R.id.add_btn_save);
        btnSave.setOnClickListener(getSaveEvent());
    }


    private View.OnClickListener getSaveEvent() {
        return v -> {
            if (isValid()) {
                Expense expense = buildFromView();
                intent.putExtra(EXPENSE_KEY, expense);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }




    private Expense buildFromView() {
        Date date = DateConverter.toDate(tietDate.getText().toString());
        double amount = Double.parseDouble(tietAmount.getText().toString());
        String category = (String) spnCategory.getSelectedItem();
        String description = tietDescription.getText().toString();


        String id = null;
        if (intent != null) {
            Expense existingExpense = intent.getParcelableExtra(EXPENSE_KEY);
            if (existingExpense != null) {
                id = existingExpense.getId();
            }
        }

        Expense expense = new Expense(date, amount, category, description);
        if (id != null) {
            expense.setId(id);
        }

        return expense;
    }

    private boolean isValid() {
        if (tietDate.getText() == null
                || DateConverter.toDate(tietDate.getText().toString()) == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_date,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (tietAmount.getText() == null ||
                tietAmount.getText().toString().trim().isEmpty()
                || Double.parseDouble(tietAmount.getText().toString()) < 0) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_invalid_amount,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
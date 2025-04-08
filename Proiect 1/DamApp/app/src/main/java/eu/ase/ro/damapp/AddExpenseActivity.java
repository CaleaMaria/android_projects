package eu.ase.ro.damapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

import eu.ase.ro.damapp.fragments.DateConverter;
import eu.ase.ro.damapp.fragments.Expense;

public class AddExpenseActivity extends AppCompatActivity {
    public static final String EXPENSE_KEY = "expense_key";
    private TextInputEditText tietDate;
    private TextInputEditText tietAmount;
    private Spinner spnCategoryType;
    private TextInputEditText tietDescription;

    private Button btnSave;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.calea_gabriela_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponents();
        intent = getIntent();
    }

    private void initComponents(){
        tietAmount = findViewById(R.id.calea_gabriela_add_tiet_amount);
        tietDate = findViewById(R.id.calea_gabriela_add_tiet_date);
        tietDescription = findViewById(R.id.calea_gabriela_add_tiet_description);
        spnCategoryType = findViewById(R.id.calea_gabriela_add_spn_category);
        btnSave = findViewById(R.id.calea_gabriela_add_btn_save);
        btnSave.setOnClickListener(v -> {
            if(isValid()){
                Expense expense = buildExpenseFromView();
                intent.putExtra(EXPENSE_KEY, expense);
                setResult(RESULT_OK, intent);
                finish();
                Log.i("AddActivity", "Expense = " + expense);
            }
        });
    }

    private Expense buildExpenseFromView() {
        Date date = DateConverter.toDate(tietDate.getText().toString());
        String category = (String) spnCategoryType.getSelectedItem();
        String description = tietDescription.getText().toString();
        String amount = tietAmount.getText().toString();

        return new Expense(date, amount, category, description);
    }

    private boolean isValid(){
        if (tietDate.getText() == null
                || DateConverter.toDate(tietDate.getText().toString()) == null) {

            Toast.makeText(getApplicationContext(), R.string.add_invalid_date, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tietAmount.getText() == null || tietAmount.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_invalid_amount, Toast.LENGTH_SHORT).show();
        } else {
            try {
                int amount = Integer.parseInt(tietAmount.getText().toString().trim());
                if (amount < 0) {
                    Toast.makeText(getApplicationContext(), R.string.add_amount_must_be_greater_than_0, Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), R.string.add_amount_must_be_a_valid_decimal_number, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
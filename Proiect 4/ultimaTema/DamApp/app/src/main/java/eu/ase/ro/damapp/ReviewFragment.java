package eu.ase.ro.damapp;
import static eu.ase.ro.damapp.R.string.niciun_review_selectat;
import static eu.ase.ro.damapp.R.string.review_ul_trebuie_sa_contina_minim_5_caractere;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {
    private static final String PREFS_NAME = "ReviewPrefs";
    private static final String REVIEWS_KEY = "reviews";

    private RatingBar ratingBar;
    private TextView reviewInput;
    private ListView reviewListView;
    private FloatingActionButton saveFab, deleteFab, cancelFab;

    private final List<Review> reviews = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private int selectedIndex = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review2, container, false);

        initViews(view);
        loadReviewsFromPreferences();
        setupListeners();
        setupListView();

        return view;
    }

    private void initViews(View view) {
        ratingBar = view.findViewById(R.id.calea_gabriela_ratingBar);
        reviewInput = view.findViewById(R.id.calea_gabriela_reviewInput);
        reviewListView = view.findViewById(R.id.calea_gabriela_reviewListView);
        saveFab = view.findViewById(R.id.calea_gabriela_saveFab);
        deleteFab = view.findViewById(R.id.calea_gabriela_deleteFab);
        cancelFab = view.findViewById(R.id.calea_gabriela_cancelFab);
    }

    private void setupListeners() {
        saveFab.setOnClickListener(v -> saveReview());
        deleteFab.setOnClickListener(v -> deleteReview());
        cancelFab.setOnClickListener(v -> clearFields());
    }

    private void setupListView() {
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, getReviewDescriptions());
        reviewListView.setAdapter(adapter);

        reviewListView.setOnItemClickListener((parent, view, position, id) -> populateFields(position));
    }

    private void saveReview() {
        String description = reviewInput.getText().toString();
        float rate = ratingBar.getRating();

        if (description.length() < 5) {
            Toast.makeText(getContext(), review_ul_trebuie_sa_contina_minim_5_caractere, Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedIndex == -1) {
            Review review = new Review(description, rate);
            reviews.add(review);
        } else {
            Review review = reviews.get(selectedIndex);
            review.setDescription(description);
            review.setRate(rate);
        }

        saveReviewsToPreferences();
        updateListView();
        clearFields();
    }

    private void deleteReview() {
        if (selectedIndex == -1) {
            Toast.makeText(getContext(), niciun_review_selectat, Toast.LENGTH_SHORT).show();
            return;
        }

        reviews.remove(selectedIndex);
        saveReviewsToPreferences();
        updateListView();
        clearFields();
    }

    private void clearFields() {
        reviewInput.setText("");
        ratingBar.setRating(0);
        selectedIndex = -1;
    }

    private void populateFields(int position) {
        Review review = reviews.get(position);
        reviewInput.setText(review.getDescription());
        ratingBar.setRating(review.getRate());
        selectedIndex = position;
    }

    private List<String> getReviewDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (Review review : reviews) {
            descriptions.add(review.toString());
        }
        return descriptions;
    }

    private void updateListView() {
        adapter.clear();
        adapter.addAll(getReviewDescriptions());
        adapter.notifyDataSetChanged();
    }

    private void saveReviewsToPreferences() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        StringBuilder sb = new StringBuilder();
        for (Review review : reviews) {
            sb.append(review.getDescription()).append("|").append(review.getRate()).append(";");
        }

        editor.putString(REVIEWS_KEY, sb.toString());
        editor.apply();
    }

    private void loadReviewsFromPreferences() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedReviews = prefs.getString(REVIEWS_KEY, "");

        if (!savedReviews.isEmpty()) {
            String[] reviewEntries = savedReviews.split(";");
            for (String entry : reviewEntries) {
                String[] parts = entry.split("\\|");
                if (parts.length == 2) {
                    String description = parts[0];
                    float rate = Float.parseFloat(parts[1]);
                    reviews.add(new Review(description, rate));
                }
            }
        }
    }
}
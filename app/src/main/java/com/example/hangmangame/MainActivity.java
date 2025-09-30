package com.example.hangmangame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView wordText, attemptsText;
    private ProgressBar attemptsBar;
    // Use View so it works whether you used ImageView or ImageButton in XML
    private View hangmanImageView;
    private Button resetButton;
    private TableLayout lettersTable;

    private final String[] words = {
            "hello", "computer", "phone", "table", "chair", "window", "door", "keyboard", "mouse",
            "notebook", "pen", "book", "tree", "house", "car", "motorcycle", "bicycle", "road", "water",
            "drink", "food", "meal", "dog", "cat", "bird", "fish", "animal", "fruit", "vegetable", "apple",
            "banana", "orange", "grape", "tomato", "carrot", "salad", "lemon", "flower", "sun", "moon",
            "star", "sky", "cloud", "wind", "rain", "snow", "sea", "river", "lake", "mountain", "hill",
            "village", "city", "countryside", "forest", "prairie", "fields", "frog", "horse", "cow", "pig",
            "sheep", "wolf", "fox", "monkey", "tiger", "elephant", "snake", "dragon", "rabbit", "crocodile",
            "bee", "butterfly", "fly", "ant", "cockroach", "spider", "snail", "slug", "beetle", "deer", "boar",
            "rat", "mouse", "hamster", "dolphin", "shark", "whale", "octopus", "crab", "shrimp", "turtle",
            "pangolin", "giraffe", "zebra", "lion", "leopard", "cheetah", "hippopotamus"
    };

    private String secretWord;
    private char[] revealed;
    private final ArrayList<Character> guessed = new ArrayList<>();
    private int attemptsLeft;
    private final int maxAttempts = 6;

    // <-- DO NOT initialize with getResources() here -->
    private int[] hangmanStages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        // Build the image sequence NOW (safe: context is ready)
        // Use your renamed files: img1.png … img7.png
        hangmanStages = new int[] {
                R.drawable.a1, // 0 errors
                R.drawable.a2, // 1 error
                R.drawable.a3, // 2
                R.drawable.a4, // 3
                R.drawable.a5, // 4
                R.drawable.a6, // 5
                R.drawable.a7  // 6 errors
        };

        setupReset();
        startNewGame();
    }

    private void bindViews() {
        wordText = findViewById(R.id.wordText);
        attemptsText = findViewById(R.id.attemptsText);
        attemptsBar = findViewById(R.id.attemptsBar);
        hangmanImageView = findViewById(R.id.hangmanImage); // ImageView or ImageButton
        resetButton = findViewById(R.id.resetButton);
        lettersTable = findViewById(R.id.lettersTable);
    }

    private void setupReset() {
        resetButton.setOnClickListener(v -> startNewGame());
    }

    private void startNewGame() {
        secretWord = words[new Random().nextInt(words.length)].toUpperCase(Locale.US);
        revealed = new char[secretWord.length()];
        Arrays.fill(revealed, '_');
        guessed.clear();
        attemptsLeft = maxAttempts;

        updateWordText();
        updateAttemptsUI();
        enableAllLetterButtons(true);
        updateHangmanImage();
    }

    private void updateWordText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < revealed.length; i++) {
            sb.append(revealed[i]);
            if (i < revealed.length - 1) sb.append(' ');
        }
        wordText.setText(sb.toString());
    }

    private void updateAttemptsUI() {
        attemptsText.setText("Attempts left: " + attemptsLeft);
        attemptsBar.setMax(maxAttempts);
        attemptsBar.setProgress(attemptsLeft);
    }

    private void updateHangmanImage() {
        int errors = maxAttempts - attemptsLeft; // 0..6
        if (errors < 0) errors = 0;
        if (errors >= hangmanStages.length) errors = hangmanStages.length - 1;

        int resId = hangmanStages[errors];
        // Set on either ImageView or ImageButton
        if (hangmanImageView instanceof ImageView) {
            ((ImageView) hangmanImageView).setImageResource(resId);
        } else if (hangmanImageView instanceof ImageButton) {
            ((ImageButton) hangmanImageView).setImageResource(resId);
        }
    }

    public void letterClick(View v) {
        if (!(v instanceof Button)) return;
        Button b = (Button) v;
        String txt = b.getText().toString();
        if (txt.isEmpty()) return;

        char guess = txt.charAt(0);
        b.setEnabled(false);

        if (guessed.contains(guess)) return;
        guessed.add(guess);

        boolean hit = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess) {
                revealed[i] = guess;
                hit = true;
            }
        }

        if (!hit) {
            attemptsLeft--;
            updateAttemptsUI();
            updateHangmanImage();
            if (attemptsLeft <= 0) {
                for (int i = 0; i < secretWord.length(); i++) {
                    revealed[i] = secretWord.charAt(i);
                }
                updateWordText();
                Toast.makeText(this, "You lost! The word was: " + secretWord, Toast.LENGTH_LONG).show();
                enableAllLetterButtons(false);
            }
        } else {
            updateWordText();
            if (isWordRevealed()) {
                Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
                enableAllLetterButtons(false);
            }
        }
    }

    private boolean isWordRevealed() {
        for (char c : revealed) if (c == '_') return false;
        return true;
    }

    private void enableAllLetterButtons(boolean enabled) {
        for (int r = 0; r < lettersTable.getChildCount(); r++) {
            View rowView = lettersTable.getChildAt(r);
            if (rowView instanceof TableRow) {
                TableRow row = (TableRow) rowView;
                for (int i = 0; i < row.getChildCount(); i++) {
                    View child = row.getChildAt(i);
                    if (child instanceof Button) child.setEnabled(enabled);
                }
            }
        }
    }
}

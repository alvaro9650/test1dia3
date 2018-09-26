package com.example.alumno.test1dia3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SharedPreferencesApp extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    TextView score;
    EditText number;
    Button check;
    Integer secretnumber;
    Integer lastguess;
    Context context;
    Integer points;
    Toast explanationtoast;
    Toast highertoast;
    Toast lowertoast;
    Toast correcttoast;
    Random randomseed;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferencesapp);
        context = getApplicationContext();
        explanationtoast = Toast.makeText(context, R.string.explanation_toast_text, Toast.LENGTH_SHORT);
        explanationtoast.show();
        score = findViewById(R.id.textView);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        points = sharedpreferences.getInt("points", 0);
        score.setText(points.toString());
        editor = sharedpreferences.edit();
        highertoast = Toast.makeText(context, R.string.higher_toast_text, Toast.LENGTH_SHORT);
        lowertoast = Toast.makeText(context, R.string.lower_toast_text, Toast.LENGTH_SHORT);
        correcttoast = Toast.makeText(context, R.string.correct_toast_text, Toast.LENGTH_SHORT);
        number = findViewById(R.id.editText);
        check = findViewById(R.id.button);
        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                lastguess = Integer.parseInt(number.getText().toString());
                if (lastguess > secretnumber) {
                    highertoast.show();
                } else if (lastguess < secretnumber) {
                    lowertoast.show();
                } else if (lastguess.equals(secretnumber)) {
                    correcttoast.show();
                    points++;
                    score.setText(points.toString());
                    editor.putInt("points", points);
                    editor.apply();
                    secretnumber = randomseed.nextInt(51);
                } else {
                    explanationtoast.show();
                }

            }

        });
        randomseed = new Random();
        secretnumber = randomseed.nextInt(51);
    }
}

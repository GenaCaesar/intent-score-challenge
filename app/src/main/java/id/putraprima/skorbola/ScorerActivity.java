package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import id.putraprima.skorbola.model.Match;

public class ScorerActivity extends AppCompatActivity {
    public static final String ADD_KEY = "add";
    public static final String TIME_KEY = "time";
    EditText scorer,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scorer = findViewById(R.id.et_scorer);
        time = findViewById(R.id.et_time);
    }

    public void submitScorer(View view) {
        Intent intent = new Intent();
        intent.putExtra(ADD_KEY, scorer.getText().toString());
        intent.putExtra(TIME_KEY, time.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
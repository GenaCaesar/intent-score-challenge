package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import id.putraprima.skorbola.model.Match;

public class ResultActivity extends AppCompatActivity {

    private static final String DATA_KEY = "data";
    TextView tvWinner, tvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvWinner = findViewById(R.id.tv_winner);
        tvList = findViewById(R.id.tv_list);

        Bundle extras = getIntent().getExtras();
        Match match = getIntent().getParcelableExtra(DATA_KEY);
        if (extras != null) {
            if(match.resultScore().equals(match.getHomeTeam())) {
                tvWinner.setText("Pemenang Pertandingan : "+ match.resultScore());
                tvList.setText(match.homeScorer());
            }else if(match.resultScore().equals(match.getAwayTeam())) {
                tvWinner.setText("Pemenang Pertandingan : "+ match.resultScore());
                tvList.setText(match.awayScorer());
            }else{
                tvWinner.setText(match.resultScore());
                tvList.setText("Pertandingan Berakhir Seri");
            }
        }
    }
}
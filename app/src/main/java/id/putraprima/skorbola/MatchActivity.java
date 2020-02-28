package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.putraprima.skorbola.model.Match;

public class MatchActivity extends AppCompatActivity {
    public static final String DATA_KEY = "data";
    public static final String ADD_KEY = "add";
    private static final String TIME_KEY = "time";

    TextView ScoreHome, GoalHome;
    TextView ScoreAway, GoalAway;
    TextView Home;
    TextView Away;

    ImageView ivHome;
    ImageView ivAway;

    Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        Home = findViewById(R.id.txt_home);
        Away = findViewById(R.id.txt_away);
        GoalHome = findViewById(R.id.txt_home_last);
        GoalAway = findViewById(R.id.txt_away_last);
        ScoreHome = findViewById(R.id.score_home);
        ScoreAway = findViewById(R.id.score_away);
        ivHome = findViewById(R.id.home_logo);
        ivAway = findViewById(R.id.away_logo);
        initData();
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        match = getIntent().getParcelableExtra(DATA_KEY);
        if (extras != null) {
            Home.setText(match.getHomeTeam());
            Away.setText(match.getAwayTeam());
            ScoreHome.setText(String.valueOf(match.getHomeScore()));
            ScoreAway.setText(String.valueOf(match.getAwayScore()));
            Bitmap bitmapHome = null;
            Bitmap bitmapAway = null;
            try {
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), match.getHomeLogo());
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), match.getAwayLogo());
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load images", Toast.LENGTH_SHORT).show();
            }
            ivHome.setImageBitmap(bitmapHome);
            ivAway.setImageBitmap(bitmapAway);
        }
    }

    public void addHomeScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 1);
    }

    public void addAwayScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, 2);
    }

    public void submitCheck(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(DATA_KEY, match);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                match.addHomeScore(data.getStringExtra(ADD_KEY), data.getStringExtra(TIME_KEY));
                ScoreHome.setText(String.valueOf(match.getHomeScore()));
                GoalHome.setText(match.homeScorer());
                Log.d("who?", "scorer is " + match.getHomeScorer());
            }
        }else if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                match.addAwayScore(data.getStringExtra(ADD_KEY), data.getStringExtra(TIME_KEY));
                ScoreAway.setText(String.valueOf(match.getAwayScore()));
                GoalAway.setText(match.awayScorer());
                Log.d("who?", "scorer is " + match.getHomeScorer());
            }
        }
    }
}
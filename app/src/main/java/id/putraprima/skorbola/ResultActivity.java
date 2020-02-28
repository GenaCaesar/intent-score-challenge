package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView hasilLaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        hasilLaga = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("results");

        if(bundle != null){
            hasilLaga.setText(value);
        }
    }
}

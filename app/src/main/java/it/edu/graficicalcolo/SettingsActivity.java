package it.edu.graficicalcolo;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupHyperlink();
    }


    private void setupHyperlink() {
        TextView licenses = findViewById(R.id.textView);
        licenses.setText(Html.fromHtml(getString(R.string.license), Html.FROM_HTML_MODE_LEGACY));
        licenses.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
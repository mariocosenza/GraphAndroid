package it.edu.graficicalcolo;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {
    public static boolean ANIMATE = false;
    private SwitchMaterial switchAnimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchAnimate = findViewById(R.id.switchAnimate);
        switchAnimate.setChecked(ANIMATE);
        setupHyperlink();
    }

    public void switched(View view) {
        ANIMATE = switchAnimate.isChecked();

    }

    private void setupHyperlink() {
        TextView licenses = findViewById(R.id.textView);
        licenses.setText(Html.fromHtml(getString(R.string.license), Html.FROM_HTML_MODE_LEGACY));
        licenses.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
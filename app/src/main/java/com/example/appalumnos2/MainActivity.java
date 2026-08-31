package com.example.appalumnos2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.appalumnos2.db.DbHelper;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private MaterialButton btnCrearBD;
    private CardView cardConfirmacion;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrearBD = findViewById(R.id.btnCrearBD);
        cardConfirmacion = findViewById(R.id.cardConfirmacion);
        dbHelper = new DbHelper(this);

        btnCrearBD.setOnClickListener(v -> {
            try {
                dbHelper.getWritableDatabase();
                cardConfirmacion.setVisibility(View.VISIBLE);
                btnCrearBD.setEnabled(false);
                btnCrearBD.setAlpha(0.6f);
                Toast.makeText(this, "✅ Base de datos creada", Toast.LENGTH_LONG).show();

                btnCrearBD.postDelayed(() -> {
                    btnCrearBD.setEnabled(true);
                    btnCrearBD.setAlpha(1.0f);
                    cardConfirmacion.setVisibility(View.GONE);
                }, 3000);
            } catch (Exception e) {
                Toast.makeText(this, "❌ Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
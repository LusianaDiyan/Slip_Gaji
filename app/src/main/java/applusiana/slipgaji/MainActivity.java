package applusiana.slipgaji;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    EditText etGaji;
    TextView totalGaji, Gaji;
    RadioGroup rgStatus;
    RadioButton rbPns, rbHonor;
    CheckBox cbIstri, cbAnak;
    Button btnHitung, btnHapus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGaji = findViewById(R.id.Gaji);
        totalGaji = findViewById(R.id.TotalGaji);

        btnHitung = findViewById(R.id.Hitung);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rgStatus = findViewById(R.id.Status);
                rbPns = findViewById(R.id.Pns);
                rbHonor = findViewById(R.id.Honor);

                cbIstri = findViewById(R.id.Istri);
                cbAnak = findViewById(R.id.Anak);

                Double gaji = Double.valueOf(etGaji.getText().toString());

                int id = rgStatus.getCheckedRadioButtonId();
                if (id == -1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Silahakan Pilih Status");
                    builder.setNegativeButton("OK", null);
                    builder.create();
                    builder.show();
                } else if (id == R.id.Honor) {
                    gaji = gaji + 100000;

                } else if (id == R.id.Pns) {
                    gaji = gaji + 200000;
                }


                if (cbIstri.isChecked()) {
                    gaji = gaji + 200000;
                }
                if (cbAnak.isChecked()) {
                    gaji = gaji + 100000;
                }

                String strTotalGaji = "Total Penerimaan: Rp. " + " " + String.valueOf(gaji);
                totalGaji.setText(strTotalGaji);

                /*Gaji = findViewById(R.id.TotalGaji);
                Gaji.setText(String.valueOf(gaji));*/
            }
        });

        btnHapus = findViewById(R.id.Hapus);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etGaji.setText("");
                rgStatus.clearCheck();
                if (cbIstri.isChecked()){
                    cbIstri.setChecked(false);
                }
                if (cbAnak.isChecked()){
                    cbAnak.setChecked(false);
                }
                totalGaji.setText("");
            }
        });
    }

    @Override
    public void onBackPressed(){
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(getBaseContext(), "Tekan Back Sekali Lagi untuk keluar",
                    Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}

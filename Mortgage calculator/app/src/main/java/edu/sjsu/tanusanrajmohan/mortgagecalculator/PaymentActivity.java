package edu.sjsu.tanusanrajmohan.mortgagecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        final EditText tip = (EditText) findViewById(R.id.tip);
        final EditText mpa = (EditText) findViewById(R.id.mpa);
        final EditText ttp = (EditText) findViewById(R.id.tptp);
        final EditText pod = (EditText) findViewById(R.id.pod);

        Bundle bundle = getIntent().getExtras();

        double taxIntPay = bundle.getDouble("tip");
        double monthPay = bundle.getDouble("mpa");
        double totTaxPay = bundle.getDouble("ttp");
        int terms = bundle.getInt("terms");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM-yyyy");
        c.add(Calendar.YEAR, terms);

        String formattedDate = df.format(c.getTime());

        tip.setText(""+taxIntPay);
        mpa.setText(""+monthPay);
        ttp.setText(""+totTaxPay);
        pod.setText(formattedDate);

    }
}

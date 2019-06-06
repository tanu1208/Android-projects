package edu.sjsu.tanusanrajmohan.mortgagecalculator;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button calcB = (Button) findViewById(R.id.calcButton);
        final Button resetButton = (Button) findViewById(R.id.resetButton);
        final EditText homeValue = (EditText) findViewById(R.id.homeValue);
        final EditText downPayment = (EditText) findViewById(R.id.downPayment);
        final EditText interestRate = (EditText) findViewById(R.id.interestRate);
        final EditText propertyTax = (EditText) findViewById(R.id.propertyTax);

        final Spinner terms = (Spinner) findViewById(R.id.terms);
        Integer[] items = new Integer[]{15,20,25,30,40};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, items);
        terms.setAdapter(adapter);

        calcB.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                if(!homeValue.getText().toString().equals("") && !downPayment.getText().toString().equals("")
                        && !interestRate.getText().toString().equals("") && !propertyTax.getText().toString().equals("")){
                    final int termValue = (int) terms.getSelectedItem();
                    final int borrowed = Integer.parseInt(homeValue.getText().toString());
                    final int dp = Integer.parseInt(downPayment.getText().toString());
                    final int ir = Integer.parseInt(interestRate.getText().toString());
                    final int ptr = Integer.parseInt(propertyTax.getText().toString());

                    double ratePerMonth = ((double)ir/100)/12;
                    int monthsPerYear = termValue*12;

                    if(borrowed != 0 && dp != 0 && ratePerMonth != 0 && ptr != 0){
                        double mpa = borrowed*((ratePerMonth*(Math.pow((1+ratePerMonth),monthsPerYear)))/
                                (Math.pow((1+ratePerMonth),monthsPerYear)-1));
                        double tip = (mpa*monthsPerYear)-borrowed;
                        double ttp = dp*ptr;

                        Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                        Intent goToNextActivity = new Intent(getApplicationContext(), PaymentActivity.class);

                        Bundle bundle = new Bundle();
                        bundle.putDouble("mpa",mpa);
                        bundle.putDouble("tip",tip);
                        bundle.putDouble("ttp",ttp);
                        bundle.putInt("terms",termValue);

                        goToNextActivity.putExtras(bundle);
                        startActivity(goToNextActivity);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error...",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Enter a value in every field",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error...",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Missing a field",Toast.LENGTH_LONG).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(final View v) {
                homeValue.setText("");
                downPayment.setText("");
                interestRate.setText("");
                propertyTax.setText("");
            }
        });
    }
}
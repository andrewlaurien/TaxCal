package com.andrewlaurien.TaxCalculator;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btnCalc;
    EditText editSalary;
    TextView editSSS, editPhilHealth, editPagibig;
    TextView txtDeductions, txtTaxableIncome, txtWTax, txtNetIncome;
    double salary;
    double philHealthMultiplier = .0275;
    double PAGIBIG = 100;
    double sssContribution = 0;
    double wTax = 0;
    double philHealth = 0;
    double totalDeductions = 0;
    DecimalFormat dec;
    double taxableIncome = 0, netIncome = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dec = new DecimalFormat("###,###,###.##");

        btnCalc = findViewById(R.id.btnCalculate);
        editSalary = findViewById(R.id.editSalary);
        editSSS = findViewById(R.id.editSSS);
        editPhilHealth = findViewById(R.id.editPhilHealth);
        editPagibig = findViewById(R.id.editPAGIBIG);
        txtDeductions = findViewById(R.id.txtDeductions);
        txtTaxableIncome = findViewById(R.id.txtTaxableIncome);
        txtWTax = findViewById(R.id.txtWTax);
        txtNetIncome = findViewById(R.id.txtNetIncome);


        MobileAds.initialize(this, "ca-app-pub-3749753055066187~6007674614");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editSalary.getText().toString().isEmpty() || editSalary.getText().toString().equals("0")) {
                    Toast.makeText(getBaseContext(), "Invalid Salary", Toast.LENGTH_SHORT).show();
                    return;
                }
                salary = Double.parseDouble(editSalary.getText().toString());
                sssContribution = getSSS(salary);
                philHealth = getPhilHealth(salary);

                editSSS.setText(dec.format(sssContribution));
                editPagibig.setText(dec.format(PAGIBIG));
                editPhilHealth.setText(dec.format(philHealth));


                totalDeductions = sssContribution + philHealth + PAGIBIG;
                taxableIncome = salary - totalDeductions;
                wTax = getWTax(taxableIncome);
                txtWTax.setText(dec.format(wTax));
                txtDeductions.setText(dec.format(totalDeductions));
                txtTaxableIncome.setText(dec.format(taxableIncome));
                netIncome = taxableIncome - wTax;
                txtNetIncome.setText(dec.format(netIncome));

            }
        });


        editSalary.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // hide virtual keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editSalary.getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
                    return true;
                }
                return false;
            }
        });

    }


    //region Functions

    public double getWTax(double salary) {

        double result = 0;
        Log.d("Data", "" + salary);
        if (salary <= 20833) {
            result = 0;
            Log.d("Data", "Here1");
        } else if (salary > 20833 && salary <= 33332) {
            result = (salary - 20833) * .20;
            Log.d("Data", "Here2");
        } else if (salary > 33332 && salary <= 66666) {
            result = (salary - 33333) * .25 + 2500;
            Log.d("Data", "Here3");
        } else if (salary > 66666 && salary <= 166666) {
            result = (salary - 66667) * .30 + 10833.33;
            Log.d("Data", "Here4");
        } else if (salary > 166666 && salary <= 666666) {
            result = (salary - 166667) * .32 + 40833.33;
            Log.d("Data", "Here5");
        } else if (salary > 666666) {
            result = (salary - 666667) * .35 + 40833.33;
            Log.d("Data", "Here6");
        }

        return result;

    }

    public double getPhilHealth(double salary) {

        double result = 0;

        if (salary <= 10000) {
            result = 137.50;
        } else if (salary > 10000 && salary < 40000) {
            result = (salary * philHealthMultiplier) / 2;
        } else {
            result = 550;
        }

        return result;

    }

    public double getSSS(double salary) {

        double result = 0;


        if (salary > 4750 && salary < 5250) {
            result = 181.70;
        } else if (salary >= 5250 && salary < 5750) {
            result = 199.80;
        } else if (salary >= 5750 && salary < 6250) {
            result = 218.00;
        } else if (salary >= 6250 && salary < 6750) {
            result = 236.20;
        } else if (salary >= 6750 && salary < 7250) {
            result = 254.30;
        } else if (salary >= 7250 && salary < 7750) {
            result = 272.50;
        } else if (salary >= 7750 && salary < 8250) {
            result = 290.70;
        } else if (salary >= 8250 && salary < 8750) {
            result = 308.80;
        } else if (salary >= 8750 && salary < 9250) {
            result = 327.00;
        } else if (salary >= 9250 && salary < 9750) {
            result = 345.20;
        } else if (salary >= 9750 && salary < 10250) {
            result = 363.30;
        } else if (salary >= 10250 && salary < 10750) {
            result = 381.50;
        } else if (salary >= 10750 && salary < 11250) {
            result = 399.70;
        } else if (salary >= 11250 && salary < 11750) {
            result = 417.80;
        } else if (salary >= 11750 && salary < 12250) {
            result = 436;
        } else if (salary >= 12250 && salary < 12750) {
            result = 454.20;
        } else if (salary >= 12750 && salary < 13250) {
            result = 472.30;
        } else if (salary >= 13250 && salary < 13750) {
            result = 492.50;
        } else if (salary >= 13750 && salary < 14250) {
            result = 508.70;
        } else if (salary >= 14250 && salary < 14750) {
            result = 526.80;
        } else if (salary >= 14750 && salary < 15250) {
            result = 545;
        } else if (salary >= 15250 && salary < 15750) {
            result = 563.20;
        } else if (salary >= 15750) {
            result = 581.30;
        }


        return result;

    }


    //endregion

}

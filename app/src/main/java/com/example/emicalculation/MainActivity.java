package com.example.emicalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity<cal_EMI> extends AppCompatActivity {

    private TextView result;
    private EditText principle;
    private EditText rate;
    private EditText month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principle=(EditText) findViewById(R.id.pInput);
        rate=(EditText) findViewById(R.id.rInput);
        month=(EditText) findViewById(R.id.nInput);
        result=(TextView) findViewById(R.id.result);

    }

    public void calculateEMI(View v){

        String principleAmt=principle.getText().toString();
        String interestRate=rate.getText().toString();
        String months=month.getText().toString();

        if(TextUtils.isEmpty(principleAmt)){
            principle.setError("Please, enter Principle amount.");
            principle.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(interestRate)){
            rate.setError("Please, enter Interest rate.");
            rate.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(months)){
            month.setError("Please, number of monthly installments.");
            month.requestFocus();
            return;
        }

        if(principleAmt !=null && !"".equals(principleAmt) &&
        interestRate !=null && !"".equals(interestRate) &&
        months !=null && !"".equals(months)){

            double pValue=Double.parseDouble(principleAmt);
            double rValue=Double.parseDouble(interestRate);
            double mValue=Double.parseDouble(months);





            double P = cal_p(pValue);
            double R=cal_r(rValue);
            double N=cal_n(mValue);
            double D1 = cal_d1(R, N);
            double D2 = cal_d2(P, R, D1);
            double D3= cal_D3(D1);
            double EMI = cal_EMI(D2, D3);
            String EMILabel = " Your EMI is Rs : " + roundTwoDecimals(EMI);
            result.setText(EMILabel);



        }

    }

    double roundTwoDecimals(double a) {
        DecimalFormat form_value=new DecimalFormat("#.##");
        return Double.valueOf(form_value.format(a));

    }
    private double cal_p(double pValue) {
        return (double) (pValue);
    }

    private double cal_r(double rValue) {
        return (double)(rValue/12/100);
    }

    private double cal_n(double mValue) {
        return (double)(mValue);
    }

    private double cal_d1(double R, double N) {
        return (double)(Math.pow(1+ R ,N));
    }

    private double cal_d2(double P, double R, double D1) {
        return (double) (P*R*D1);

    }

    private double cal_D3(double D1) {
        return (double)(D1-1);

    }

    private double cal_EMI(double D2, double D3) {
        return (double)(D2/D3);
    }

}

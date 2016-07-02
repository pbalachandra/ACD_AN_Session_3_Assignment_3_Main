package com.acadgild.balu.acd_an_session_3_assignment_3_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    EditText et_enter_card_balance, et_enter_yearly_interest_rate, et_enter_minimum_payment,
            et_final_card_balance, et_months_remaining, et_interest_paid_will_be;
    Button btn_compute, btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);

        et_enter_card_balance = (EditText) findViewById(R.id.editText_enter_card_balance);
        et_enter_yearly_interest_rate = (EditText) findViewById(R.id.editText_enter_yearly_interest_rate);
        et_enter_minimum_payment = (EditText) findViewById(R.id.editText_enter_minimum_payment);

        et_final_card_balance = (EditText) findViewById(R.id.editText_final_card_balance);
        et_months_remaining = (EditText) findViewById(R.id.editText_months_remaining);
        et_interest_paid_will_be = (EditText) findViewById(R.id.editText_interest_paid_will_be);

        btn_compute = (Button) findViewById(R.id.button_compute);
        btn_clear = (Button) findViewById(R.id.button_clear);

        btn_compute.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.button_compute)
        {
            compute_amount();
        }
        else if (v.getId() == R.id.button_clear)
        {
            et_enter_card_balance.setText("");
            et_enter_yearly_interest_rate.setText("");
            et_enter_minimum_payment.setText("");
            et_final_card_balance.setText("");
            et_interest_paid_will_be.setText("");
            et_months_remaining.setText("");
        }
    }

    public void compute_amount()
    {
        float ft_monthlyfloatInterestPaid = 0, ft_monthlyPrinciple = 0, ft_monthlyBalance = 0;

        float result_monthlyfloatInterestPaid = 0, result_monthlyBalance = 0, result_monthsRemaining = 0;

        float ft_principle = Float.parseFloat(et_enter_card_balance.getText().toString());
        float ft_yearlyInterest = Float.parseFloat(et_enter_yearly_interest_rate.getText().toString());
        float ft_minimumPayment = Float.parseFloat(et_enter_minimum_payment.getText().toString());

        float counter = 0;
        do
        {
            counter++;
            ft_monthlyfloatInterestPaid = Math.round((ft_principle * (ft_yearlyInterest / (100 * 12))));
            ft_monthlyPrinciple = ft_minimumPayment - ft_monthlyfloatInterestPaid;
            ft_monthlyBalance = ft_principle - ft_monthlyPrinciple;
            ft_principle = ft_monthlyBalance;

            if (counter == 1)
            {
                result_monthlyBalance = ft_monthlyBalance;
                result_monthlyfloatInterestPaid = ft_monthlyfloatInterestPaid;
            }

        } while (ft_monthlyBalance > 0);

        result_monthsRemaining = counter - 1;

        et_final_card_balance.setText(String.valueOf(result_monthlyBalance));
        et_interest_paid_will_be.setText(String.valueOf(result_monthlyfloatInterestPaid));
        et_months_remaining.setText(String.valueOf(result_monthsRemaining));
    }
}

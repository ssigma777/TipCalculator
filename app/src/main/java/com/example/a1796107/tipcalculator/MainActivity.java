package com.example.a1796107.tipcalculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity {


    private EditText eBillAmount ;
    private TextView ePercent ;
    private TextView eTip;
    private TextView eTotal;
    private Button bMinus;
    private Button bPlus;
    private SeekBar sSeekBar;

    private double amount;
    private double percent;
    private double tip;

    final int STEP_TIP = 5;
    int minTip;
    int maxTip ;

    MainActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context  = this;
        percent = 15;
        minTip = 5 ;
        maxTip = 45 ;

        eBillAmount = (EditText) findViewById(R.id.etxt_BillAmount);
        ePercent = (TextView) findViewById(R.id.tv_Percent);
        eTip = (TextView) findViewById(R.id.tv_Tip);
        eTotal = (TextView) findViewById(R.id.tv_Total);

        bMinus = (Button) findViewById(R.id.btn_Minus);
        bPlus = (Button) findViewById(R.id.btn_Plus);

        sSeekBar = (SeekBar) findViewById(R.id.seekBar);
        sSeekBar.setMin(minTip);
        sSeekBar.setMax((maxTip-minTip)/ STEP_TIP);
        sSeekBar.setProgress((int)percent);
        updateValues();

        bMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                percent -= STEP_TIP;
                if (percent < minTip) {
                    Toast.makeText(context,"Can not go less than 5",Toast.LENGTH_SHORT).show();
                    percent += STEP_TIP;
                }
                updateValues();
            }
        });

        bPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                percent += STEP_TIP;
                if (percent > maxTip) {
                    Toast.makeText(context,"Cannot go Over 45",Toast.LENGTH_SHORT).show();
                    percent -= STEP_TIP;
                }
                updateValues();
            }
        });

        sSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                percent = progress * STEP_TIP ;
                updateValues();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        eBillAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                updateValues();
            }
        });
    }

    private void updateValues()
    {
        try {
            double amount = Double.parseDouble(eBillAmount.getText().toString());
            double tip = amount * percent / 100;
            double total = amount + tip ;

            ePercent.setText(Double.toString(percent));
            eTip.setText(Double.toString(tip));
            eTotal.setText(Double.toString(total));
            sSeekBar.setProgress((int)percent/STEP_TIP);

            //Snackbar snackbar = Snackbar.

        }
        catch(Exception e)
        {
            //d(tag,e);
        }
    }
}

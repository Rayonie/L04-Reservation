package sg.edu.rp.c346.id21045028.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText phone_no;
    EditText pax;
    DatePicker dp;
    TimePicker tp;
    CheckBox smoking_area;
    Button submit;
    Button reset;
    TextView output;
    TextView output2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name_field);
        phone_no = findViewById(R.id.mobile_number);
        pax = findViewById(R.id.pax);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        smoking_area = findViewById(R.id.checkBoxSmoke);
        submit = findViewById(R.id.Submit);
        reset = findViewById(R.id.Reset);
        output = findViewById(R.id.Output);
        output2 = findViewById(R.id.Output2);
        dp.updateDate(2020, 5,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_string = name.getText().toString();
                String phone_string = phone_no.getText().toString();
                String pax_string = pax.getText().toString();
                int year = dp.getYear();
                int month = dp.getMonth();
                int date = dp.getDayOfMonth();
                int hour = tp.getCurrentHour();
                int min = tp.getCurrentMinute();
                if (name_string.equals("") || phone_string.equals("") || pax_string.equals("") || (year <= 2020 && month <= 5 && date == 1) || (hour <= 7 && min <= 30 )) {
                    Toast.makeText(MainActivity.this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Reservation Success",Toast.LENGTH_LONG).show();
                    if(smoking_area.isChecked()){
                        output.setText("Smoking Area seat " + "| Booked by " + name_string + "| Phone:" + phone_string + "| Pax:"+pax_string);
                        output2.setText("Date:" + year + "/"+ (month+1) + "/" + date +"| Time:" + hour + ":" + min);
                    }else{
                        output.setText("Non-smoking Area seat " + " Booked by " + name_string + "| Phone:" + phone_string + "| Pax:"+pax_string);
                        output2.setText("Date:" + year + "/"+ (month+1) + "/" + date +"| Time:" + hour + ":" + min);
                    }
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tp.setCurrentHour(7);
                tp.setCurrentMinute(30);
                dp.updateDate(2020,5,1);
                name.setText("");
                phone_no.setText("");
                pax.setText("");
                smoking_area.setChecked(false);

            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute) {
                if(hourOfDay >=  7 && hourOfDay <= 20 ){
                    tp.setCurrentHour(hourOfDay);
                }else{
                    tp.setCurrentHour(20);
                }

            }
        });



    }
}
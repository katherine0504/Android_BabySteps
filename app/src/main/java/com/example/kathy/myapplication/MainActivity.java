package com.example.kathy.myapplication;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewSwitcher;
import android.app.DialogFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    public int month, date, year;
    public int moodMode = 0;
    private int outdoorMode = 0, sleepMode = 0, poopMode = 0, foodMode = 0, milkMode = 0;
    private String outdoor, sleep, poop, food, milk;
    private TextView txtWakeTime, txtBedTime, setMonth, setDate, txtMilkInfo, txtFoodInfo, txtPoopInfo, txtSleepInfo, txtOutdoorInfo, txtBathInfo;
    private ImageView imgDay, imgNight, imgMilk, imgFood, imgPoop, imgSleep, imgOutdoor, imgBath;
    private ImageSwitcher moodSwitcher;
    //private EditText edtColor, edtShape;
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWakeTime = (TextView) findViewById(R.id.wakeUpTime);
        txtBedTime = (TextView) findViewById(R.id.bedTime);
        setMonth = (TextView)findViewById(R.id.monthView);
        setDate = (TextView)findViewById(R.id.dateView);
        txtMilkInfo = (TextView) findViewById(R.id.milkInfo);
        txtFoodInfo = (TextView) findViewById(R.id.foodInfo);
        txtPoopInfo = (TextView) findViewById(R.id.poopInfo);
        txtSleepInfo = (TextView) findViewById(R.id.sleepInfo);
        txtOutdoorInfo = (TextView) findViewById(R.id.outdoorInfo);
        txtBathInfo = (TextView) findViewById(R.id.bathInfo);

        imgDay = (ImageView) findViewById(R.id.day);
        imgNight = (ImageView) findViewById(R.id.night);
        imgMilk = (ImageView) findViewById(R.id.milk);
        imgFood = (ImageView) findViewById(R.id.food);
        imgPoop = (ImageView) findViewById(R.id.poop);
        imgSleep = (ImageView) findViewById(R.id.sleep);
        imgOutdoor = (ImageView) findViewById(R.id.outdoor);
        imgBath = (ImageView) findViewById(R.id.bath);

        moodSwitcher = (ImageSwitcher) findViewById(R.id.mood);



        imgDay.setOnClickListener(pickDayTime);
        imgNight.setOnClickListener(pickNightTime);
        imgMilk.setOnClickListener(pickMilkTime);
        imgFood.setOnClickListener(pickFoodTime);
        imgPoop.setOnClickListener(pickPoopTime);
        imgSleep.setOnClickListener(pickSleepTime);
        imgOutdoor.setOnClickListener(pickOutdoorTime);
        imgBath.setOnClickListener(pickBathTime);

        moodSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return myView;
            }
        });
        moodSwitcher.setImageResource(R.drawable.mood_happy);

        getCurrentDate();
    }

    public void nextMood (View view)
    {
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        moodSwitcher.setInAnimation(in);
        moodSwitcher.setOutAnimation(out);
        switch (moodMode)
        {
            case 0:
                moodSwitcher.setImageResource(R.drawable.mood_angry);
                moodMode ++; break;
            case 1:
                moodSwitcher.setImageResource(R.drawable.mood_sad);
                moodMode ++; break;
            case 2:
                moodSwitcher.setImageResource(R.drawable.mood_worried);
                moodMode ++; break;
            case 3:
                moodSwitcher.setImageResource(R.drawable.mood_sick);
                moodMode++; break;
            case 4:
                moodSwitcher.setImageResource(R.drawable.mood_happy);
                moodMode=0; break;
            default:
                moodSwitcher.setImageResource(R.drawable.mood_happy);
                moodMode=0; break;
        }

    }

    //get the system date
    public void getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        date = cal.get(Calendar.DATE);

        Log.d("Date Error", String.valueOf(month));

        String monthVoc = transferMonth();

        setMonth.setText(monthVoc);
        setDate.setText(String.valueOf(date));
    }

    // transfers month from numeric to alphabetic
    public String transferMonth ()
    {

        switch (month)
        {
            case 0: return "January";
            case 1: return "February";
            case 2: return "March";
            case 3: return "April";
            case 4: return "May";
            case 5: return "June";
            case 6: return "July";
            case 7: return "August";
            case 8: return "September";
            case 9: return "October";
            case 10: return "November";
            case 11: return "December";
            default: return "error";
        }
    }

    // choose when the baby wakes up in the morning
    public View.OnClickListener pickDayTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtWakeTime.setText(toZeroZero(hourOfDay) + ":" + toZeroZero(minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    };

    // choose when the baby sleeps at night
    public View.OnClickListener pickNightTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtBedTime.setText(toZeroZero(hourOfDay) + ":" + toZeroZero(minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    };

    // choose when the baby drank milk and the amount of milk
    public View.OnClickListener pickMilkTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            if (milkMode==0)
            {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                milk = toZeroZero(hourOfDay) + ":" + toZeroZero(minute) + "  " ;
                                txtMilkInfo.setText(milk);
                                milkMode++;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

            else if (milkMode==1)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.milk_dialog, null))
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dialog d = (Dialog) dialog;
                                EditText edtMilkAmount = (EditText) d.findViewById(R.id.milkAmountInfo);
                                milk = milk + "   " + edtMilkAmount.getText().toString() + " mL";
                                txtMilkInfo.setText(milk);
                                milkMode = 0;
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }
        }
    };

    // choose when the baby ate and the details of the food
    public View.OnClickListener pickFoodTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            if (foodMode==0)
            {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                food = toZeroZero(hourOfDay) + ":" + toZeroZero(minute) + "  " ;
                                txtFoodInfo.setText(food);
                                foodMode++;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

            else if (foodMode==1)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.food_dialog, null))
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dialog d = (Dialog) dialog;
                                EditText edtFood = (EditText) d.findViewById(R.id.foodTypeInfo);
                                EditText edtFoodAmount = (EditText) d.findViewById(R.id.foodAmountInfo);
                                food = food + edtFoodAmount.getText().toString() + " of " + edtFood.getText().toString();
                                txtFoodInfo.setText(food);
                                foodMode = 0;
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }
        }
    };

    // choose when the baby changes diaper and the appearance of the poop
    public View.OnClickListener pickPoopTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            if (poopMode==0)
            {
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                poop = toZeroZero(hourOfDay) + ":" + toZeroZero(minute) + "  " ;
                                txtPoopInfo.setText(poop);
                                poopMode++;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

            else if (poopMode==1)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(inflater.inflate(R.layout.poop_dialog, null))
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dialog d = (Dialog) dialog;
                                EditText edtColor = (EditText) d.findViewById(R.id.colorInfo);
                                EditText edtShape = (EditText) d.findViewById(R.id.shapeInfo);
                                poop = poop + edtColor.getText().toString() + "  " + edtShape.getText().toString();
                                txtPoopInfo.setText(poop);
                                poopMode = 0;
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }
        }
    };

    // choose when the baby naps and wake up time
    public View.OnClickListener pickSleepTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            if (sleepMode==0)
                            {
                                sleep = toZeroZero(hourOfDay) + ":" + toZeroZero(minute) + "  ~  " ;
                                txtSleepInfo.setText(sleep);
                                sleepMode++;
                            }
                            else if (sleepMode==1)
                            {
                                sleep = sleep + toZeroZero(hourOfDay) + ":" + toZeroZero(minute);
                                txtSleepInfo.setText(sleep);
                                sleepMode=0;
                            }
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    };

    // choose when the baby goes outdoors
    public View.OnClickListener pickOutdoorTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            if (outdoorMode==0)
                            {
                                outdoor = toZeroZero(hourOfDay) + ":" + toZeroZero(minute) + "  ~  " ;
                                txtOutdoorInfo.setText(outdoor);
                                outdoorMode++;
                            }
                            else if (outdoorMode==1)
                            {
                                outdoor = outdoor+ toZeroZero(hourOfDay) + ":" + toZeroZero(minute);
                                txtOutdoorInfo.setText(outdoor);
                                outdoorMode=0;
                            }

                            /*else if (outdoorMode==2)
                            {
                                outdoor = outdoor + "\n" + toZeroZero(hourOfDay) + ":" + toZeroZero(minute) + "  ~  " ;
                                txtOutdoorInfo.setText(outdoor);
                                outdoorMode=1;
                            }*/

                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    };

    // choose when the baby takes a bath
    public View.OnClickListener pickBathTime = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtBathInfo.setText(toZeroZero(hourOfDay) + ":" + toZeroZero(minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    };

    // adding zero to the number smaller than 10
    public String toZeroZero (int i) {
        if(i < 10) {
            return "0" + Integer.toString(i);
        }
        else
            return Integer.toString(i);
    }
}

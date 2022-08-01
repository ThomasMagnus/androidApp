package com.example.myapplication;

import Users.*;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import org.postgresql.util.PSQLException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import static java.lang.System.*;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    final Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EditText birthDate = findViewById(R.id.birthday);
//
//        birthDate.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                setDate(birthDate);
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestry");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

//    public void createUser() {
//        EditText firstname = findViewById(R.id.firstnameInput);
//        EditText lastname = findViewById(R.id.lastnameInput);
//        EditText birthday = findViewById(R.id.birthday);
//        EditText email = findViewById(R.id.email);
//        EditText password = findViewById(R.id.password);
//        EditText tryPassword = findViewById(R.id.tryPassword);
//
//        User user = new User(
//                firstname.getText().toString(),
//                lastname.getText().toString(),
//                email.getText().toString(),
//                password.getText().toString(),
//                tryPassword.getText().toString(),
//                new Date(birthday.getText().toString())
//        );
//    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            EditText birthdate = findViewById(R.id.birthday);
            birthdate.setText(String.format("%d.%d.%d", dayOfMonth, (monthOfYear + 1), year));
        }
    };

    public void setDate(View view) {
        new DatePickerDialog(MainActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
        ).show();
    }
    public void openConnection(View view) {
        final String connectionString = "jdbc:postgresql://localhost:5432/AndroidApp";
        final String user = "postgres";
        final String password = "Hofman95";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection(connectionString, user, password);
            Snackbar.make(view, "Соединение открыто", Snackbar.LENGTH_LONG).show();

            connect.createStatement();

            Snackbar.make(view, "Соединение закрыто", Snackbar.LENGTH_LONG).show();

            Intent intent = new Intent(this, UserPage.class);
            intent.putExtra("hello", "Hello World");
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            Log.d(TAG, "Класс не найден");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
package com.schuetz.rust_android_ios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            System.loadLibrary("mobcore");
        } catch (UnsatisfiedLinkError e) {
            Log.e("MainActivity", "Load library error: " + e);
            return;
        }

        MyRustStruct myRustStruct = new MyRustStruct();

        final TextView greetingTextView = findViewById(R.id.greetingLabel);
        greetingTextView.setText(myRustStruct.greet("Ivan"));

        final TextView numberTextView = findViewById(R.id.numberLabel);
        final int res = myRustStruct.add(2);
        numberTextView.setText(getString(R.string.rust_add_text, res));
    }
}
package com.android.credassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MultiSheetView multiSheetView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiSheetView = findViewById(R.id.multi_sheet_view);

    }

    @Override
    public void onBackPressed() {
        if (multiSheetView.getCurrentSheet() == MultiSheetView.Sheet.NONE) {
            super.onBackPressed();
        }else {
            multiSheetView.consumeBackPress();
        }
    }
}
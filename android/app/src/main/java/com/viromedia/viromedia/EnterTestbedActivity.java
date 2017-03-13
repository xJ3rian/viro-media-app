/*
 * Copyright © 2017 Viro Media. All rights reserved.
 */
package com.viromedia.viromedia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EnterTestbedActivity extends AppCompatActivity {
    public final static String EXTRA_IP_ADDRESS = "com.viromedia.IP_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_testbed);
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String debug_http_host = preferences.getString("debug_http_host", "");
        TextView previousEndpointView = (TextView) findViewById(R.id.endpoint_string);
        if (debug_http_host.isEmpty()) {
            previousEndpointView.setVisibility(View.INVISIBLE);
        } else {
            previousEndpointView.setVisibility(View.VISIBLE);
            previousEndpointView.setText(debug_http_host.split(ViroTestBedViroActivity.HOST_PORT)[0]);
        }

        ImageButton back_btn = (ImageButton) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EditText editText = (EditText) findViewById(R.id.edit_message);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(v);
                    startTestBed(v);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void addPreviousEntpoint(View view) {
        TextView prevEndpointView = (TextView) findViewById(R.id.endpoint_string);
        String prevEndpointText = prevEndpointView.getText().toString();
        if (!prevEndpointText.trim().isEmpty()) {

            EditText editText = (EditText) findViewById(R.id.edit_message);
            editText.setText(prevEndpointText);
        }
    }

    public void startTestBed(View view) {
        Intent intent = new Intent(this, ViroTestBedViroActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String ipAddr = editText.getText().toString();
        if (ipAddr != null && !ipAddr.trim().isEmpty()) {

            intent.putExtra(EXTRA_IP_ADDRESS, ipAddr.trim());
            startActivity(intent);
        }
    }
}

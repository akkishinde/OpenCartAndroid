package com.splashinfotech.mahaveer_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


/**
 * Created by Akshay on 3/24/2015.
 */
public class SplashActivity extends Activity {
    // Splash screen timer
    private Handler splashHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        Runnable r = new Runnable() {
            public void run() {
                Intent brain = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(brain);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
        setContentView(R.layout.splash_screen);
        if (isNetworkAvailable())
            splashHandler.postDelayed(r, 3000);
        else {
            //splashHandler.postDelayed(r, 3000);
            //Notify user they aren't connected
            Toast.makeText(getApplicationContext(), "You aren't connected to the internet.", Toast.LENGTH_LONG).show();
            splashHandler.postDelayed(r2, 3000);
            //close the app
            //finish();
        }
    }

    public void onResume(Bundle savedInstanceState) {
        super.onResume();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}

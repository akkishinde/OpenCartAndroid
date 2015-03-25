package com.splashinfotech.mahaveer_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;

/**
 * Created by Akshay on 3/24/2015.
 */
public class LoginActivity extends Activity{

    EditText username,password;


  @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.login_activity);
      username=(EditText)findViewById(R.id.username_text);
      password=(EditText)findViewById(R.id.password_text);


  }
    public void login(View view)
    {   View v;
        Toast toast;
        TextView text;


        String nowEmail = username.getText().toString();
        String nowPass = password.getText().toString();
        if (Validation.isNotNull(nowEmail) && Validation.isNotNull(nowPass)) {
            if (Validation.validate(nowEmail)) {
                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                doLogin();


            } else {
                toast=Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT);
                v = toast.getView();
                text = (TextView) v.findViewById(android.R.id.message);
                text.setTextColor(getResources().getColor(R.color.mWhite));
                text.setShadowLayer(0,0,0,0);
                v.setBackgroundResource(R.color.mTeal);
                //toast.setGravity(Gravity.TOP, 0, 950);
                toast.show();
            }

        } else {

            toast=Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_SHORT);
            v = toast.getView();
            text = (TextView) v.findViewById(android.R.id.message);
            text.setTextColor(getResources().getColor(R.color.mWhite));
            text.setShadowLayer(0,0,0,0);
            v.setBackgroundResource(R.color.mTeal);
            //toast.setGravity(Gravity.TOP, 0, 950);
            toast.show();
        }

    }

    private void doLogin() {

        StringEntity entity = null;
        JSONObject jsonParams = new JSONObject();
        String email=username.getText().toString();
        String pass=password.getText().toString();
        try {
            jsonParams.put("email", email);
            jsonParams.put("password", pass);
            entity = new StringEntity(jsonParams.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Oc-Merchant-Id","123");
        client.addHeader("X-Oc-Merchant-Language","en");
        client.post(getApplicationContext(), "http://webshop.opencart-api.com/api/rest/login", entity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(String response) {
                View v;
                Toast toast;
                TextView text;
                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("success").equals("true")) {
                        JSONObject json= (JSONObject) new JSONTokener(response).nextValue();
                        JSONObject json2 = json.getJSONObject("data");
                        String sessionID=json2.getString("session");
                        String first_name=json2.getString("firstname");
                        String username=json2.getString("email");
                        final Session session = (Session) getApplicationContext();
                        session.setSession_id(sessionID);
                        session.setFirstname(first_name);
                        session.setUsername(username);
                        Toast.makeText(getApplicationContext(), "Welcome "+first_name, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //String error=obj.getString("error");
                        toast=Toast.makeText(getApplicationContext(), "Username & Password Mismatch", Toast.LENGTH_SHORT);
                        v = toast.getView();
                        text = (TextView) v.findViewById(android.R.id.message);
                        text.setTextColor(getResources().getColor(R.color.mWhite));
                        text.setShadowLayer(0,0,0,0);
                        v.setBackgroundResource(R.color.mRed);
                        //toast.setGravity(Gravity.TOP, 0, 950);
                        toast.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                View v;
                Toast toast;
                TextView text;
                toast=Toast.makeText(getApplicationContext(), "Something went wrong at Server Side!", Toast.LENGTH_SHORT);
                v = toast.getView();
                text = (TextView) v.findViewById(android.R.id.message);
                text.setTextColor(getResources().getColor(R.color.mWhite));
                text.setShadowLayer(0,0,0,0);
                v.setBackgroundResource(R.color.mRed);
                //toast.setGravity(Gravity.TOP, 0, 950);
                toast.show();
            }

        });
    }

    public void register(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void skip(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

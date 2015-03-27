package com.splashinfotech.mahaveer_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Splash New on 3/24/2015.
 */
public class RegisterActivity extends Activity{
    EditText firstname,lastname,username,password,repeat_password,contact,address1,address2,city;
    ProgressDialog prgDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        firstname=(EditText)findViewById(R.id.firstname_text);
        lastname=(EditText)findViewById(R.id.lastname_text);
        username=(EditText)findViewById(R.id.username_text);
        password=(EditText)findViewById(R.id.password_text);
        repeat_password=(EditText)findViewById(R.id.repeatpassword_text);
        contact=(EditText)findViewById(R.id.contact_text);
        address1=(EditText)findViewById(R.id.address1_text);
        address2=(EditText)findViewById(R.id.address2_text);
        city=(EditText)findViewById(R.id.city_text);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Please wait...");

    }
    public void register(View view)
    {   Toast toast;
        TextView text;
        View v;
        String fname=firstname.getText().toString();
        String lname=lastname.getText().toString();
        String email=username.getText().toString();
        String pass=password.getText().toString();
        String repass=repeat_password.getText().toString();
        String mobile=contact.getText().toString();
        String add1=address1.getText().toString();
        String add2=address2.getText().toString();

        if (Validation.isNotNull(fname) && Validation.isNotNull(lname)
                && Validation.isNotNull(email)&& Validation.isNotNull(pass)
                && Validation.isNotNull(repass)&& Validation.isNotNull(mobile)
                && Validation.isNotNull(add1)&& Validation.isNotNull(add2)){
            if (Validation.validate(email)) {
                if(pass.equals(repass))
                {
                    doRegister();
                }
                else {
                    toast = Toast.makeText(getApplicationContext(), "Password Missmatch", Toast.LENGTH_SHORT);
                    v = toast.getView();
                    text = (TextView) v.findViewById(android.R.id.message);
                    text.setTextColor(getResources().getColor(R.color.mWhite));
                    text.setShadowLayer(0, 0, 0, 0);
                    v.setBackgroundResource(R.color.mTeal);
                    //toast.setGravity(Gravity.TOP, 0, 950);
                    toast.show();
                }
            }else {
                toast = Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT);
                v = toast.getView();
                text = (TextView) v.findViewById(android.R.id.message);
                text.setTextColor(getResources().getColor(R.color.mWhite));
                text.setShadowLayer(0, 0, 0, 0);
                v.setBackgroundResource(R.color.mTeal);
                //toast.setGravity(Gravity.TOP, 0, 950);
                toast.show();
            }
        }
        else {
            toast = Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_SHORT);
            v = toast.getView();
            text = (TextView) v.findViewById(android.R.id.message);
            text.setTextColor(getResources().getColor(R.color.mWhite));
            text.setShadowLayer(0, 0, 0, 0);
            v.setBackgroundResource(R.color.mTeal);
            //toast.setGravity(Gravity.TOP, 0, 950);
            toast.show();
        }
    }

    private void doRegister() {

        prgDialog.show();
        String fname=firstname.getText().toString();
        String lname=lastname.getText().toString();
        String email=username.getText().toString();
        String pass=password.getText().toString();
        String repass=repeat_password.getText().toString();
        String mobile=contact.getText().toString();
        String add1=address1.getText().toString();
        String add2=address2.getText().toString();
        String citys=city.getText().toString();
        String country="99";
        String postal="431001";
        String state="1493";
        String agree="1";

        StringEntity entity = null;
        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("firstname", fname);
            jsonParams.put("lastname", lname);
            jsonParams.put("email", email);
            jsonParams.put("password", pass);
            jsonParams.put("confirm", repass);
            jsonParams.put("telephone", mobile);
            jsonParams.put("address_1", add1);
            jsonParams.put("address_2", add2);
            jsonParams.put("city", citys);
            jsonParams.put("country_id",country);
            jsonParams.put("postcode",postal);
            jsonParams.put("zone_id", state);
            jsonParams.put("agree", agree);
            entity = new StringEntity(jsonParams.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("X-Oc-Merchant-Id","123");
        client.addHeader("X-Oc-Merchant-Language","en");
        client.post(getApplicationContext(), "http://webshop.opencart-api.com/api/rest/register", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                prgDialog.hide();
                try {
                    View v;
                    Toast toast;
                    TextView text;
                    JSONObject obj = new JSONObject(response);
                    //Toast.makeText(getApplicationContext(), ""+ response, Toast.LENGTH_SHORT).show();
                    if (obj.getString("success").equals("true")) {
                        toast=Toast.makeText(getApplicationContext(), "Registration Successful!"+
                                "\n"+"Please Login to Continue..", Toast.LENGTH_SHORT);
                        v = toast.getView();
                        text = (TextView) v.findViewById(android.R.id.message);
                        text.setTextColor(getResources().getColor(R.color.mWhite));
                        text.setShadowLayer(0,0,0,0);
                        v.setBackgroundResource(R.color.mGreen);
                        //toast.setGravity(Gravity.TOP, 0, 950);
                        toast.show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        toast=Toast.makeText(getApplicationContext(), "E-Mail Address is already registered!", Toast.LENGTH_SHORT);
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
                prgDialog.hide();

            }
        });
    }

}

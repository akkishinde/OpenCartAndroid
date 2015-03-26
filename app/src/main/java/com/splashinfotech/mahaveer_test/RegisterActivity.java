package com.splashinfotech.mahaveer_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Splash New on 3/24/2015.
 */
public class RegisterActivity extends Activity{
    EditText firstname,lastname,username,password,repeat_password,contact,address1,address2,city;
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
        String citys=city.getText().toString();
        String country="India";
        String postal="431001";
        String state="Maharashtra";

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

    }

}

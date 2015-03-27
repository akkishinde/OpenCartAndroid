package com.splashinfotech.mahaveer_test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    static int cat_count;
    ArrayList<String> category = new <String>ArrayList<String>();
    ArrayList<String> category_id = new <String>ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.mOrange)));
        */
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        final Session session = (Session) getApplicationContext();
        Toast.makeText(getApplicationContext(),"ID"+session.getSession_id(),Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }


    public void onSectionAttached(final int number) {
        AsyncHttpClient client=new AsyncHttpClient();
        client.addHeader("X-Oc-Merchant-Id", "123");
        client.addHeader("X-Oc-Merchant-Language", "en");
        client.get("http://webshop.opencart-api.com/api/rest/categories", new AsyncHttpResponseHandler() {
            public static final String TAG = "";

            @Override
            public void onSuccess(String response) {
                // Log.i(TAG,"resp= "+response);
                try {
                    JSONObject resp = new JSONObject(response);
                    if (resp.getString("success").equals("true")) {
                        JSONArray array = resp.getJSONArray("data");
                        cat_count = array.length();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject ArrObj = array.getJSONObject(i);
                            category.add(ArrObj.getString("name"));
                            category_id.add(ArrObj.getString("category_id"));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (number) {
                    case 1:
                        mTitle = category.get(0);
                        break;
                    case 2:
                        mTitle = category.get(1).replaceAll("&amp;","&");
                        break;
                    case 3:
                        mTitle = category.get(2);
                        break;
                    case 4:
                        mTitle = category.get(3);
                        break;
                    case 5:
                        mTitle = category.get(4);
                        break;
                    case 6:
                        mTitle = category.get(5).replaceAll("&amp;","&");
                        break;
                    case 7:
                        mTitle = category.get(6);
                        break;
                    case 8:
                        mTitle = category.get(7);
                        break;

                }

            }
        });

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }


    }
    @Override
    public void onBackPressed() {
        final Session session = (Session) getApplicationContext();
        final String sessID=session.getSession_id();
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_delete).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LoginActivity.client.addHeader("X-Oc-Merchant-Id", "123");
                                LoginActivity.client.addHeader("X-Oc-Merchant-Language", "en");
                                LoginActivity.client.addHeader("X-Oc-Session", sessID);
                                LoginActivity.client.post("http://webshop.opencart-api.com/api/rest/logout", new AsyncHttpResponseHandler() {});
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);

                                finish();
                            }
                        }

                ).

                setNegativeButton("no",null)

                .

                        show();
    }

}
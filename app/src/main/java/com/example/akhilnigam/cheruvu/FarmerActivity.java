package com.example.akhilnigam.cheruvu;

// By Akhil_Xonym 20-04-2018    ----


import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FarmerActivity extends AppCompatActivity {

    private Spinner mandal_Spinner;
    private Spinner village_Spinner;
    TextView text;
    EditText usern;
    EditText pass;
    EditText age;
    Spinner spinner1;
    Spinner spinner2;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private ArrayAdapter<mandal> mandalArrayAdapter;
    private ArrayAdapter<village> villageArrayAdapter;


    private ArrayList<mandal> mandals;
    private ArrayList<village> villages;


    @Override
    protected void onCreate(Bundle savedInstancevillage) {
        super.onCreate(savedInstancevillage);
        setContentView(R.layout.activity_farmer);

        usern = (EditText) findViewById(R.id.usern);
        age = (EditText) findViewById(R.id.fage);

        Button btnRegister = (Button) findViewById(R.id.reg);
        mandal_Spinner = (Spinner) findViewById(R.id.SpinnermandalActivity_mandal_spinner);
        village_Spinner = (Spinner) findViewById(R.id.SpinnermandalActivity_village_spinner);


        mandals = new ArrayList<>();
        villages = new ArrayList<>();


        createLists();

        mandalArrayAdapter = new ArrayAdapter<mandal>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, mandals);
        mandalArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mandal_Spinner.setAdapter(mandalArrayAdapter);

        villageArrayAdapter = new ArrayAdapter<village>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, villages);
        villageArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        village_Spinner.setAdapter(villageArrayAdapter);


        mandal_Spinner.setOnItemSelectedListener(mandal_listener);
        village_Spinner.setOnItemSelectedListener(village_listener);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //  final String username = inputFullName.getText().toString().trim();
                String Age = age.getText().toString().trim();
                String username = usern.getText().toString().trim();
                String mandal  =String.valueOf(mandal_Spinner.getSelectedItem());
                String village  =String.valueOf(village_Spinner.getSelectedItem());
                //  Toast.makeText(FarmerActivity.this, village, Toast.LENGTH_SHORT).show();
                /////////////////



                 //  Toast.makeText(FarmerActivity.this, village, Toast.LENGTH_SHORT).show();


                if (!username.isEmpty() && !Age.isEmpty()) {

                    final String Username, Email, Password;
                    //Executing AsyncTask, passing api as parameter
                    // Toast.makeText(FarmerActivity.this, username, Toast.LENGTH_SHORT).show();
                    new CheckConnectionStatus().execute("http://helpemglocalplatform.000webhostapp.com/cheruvu.php",username,Age,mandal,village);
                }
            }

        });
    }


    private AdapterView.OnItemSelectedListener mandal_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final mandal mandal = (mandal) mandal_Spinner.getItemAtPosition(position);
                Log.d("Spinnermandal", "onItemSelected: mandal: "+mandal.getmandalID());
                ArrayList<village> tempvillages = new ArrayList<>();

                tempvillages.add(new village(0, new mandal(0, "Choose a mandal"), "Choose a village"));
                int count=0;
                for (village singlevillage : villages) {
                    if (((singlevillage.getmandal().getmandalID() ) == ( mandal.getmandalID()) ) ) {
                        {
                            tempvillages.add(singlevillage);

                        }
                    }
                }

                villageArrayAdapter = new ArrayAdapter<village>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, tempvillages);
                villageArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                village_Spinner.setAdapter(villageArrayAdapter);
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener village_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) {
                final village village = (village) village_Spinner.getItemAtPosition(position);
                Log.d("Spinnermandal", "onItemSelected: village: "+village.getvillageID());


                mandal mandal = new mandal(0, "Choose a mandal");
                village firstvillage = new village(0, mandal, "Choose a village");




            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    private void createLists() {
        mandal mandal0 = new mandal(0, "Choose a mandal");
        mandal mandal1 = new mandal(1, "mandal 1");
        mandal mandal2 = new mandal(2, "mandal 2");
        mandal mandal3 = new mandal(3, "mandal 3");

        mandals.add(new mandal(0, "Choose a mandal"));
        mandals.add(new mandal(1, "mandal 1"));
        mandals.add(new mandal(2, "mandal 2"));
        mandals.add(new mandal(3, "mandal 3"));

        village village0 = new village(0, mandal0, "Choose a mandal");
        village village1 = new village(1, mandal1, "village A");
        village village2 = new village(2, mandal1, "village B");
        village village3 = new village(3, mandal1, "village C");
        village village4 = new village(4, mandal2, "village D");
        village village5 = new village(5, mandal2, "village E");
        village village6 = new village(6, mandal2, "village F");
        village village7 = new village(7, mandal3, "village G");
        village village8 = new village(8, mandal3, "village H");
        village village9 = new village(9, mandal3, "village I");



        villages.add(village0);
        villages.add(village1);
        villages.add(village2);
        villages.add(village3);
        villages.add(village4);
        villages.add(village5);
        villages.add(village6);
        villages.add(village7);
        villages.add(village8);
        villages.add(village9);

    }

    private class mandal implements Comparable<mandal> {

        private int mandalID;
        private String mandalName;


        public mandal(int mandalID, String mandalName) {
            this.mandalID = mandalID;
            this.mandalName = mandalName;
        }

        public int getmandalID() {
            return mandalID;
        }

        public String getmandalName() {
            return mandalName;
        }

        @Override
        public String toString() {
            return mandalName;
        }


        @Override
        public int compareTo(mandal another) {
            return this.getmandalID() - another.getmandalID();//ascending order
//            return another.getmandalID()-this.getmandalID();//descending  order
        }
    }

    private class village implements Comparable<village> {

        private int villageID;
        private mandal mandal;
        private String villageName;

        public village(int villageID, mandal mandal, String villageName) {
            this.villageID = villageID;
            this.mandal = mandal;
            this.villageName = villageName;
        }

        public int getvillageID() {
            return villageID;
        }

        public mandal getmandal() {
            return mandal;
        }

        public String getvillageName() {
            return villageName;
        }

        @Override
        public String toString() {
            return villageName;
        }

        @Override
        public int compareTo(village another) {
            return this.getvillageID() - another.getvillageID();//ascending order
//            return another.getvillageID()-this.getvillageID();//descending order
        }
    }


     class CheckConnectionStatus extends AsyncTask<String, Void, String> {
        //This method will run on UIThread and it will execute before doInBackground
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Making text field blank
            //text.setText("");
        }

        //This method will run on background thread and after completion it will return result to onPostExecute
        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            try {
                //As we are passing just one parameter to AsyncTask, so used param[0] to get value at 0th position that is URL
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);

                //Building URI
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("Name", params[1])
                        .appendQueryParameter("Age", params[2])
                        .appendQueryParameter("Mandal", params[3])
                        .appendQueryParameter("Village", params[4]);

                //Getting object of OutputStream from urlConnection to write some data to stream
                OutputStream outputStream = urlConnection.getOutputStream();

                //Writer to write data to OutputStream
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(builder.build().getEncodedQuery());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                urlConnection.connect();

                //Getting inputstream from connection, that is response which we got from server
                InputStream inputStream = urlConnection.getInputStream();

                //Reading the response
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String s = bufferedReader.readLine();
                bufferedReader.close();

                //Returning the response message to onPostExecute method
                return s;
            } catch (IOException e) {
                Log.e("Error: ", e.getMessage(), e);
            }
            return null;
        }

        //This method runs on UIThread and it will execute when doInBackground is completed
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Setting the response message to textview
            if (s.equals("Success")) {
                Toast.makeText(FarmerActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            } else

            {
                Toast.makeText(FarmerActivity.this, s, Toast.LENGTH_SHORT).show();

            }
        }
    }
}

package com.example.android.currencyrates;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int NUM_ITEMS = 32;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    String mResponse;

    ProgressBar pb;

    ArrayList<String> currencyNameList;
    ArrayList<String> valueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.progress_bar);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_currency);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(llm);

        getCurrencyConvertion("USD");



    }

    public void getCurrencyConvertion(String base){

        URL url = NetworkUtils.buildUrl(base);

        new CustomAsyncTask().execute(url);
    }

    public ArrayList<String> getValue(String response, String base) throws JSONException{
        ArrayList<String> answer = new ArrayList<>();
        JSONObject object;
        object = new JSONObject(response);
        JSONObject names = object.getJSONObject("rates");
        String aud = names.getString("AUD");
        String bgn = names.getString("BGN");
        String brl = names.getString("BRL");
        String cad = names.getString("CAD");
        String chf = names.getString("CHF");
        String cny = names.getString("CNY");
        String czk = names.getString("CZK");
        String dkk = names.getString("DKK");
        String eur = "1";
        if(base.equals("USD")) {
            eur = names.getString("EUR");
        }
        String gbp = names.getString("GBP");
        String hkd = names.getString("HKD");
        String hrk = names.getString("HRK");
        String huf = names.getString("HUF");
        String idr = names.getString("IDR");
        String ils = names.getString("ILS");
        String inr = names.getString("INR");
        String isk = names.getString("ISK");
        String jpy = names.getString("JPY");
        String krw = names.getString("KRW");
        String mxn = names.getString("MXN");
        String myr = names.getString("MYR");
        String nok = names.getString("NOK");
        String nzd = names.getString("NZD");
        String php = names.getString("PHP");
        String pln = names.getString("PLN");
        String ron = names.getString("RON");
        String rub = names.getString("RUB");
        String sek = names.getString("SEK");
        String sgd = names.getString("SGD");
        String thb = names.getString("THB");
        String try1 = names.getString("TRY");
        String zar = names.getString("ZAR");

        answer.add(aud);
        answer.add(bgn);
        answer.add(brl);
        answer.add(cad);
        answer.add(chf);
        answer.add(cny);
        answer.add(czk);
        answer.add(dkk);
        answer.add(eur);
        answer.add(gbp);
        answer.add(hkd);
        answer.add(hrk);
        answer.add(huf);
        answer.add(idr);
        answer.add(ils);
        answer.add(inr);
        answer.add(isk);
        answer.add(jpy);
        answer.add(krw);
        answer.add(mxn);
        answer.add(myr);
        answer.add(nok);
        answer.add(nzd);
        answer.add(php);
        answer.add(pln);
        answer.add(ron);
        answer.add(rub);
        answer.add(sek);
        answer.add(sgd);
        answer.add(thb);
        answer.add(try1);
        answer.add(zar);

        return answer;

    }

    public ArrayList<String> getCurrencyName(){
        ArrayList<String> list = new ArrayList<>();

        list.add("AUD");
        list.add("BGN");
        list.add("BRL");
        list.add("CAD");
        list.add("CHF");
        list.add("CNY");
        list.add("CZK");
        list.add("DKK");
        list.add("EUR");
        list.add("GBP");
        list.add("HKD");
        list.add("HRK");
        list.add("HUF");
        list.add("IDR");
        list.add("ILS");
        list.add("INR");
        list.add("ISK");
        list.add("JPY");
        list.add("KRW");
        list.add("MXN");
        list.add("MYR");
        list.add("NOK");
        list.add("NZD");
        list.add("PHP");
        list.add("PLN");
        list.add("RON");
        list.add("RUB");
        list.add("SEK");
        list.add("SGD");
        list.add("THB");
        list.add("TRY");
        list.add("ZAR");
        return list;
    }

    public class CustomAsyncTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mRecyclerView.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.VISIBLE);
        }

        String getBase(String response) throws JSONException {
            JSONObject object = new JSONObject(response);
            String answer = object.getString("base");
            return answer;
        }

        @Override
        protected void onPostExecute(String s) {

            pb.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mResponse = s;

            String base = "USD";
            try {
                base = getBase(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            currencyNameList = getCurrencyName();
            valueList = null;
            try {
                valueList = getValue(s,base);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mAdapter = new CustomAdapter(NUM_ITEMS,currencyNameList,valueList);

            mRecyclerView.setAdapter(mAdapter);

        }

        @Override
        protected String doInBackground(URL... params) {
            URL url = params[0];
            String httpResponse = null;
            try{
                httpResponse = NetworkUtils.getResponseFromHttpUrl(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return httpResponse;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.first_menu_item){
            getCurrencyConvertion("USD");
        }else if(item.getItemId() == R.id.second_menu_item){
            getCurrencyConvertion("");
        }
        return super.onOptionsItemSelected(item);
    }
}

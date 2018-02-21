package com.example.android.currencyrates;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(llm);

        getCurrencyConvertion();

    }

    public void getCurrencyConvertion(){

        URL url = NetworkUtils.buildUrl();
        try {
            mResponse = NetworkUtils.getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new CustomAsyncTask().execute(url);
    }

    public ArrayList<String> getValue(String response) throws JSONException {
        ArrayList<String> answer = null;
        JSONObject object = null;
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
        String eur = names.getString("EUR");
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

    public ArrayList<String> getCurrencyName(String str){
        ArrayList<String> list = null;

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
        protected void onPostExecute(String s) {

            mResponse = s;

            ArrayList<String> currencyNameList = getCurrencyName(s);
            ArrayList<String> valueList = null;
            try {
                valueList = getValue(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mRecyclerView = (RecyclerView) findViewById(R.id.rv_currency);

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

}

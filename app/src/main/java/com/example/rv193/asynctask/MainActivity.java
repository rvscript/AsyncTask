package com.example.rv193.asynctask;
//how to create threads that don't get destroyed upon orientation change
/*
A headless fragment doesnt have UI its just an object thaT extends fragment but
does not create.
AsyncTask Loaders allow AsyncTask to work upon screen orientation change

An EventBus works well. It can communicate between any two parts of my code;

Eventbus is an external library that can be accessed by setting up gradle
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView2;
    TextView textView3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
    }
    public void onStart(View view) {
        MyTask mT = new MyTask();
       // mT.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 5);
        mT.execute(5);
    }

    private class MyTask extends AsyncTask<Integer, Integer, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            textView2.setText("started");
        }

        protected String doInBackground(Integer... params) {
            int tTs = params[0] * 100;

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(tTs);
                    publishProgress((i + 1) * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Done";
        }

        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int percentage = values[0];

            textView3.setText(percentage + "%");
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView3.setText(s);

            Log.d("MAC_Tag", "Done with ASyncTask");
        }
    }


}

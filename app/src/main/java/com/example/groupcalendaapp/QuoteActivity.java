package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class QuoteActivity extends AppCompatActivity {
    ArrayList<String> listQuotes = new ArrayList<String>();
    int quoteNumber;
    String mainText;
    TextView quoteTV;
    FloatingActionButton quoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        listQuotes.add(String.valueOf(R.string.quote1));
        listQuotes.add(String.valueOf(R.string.quote2));
        listQuotes.add(String.valueOf(R.string.quote3));
        listQuotes.add(String.valueOf(R.string.quote4));
        listQuotes.add(String.valueOf(R.string.quote5));
        listQuotes.add(String.valueOf(R.string.quote6));
        listQuotes.add(String.valueOf(R.string.quote7));
        listQuotes.add(String.valueOf(R.string.quote8));
        listQuotes.add(String.valueOf(R.string.quote9));
        listQuotes.add(String.valueOf(R.string.quote10));

        quoteNumber=0;

        //quoteOnAppLoaded();
        //clickNewQuote();

        quoteTV = findViewById(R.id.quoteTV);
        quoteButton = findViewById(R.id.fabNewQuote);

        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printQuote();
            }
        });



    }

    public void printQuote(){
        Random quoteNumber = new Random();
        String quoteString = listQuotes.get(quoteNumber.nextInt() % listQuotes.size());
        quoteTV.setText(quoteString);

    }
}
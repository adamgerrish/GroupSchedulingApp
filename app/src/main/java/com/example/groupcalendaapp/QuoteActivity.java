package com.example.groupcalendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class QuoteActivity extends AppCompatActivity {

    ArrayList<String> listQuotes;
    int quoteNumber;
    String mainText;
    TextView quoteTV;
    FloatingActionButton quoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        listQuotes = quotes();


        quoteTV = findViewById(R.id.quoteTV);
        quoteButton = findViewById(R.id.fabNewQuote);

        quoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printQuote();
            }
        });



    }

    public ArrayList<String> quotes(){
        ArrayList<String> listQuotes = new ArrayList<String>();
        listQuotes.add("Do not fear failure but rather fear not trying.");
        listQuotes.add("Without ambition one starts nothing. Without work one finishes nothing. The prize will not be sent to you. You have to win it.");
        listQuotes.add("Action may not always bring happiness, but there is no happiness without action.");
        listQuotes.add("“Don't fear failure. — Not failure, but low aim, is the crime. In great attempts it is glorious even to fail.");
        listQuotes.add("First say to yourself what you would be; and then do what you have to do.");
        listQuotes.add("Everything is hard before it is easy");
        listQuotes.add("Make today worth remembering.");


        return listQuotes;
    }

    public void printQuote(){
        Random quoteNumber = new Random();
        String quoteString = listQuotes.get(quoteNumber.nextInt() % listQuotes.size()).toString();
        quoteTV.setText(listQuotes.get(2));
    }
}
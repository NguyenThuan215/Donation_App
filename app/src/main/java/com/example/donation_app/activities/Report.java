package com.example.donation_app.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.donation_app.R;
import com.example.donation_app.models.Donation;

import java.util.List;


class DonationAdapter extends ArrayAdapter<Donation>
{
    private Context context;
    public List<Donation> donations;
    public DonationAdapter(Context context, List<Donation> donations)
    {
        super(context, R.layout.row_donate, donations);
        this.context = context;
        this.donations = donations;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_donate, parent, false);
        Donation donation = donations.get(position);
        TextView amountView = (TextView) view.findViewById(R.id.row_amount);
        TextView methodView = (TextView) view.findViewById(R.id.row_method);
        amountView.setText("$" + donation.amount);
        methodView.setText(donation.paymenttype);
        return view;
    }
    @Override
    public int getCount()
    {
        return donations.size();
    }
}

public class Report extends Base
{
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        listView = (ListView) findViewById(R.id.reportList);
        DonationAdapter adapter = new DonationAdapter(this, app.donations);
        listView.setAdapter(adapter);
    }

    public void reset(MenuItem item) {
        app.donations.clear();
        app.totalDonated = 0;
        donate(item);
//        amountTotal.setText("$" + app.totalDonated);
    }
}

package edu.cudenver.salimlakhani.phonebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.cudenver.salimlakhani.phonebook.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ArrayList<Contact> list;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefs = getSharedPreferences("phonebook", Context.MODE_PRIVATE);
        editor = prefs.edit();

        setSupportActionBar(binding.toolbar);

        list = new ArrayList<Contact>();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddContactDialog addContactDialog = new AddContactDialog();
                addContactDialog.show(getSupportFragmentManager(), "");
            }
        });

        String type = prefs.getString ("type", "name");

        recyclerView = findViewById(R.id.recyclerView);
        contactAdapter = new ContactAdapter(this, list);
        contactAdapter.setType(type);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(contactAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            AddContactDialog addContactDialog = new AddContactDialog();
            addContactDialog.show(getSupportFragmentManager(), "");
            return true;
        }
        else if (id == R.id.action_name) {
            contactAdapter.setType("name");
            editor.putString("type", "name");
            editor.commit();
            contactAdapter.notifyDataSetChanged();
            return true;

        }
        else if (id == R.id.action_phone) {
            contactAdapter.setType("phone");
            editor.putString("type", "phone");
            editor.commit();
            contactAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addContact (Contact contact) {
        list.add(contact);
        Log.i ("info", "Number of Contacts " + list.size());
        contactAdapter.notifyDataSetChanged();
    }

    public void showContact (int contactToShow) {
        //Create object for ViewContact
    }
}
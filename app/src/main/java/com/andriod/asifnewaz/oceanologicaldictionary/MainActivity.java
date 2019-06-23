package com.andriod.asifnewaz.oceanologicaldictionary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static ArrayList<Model> data;
    DatabaseHelper db ;
    ArrayList<String> wordcombimelist;
    ArrayList<String> meancombimelist;
    ArrayList<Integer> isBookmarkedlist;
    ArrayList<Long> id;
    LinkedHashMap<String,String> namelist;
    SearchView searchView;
    Context context;
    SQLiteDatabase sd;
    DatabaseOperations databaseOperations;
    ArrayList<Model>list;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        databaseOperations = new DatabaseOperations(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = new ArrayList<>();
        meancombimelist = new ArrayList<>();
        wordcombimelist = new ArrayList<>();
        isBookmarkedlist = new ArrayList<>();
        id = new ArrayList<>();

        list.clear();
        list = databaseOperations.getAllDataFromDictionary1(this);
        customAdapter = new CustomAdapter(list,this);
        customAdapter.notifyDataSetChanged();

        fetchDataList();

        // Data
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(customAdapter);

        //db= new DatabaseHelper(this);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search Here");
        searchView.setQueryRefinementEnabled(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        data = new ArrayList<Model>();
//        fetchData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return  false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                final ArrayList<Model> filteredList = new ArrayList<Model>();

                for (int i = 0; i < wordcombimelist.size(); i++) {
                    final String text = wordcombimelist.get(i).toLowerCase();
                    if (text.contains(newText)) {
                        filteredList.add(new Model(wordcombimelist.get(i),meancombimelist.get(i),isBookmarkedlist.get(i),id.get(i)));
                    }
                }
                adapter = new CustomAdapter(filteredList, context);
                recyclerView.setAdapter(adapter);

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
        // Handle the camera action
        // Perform action on click
            Intent activityChangeIntent = new Intent(MainActivity.this, BookmarksActivity.class);
            startActivity(activityChangeIntent);

        } else if (id == R.id.nav_dev) {
            Intent activityChangeIntent = new Intent(MainActivity.this, BookmarksActivity.class);
            startActivity(activityChangeIntent);

        } else if (id == R.id.nav_otherApp) {
            Intent activityChangeIntent = new Intent(MainActivity.this, BookmarksActivity.class);
            startActivity(activityChangeIntent);

        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void fetchData()
//    {
//        db =new DatabaseHelper(this);
//        try {
//            db.createDataBase();
//            db.openDataBase();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        namelist=new LinkedHashMap<>();
//        int ii;
//        sd = db.getReadableDatabase();
//        Cursor cursor = sd.query("dictionarydata" ,null, null, null, null, null, null);
//        ii=cursor.getColumnIndex("word");
//        wordcombimelist=new ArrayList<String>();
//        meancombimelist= new ArrayList<String>();
//        while (cursor.moveToNext()){
//            namelist.put(cursor.getString(ii), cursor.getString(cursor.getColumnIndex("definition")));
//        }
//        Iterator entries = namelist.entrySet().iterator();
//        while (entries.hasNext()) {
//            Map.Entry thisEntry = (Map.Entry) entries.next();
//            wordcombimelist.add(String.valueOf(thisEntry.getKey()));
//            meancombimelist.add(String.valueOf(thisEntry.getValue()));
//        }
//
//        for (int i = 0; i < wordcombimelist.size(); i++) {
//            data.add(new Model(wordcombimelist.get(i), meancombimelist.get(i)));
//        }
//        adapter = new CustomAdapter(data,this);
//        recyclerView.setAdapter(adapter);
//    }

    public void fetchDataList()
    {
        wordcombimelist.clear();
        meancombimelist.clear();
        isBookmarkedlist.clear();
        id.clear();

        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).word;
            wordcombimelist.add(text) ;
        }
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).definition;
            meancombimelist.add(text) ;
        }

        for (int i = 0; i < list.size(); i++) {
            int text = list.get(i).isBookmarked;
            isBookmarkedlist.add(text) ;
        }

        for (int i = 0; i < list.size(); i++) {
            long text = list.get(i).id;
            id.add(text) ;
        }
    }

}

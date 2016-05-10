package ca.windmobile.mytestingprojects.mytestingproject;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActivityStarting extends AppCompatActivity {

    private List<Node> nodes = new ArrayList<Node>();
    private ListView lstCustomers;
    private TextView txtStatus ;

    public String getCurrentDate() {
        if(currentDate == null)
            currentDate = String.valueOf(Calendar.getInstance().getTimeInMillis());
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    private String currentDate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState!=null ){
            setCurrentDate(savedInstanceState.getString("currentDate"));
            LogHelper.logCallBack(this,"onCreate((state restore))::" + savedInstanceState.getString("currentDate"));
            //initiate nodes variable array with values
            if(savedInstanceState.getSerializable("nodes")!=null) {
                nodes = (List<Node>) savedInstanceState.getSerializable("nodes");
            }
        }else{
            //initiate nodes variable array with values
            for (int i = 0; i < 30; i++) {
                nodes.add(new Node(String.valueOf(i),"Value " + i, (new Date()).toString()));
            }
        }

        LogHelper.logCallBack(this,"onCreate::" + getCurrentDate());
        setContentView(R.layout.activity_starting);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        lstCustomers = (ListView) findViewById(R.id.lstNodes);


        //add click event on items of the list
        lstCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Node editedNode = nodes.get(position);
                Intent intEdit = new Intent(view.getContext(),EditNodeActivity.class);
                intEdit.putExtra("edit",editedNode);
                intEdit.putExtra("edit-id",position);
                startActivityForResult(intEdit,25);
            }
        });
        refreshList();

        registerForContextMenu(lstCustomers);  //this is just register , tell android that this view need to have context menu but the actual implementation of the menu is in onCreateContextMenu

        txtStatus.setText(getCurrentDate() + " Created");
    }

    private void refreshList() {
        List<String> values = new ArrayList<String>();

        for (Node nd : nodes){
            values.add(nd.getNodeName());
        }

        ListAdapter listAdabtor =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,values);
        lstCustomers.setAdapter(listAdabtor);
    }

//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        Context context = getApplicationContext();
//        CharSequence text = "Menu Opened!";
//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//
//        return super.onMenuOpened(featureId, menu);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        menu.findItem(R.id.create_new).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Context context = getApplicationContext();
                Intent createNodeIntent = new Intent( context , EditNodeActivity.class);
                startActivityForResult(createNodeIntent,23); //22 is any value

//                Context context = getApplicationContext();
//                CharSequence text = "Create!";
//                int duration = Toast.LENGTH_LONG;
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
                return false;
            }
        });

        menu.findItem(R.id.mnuFragments).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Context context = getApplicationContext();
                Intent intent = new Intent( context , FragmentTest.class);
                startActivity(intent);
                return false;
            }
        });
        return true;
    }

    @Override
    //this method runs as soon as user long click and before show the context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //Here you can check the source of that context menu by checking v.id and accordingly show different context menu
        MenuInflater mni=new MenuInflater(this);
        mni.inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.cntMnuDelete){
            AdapterView.AdapterContextMenuInfo adp = (AdapterView.AdapterContextMenuInfo)  item.getMenuInfo();
            nodes.remove(adp.position);
            refreshList();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode!=RESULT_CANCELED &&   data!=null){
        Serializable dt =  data.getSerializableExtra("node")  ;
        int index =  data.getIntExtra("node-id",-1)  ;

        int anything = 10;

        if(dt !=null) {
            Node nd = (Node) dt;
            if(index == -1 ) //new
            {
                nodes.add(nd);

                refreshList();
            }else
            {
                //this is edit
                nodes.set(index,nd);
                refreshList();
            }
        }
    }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        CharSequence text = item.getTitle();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return super.onOptionsItemSelected(item);
    }


    //Logging
    @Override
    protected void onRestart() {
        LogHelper.logCallBack(this,"onRestart::" + getCurrentDate());
        txtStatus.setText(getCurrentDate() + " onRestart");
        super.onRestart();
    }
    @Override
    protected void onStart() {
        LogHelper.logCallBack(this,"onStart::" + getCurrentDate());
        txtStatus.setText(getCurrentDate() + " onStart");
        super.onStart();
    }
    @Override
    protected void onResume() {
        LogHelper.logCallBack(this,"onResume::" + getCurrentDate());
        txtStatus.setText(getCurrentDate() + " onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogHelper.logCallBack(this,"onPause::" + getCurrentDate() );
        txtStatus.setText(getCurrentDate() + " onPause");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        LogHelper.logCallBack(this,"onSaveInstanceState + outPersistentState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogHelper.logCallBack(this,"onSaveInstanceState");
        outState.putString("currentDate",currentDate);
        outState.putSerializable("nodes",(Serializable)nodes);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        LogHelper.logCallBack(this,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogHelper.logCallBack(this,"onDestroy");
        super.onDestroy();
    }
}

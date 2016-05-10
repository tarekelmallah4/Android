package ca.windmobile.mytestingprojects.mytestingproject;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditNodeActivity extends AppCompatActivity {

    private int indexData = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.logCallBack(this,"onCreate");
        setContentView(R.layout.activity_edit_node);

        final Button btnSave =(Button) findViewById(R.id.btnSave);
        final EditText txtName =(EditText) findViewById(R.id.txtName);
        final EditText txtDate =(EditText) findViewById(R.id.txtDate);
        final DateFormat dateFor=new SimpleDateFormat("dd MM,yyyy HH:mm:ss");

        assert txtDate != null;
        assert btnSave != null;

        //default value

        Intent currentItent = getIntent();
        Serializable sDta = currentItent.getSerializableExtra("edit");
        indexData = currentItent.getIntExtra("edit-id",0);
        if(sDta!=null){
            Node nDta= (Node)sDta;
            txtName.setText(nDta.getNodeName());
            txtDate.setText(nDta.getNodeDate());
        }
        else{
            txtDate.setText(dateFor.format(Calendar.getInstance().getTime()));
        }
        //end default data


        //events

        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Node nd=new Node("",txtName.getText().toString(),txtDate.getText().toString());

                        Intent intReturn =new Intent();
                        intReturn.putExtra("node",nd);
                        intReturn.putExtra("node-id",indexData);
                        setResult(RESULT_OK,intReturn);

                        finish();
                    }
                }
        );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mni = getMenuInflater();
        mni.inflate(R.menu.edit_node_menu ,menu);

        return super.onCreateOptionsMenu(menu);

    }



    @Override
    protected void onRestart() {
        LogHelper.logCallBack(this,"onRestart");
        super.onRestart();
    }
    @Override
    protected void onStart() {
        LogHelper.logCallBack(this,"onStart");
        super.onStart();
    }
    @Override
    protected void onResume() {
        LogHelper.logCallBack(this,"onResume");
        super.onResume();
    }



    @Override
    protected void onPause() {
        LogHelper.logCallBack(this,"onPause");
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

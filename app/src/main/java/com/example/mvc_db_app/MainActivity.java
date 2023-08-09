package com.example.mvc_db_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        db=new CDB(this);
    }
    public void onSave(View v){
        CDept rec=new CDept();
        rec.dname=e1.getText().toString();
        rec.dloc=e2.getText().toString();
        db.addDept(rec);
        e1.setText("");
        e2.setText("");
    }
    public void onList(View view){
        List<CDept> rec =db.getAllvalues();
        String str="";
        for(CDept cr:rec){
            String log="DId"+cr.id+",Dname:"+cr.dname+",DLOC:"+cr.dloc;
            log=log+"\n";
            str=str+log;
        }
        TextView t=(TextView) findViewById(R.id.tv);
        t.setText(str);
    }
//    public void onFind(View v){
//        String a[];
//        try {
//    }
}
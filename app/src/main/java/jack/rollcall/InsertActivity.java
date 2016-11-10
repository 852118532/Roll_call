package jack.rollcall;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends Activity {


    private List<String> list = new ArrayList<String>();  //定义一个ArrayList
    private ArrayAdapter<String> adapter;

    private Button B_back;
    private Button B_save;
    private Spinner ClassSpinner;
    protected String classname="";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_insert);
        B_back=(Button)findViewById(R.id.button_back);
        B_save=(Button)findViewById(R.id.button_save);
        final EditText editText1=(EditText)findViewById(R.id.editText);
        final EditText editText2=(EditText)findViewById(R.id.editText2);

        B_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("rollcall.db",MODE_PRIVATE,null);
                db.execSQL("create table if not exists stu(_id integer primary key autoincrement,sno  text  not null,sname text not null," +
                        "class text not null,check1 text,check2 text,check3 text,check4 text,check5 text)");

                //Student student=new Student();

                String String1=editText1.getText().toString();
                String String2=editText2.getText().toString();
                String String3=classname;
                String String4="未点名";
                if(classname.equals("")){
                    Toast.makeText(InsertActivity.this,"请选择班级",Toast.LENGTH_SHORT).show();
                }else if(editText1.getText().toString().equals("")){
                    Toast.makeText(InsertActivity.this,"请输入学号",Toast.LENGTH_SHORT).show();
                }else if(editText2.getText().toString().equals("")){
                    Toast.makeText(InsertActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
                }else{
                    ContentValues values=new ContentValues();
                    values.put("sno",String1);
                    values.put("sname",String2);
                    values.put("class",String3);
                    values.put("check1",String4);
                    values.put("check2",String4);
                    values.put("check3",String4);
                    values.put("check4",String4);
                    values.put("check5",String4);
                    //Toast.makeText(InsertActivity.this,"插入成功！",Toast.LENGTH_SHORT).show();
                    try{
                        db.insert("stu",null,values);
                        Toast.makeText(InsertActivity.this,"插入成功！",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(InsertActivity.this,"插入失败！",Toast.LENGTH_SHORT).show();
                    }
                    values.clear();
                }


            }
        });

        B_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(InsertActivity.this, MainActivity.class);
                InsertActivity.this.startActivity(intent);
            }
        });
        //-----------------------------------
        list.add("计科1401");
        list.add("计科1402");
        list.add("计科1403");
        list.add("计科1404");
        ClassSpinner=(Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ClassSpinner.setAdapter(adapter);
        ClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                classname=ClassSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                classname="";
            }
        });

        //------------------------------------
    }
}


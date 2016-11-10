package jack.rollcall;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listsa = new ArrayList<String>();  //定义一个ArrayList
    private List<String> listwe = new ArrayList<String>();  //定义一个ArrayList
    private List<String> listse = new ArrayList<String>();  //定义一个ArrayList
    private ArrayAdapter<String> adapter_sa;
    private ArrayAdapter<String> adapter_we;
    private ArrayAdapter<String> adapter_se;

    private Button B_add;
    private Button B_del;
    private Button B_sele;
    private Button B;
    private int i; //判断是第几周
    private String name; //用与保存学生名字
    private String A; //用于保存出勤状态

    private Spinner SaSpinner;
    private Spinner WeekSpinner;
    private Spinner SeleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        B_add=(Button)findViewById(R.id.button_add);
        B_del=(Button)findViewById(R.id.button_delet);
        B_sele=(Button)findViewById(R.id.button_select);
        B=(Button)findViewById(R.id.button);
        SaSpinner=(Spinner)findViewById(R.id.spinner_sa);
        WeekSpinner=(Spinner)findViewById(R.id.spinner_week);
        SeleSpinner=(Spinner)findViewById(R.id.spinner_select);
        final SQLiteDatabase db=openOrCreateDatabase("rollcall.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists stu(_id integer primary key autoincrement,sno  text  not null,sname text not null," +
                "class text not null,check1 text,check2 text,check3 text,check4 text,check5 text)");

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.equals("")){
                    Toast.makeText(MainActivity.this,"请选择学生！",Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        final SQLiteDatabase db=openOrCreateDatabase("rollcall.db",MODE_PRIVATE,null);
                        if (i == 1) {
                            /*db.execSQL("UPDATE stu SET check1=" + A + " WHERE sname=" + name + "");*/
                            ContentValues values = new ContentValues();
                            values.put("check1", A);//key为字段名，value为值
                            db.update("stu", values, "sname=?", new String[]{name});
                            Toast.makeText(MainActivity.this, "点名成功！", Toast.LENGTH_SHORT).show();
                        }else if (i == 2) {
                            db.execSQL("UPDATE stu SET check2=" + A + " WHERE sname=" + name + "");
                            Toast.makeText(MainActivity.this, "点名成功！", Toast.LENGTH_SHORT).show();
                        }else if (i == 3) {
                            db.execSQL("UPDATE stu SET check3=" + A + " WHERE sname=" + name + "");
                            Toast.makeText(MainActivity.this, "点名成功！", Toast.LENGTH_SHORT).show();
                        }else if (i == 4) {
                            db.execSQL("UPDATE stu SET check4=" + A + " WHERE sname=" + name + "");
                            Toast.makeText(MainActivity.this, "点名成功！", Toast.LENGTH_SHORT).show();
                        }else if(i==5){
                            db.execSQL("UPDATE stu SET check5=" + A + " WHERE sname=" + name + "");
                            Toast.makeText(MainActivity.this, "点名成功！", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "失败！", Toast.LENGTH_SHORT).show();
                        System.out.println(e.toString());
                    }
                }
            }
        });
        B_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, InsertActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        B_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DeleteActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        B_sele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SelectActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        //-------------adpter_sa的处理方法---------------------
        listsa.add("到课");
        listsa.add("缺勤");
        listsa.add("请假");
        listsa.add("迟到");
        SaSpinner=(Spinner)findViewById(R.id.spinner_sa);
        adapter_sa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listsa);
        adapter_sa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SaSpinner.setAdapter(adapter_sa);
        SaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                A=SaSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //-----------------------------------------------------


        //-------------adpter_week的处理方法---------------------
        listwe.add("一");
        listwe.add("二");
        listwe.add("三");
        listwe.add("四");
        listwe.add("五");
        SaSpinner=(Spinner)findViewById(R.id.spinner_sa);
        adapter_we = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listwe);
        adapter_we.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        WeekSpinner.setAdapter(adapter_we);
        WeekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(WeekSpinner.getSelectedItem().toString().equals("一")){
                    i=1;
                }else if(WeekSpinner.getSelectedItem().toString().equals("二")){
                    i=2;
                }else if(WeekSpinner.getSelectedItem().toString().equals("三")){
                    i=3;
                }else if(WeekSpinner.getSelectedItem().toString().equals("四")){
                    i=4;
                }else if(WeekSpinner.getSelectedItem().toString().equals("五")){
                    i=5;
                }else{
                    i=6;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //-----------------------------------------------------

        //------------------------------------------------------
        Cursor c=db.rawQuery("select sname from stu",null);
        while (c.moveToNext()){
            int nameindex=c.getColumnIndex( "sname");
            if(c.getString(nameindex).equals("")==false){
                listse.add(c.getString(nameindex));
            }
        }
        adapter_se = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listse);
        adapter_se.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SeleSpinner.setAdapter(adapter_se);
        SeleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                name=SeleSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                name="";
            }
        });
        //------------------------------------------------------
    }
}

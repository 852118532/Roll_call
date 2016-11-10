package jack.rollcall;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelectActivity extends Activity {

    private Button B_back;
    private Button B_select;
    private TextView Text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select);
        B_back=(Button)findViewById(R.id.button_back);
        B_select=(Button)findViewById(R.id.button_select);
        Text=(TextView)findViewById(R.id.textView2);
        final EditText snum=(EditText)findViewById(R.id.editText3);

        B_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SelectActivity.this, MainActivity.class);
                SelectActivity.this.startActivity(intent);
            }
        });
        B_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("rollcall.db",MODE_PRIVATE,null);
                db.execSQL("create table if not exists stu(_id integer primary key autoincrement,sno  text  not null,sname text not null," +
                        "class text not null,check1 text,check2 text,check3 text,check4 text,check5 text)");
                String result="";
                if (snum.getText().toString().equals("")){
                    Text.setText("查询失败！请输入学号！");
                }else{
                    try{
                        Cursor c=db.rawQuery("select * from stu where sno="+snum.getText().toString()+" ",null);
                        while(c.moveToNext()){
                            result=c.getString(c.getColumnIndex("sno"))+"     "+c.getString(c.getColumnIndex("sname"))+"     " +c.getString(c.getColumnIndex("class"))+"        第一次考勤："+c.getString(c.getColumnIndex("check1"))+"        第二次考勤："+c.getString(c.getColumnIndex("check1"))+"        第三次考勤："+c.getString(c.getColumnIndex("check1"))+"        第四次考勤："+c.getString(c.getColumnIndex("check1"))+"        第五次考勤："+c.getString(c.getColumnIndex("check1"));
                            Text.setText(result);
                            Text.setTextSize(20);
                        }
                    }catch (Exception e){
                        Text.setText("未知原因，查询失败！");
                    }
                }
            }
        });
    }
}

package jack.rollcall;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DeleteActivity extends Activity {

    private Button B_back;
    private Button B_Dele;
    private TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_delete);
        B_back=(Button)findViewById(R.id.button_back);
        B_Dele=(Button)findViewById(R.id.button_delete);
        final EditText snum=(EditText)findViewById(R.id.editText4);
        Text=(TextView)findViewById(R.id.textView_message);

        B_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(DeleteActivity.this, MainActivity.class);
                DeleteActivity.this.startActivity(intent);
            }
        });
        B_Dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("rollcall.db",MODE_PRIVATE,null);
                db.execSQL("create table if not exists stu(_id integer primary key autoincrement,sno  text  not null,sname text not null," +
                        "class text not null,check1 text,check2 text,check3 text,check4 text,check5 text)");
                if(snum.getText().toString().equals("")){
                    Text.setText("删除失败！请输入学号！");
                }else{
                    try{
                        db.execSQL("delete * from stu where sno="+snum.getText().toString()+"");
                        Text.setText("删除成功！");
                    }catch (Exception e){
                        Text.setText("不明原因删除失败！");
                    }
                }
            }
        });
    }
}

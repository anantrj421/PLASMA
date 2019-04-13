package com.datasecure.jarvis.plasma;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView t1, t2;
    private EditText e1;
    private Button btn;
DatabaseReference dbreff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        t1=(TextView) findViewById(R.id.b1);
        t2=(TextView) findViewById(R.id.b2);
        e1=(EditText) findViewById(R.id.e1);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var=e1.getText().toString();
                int i=Integer.valueOf(var);
                if(i>5){
                    String msg = "Wrong Key";
                    t1.setText(msg);
                    t2.setText(msg);
                }
                else {
                    dbreff = FirebaseDatabase.getInstance().getReference().child(var);
                    dbreff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String b1 = dataSnapshot.child("Book1").getValue().toString();
                            String b2 = dataSnapshot.child("Book2").getValue().toString();
                            t1.setText(b1);
                            t2.setText(b2);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
    }
}


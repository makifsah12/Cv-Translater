package com.example.cv_translater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IsPage extends AppCompatActivity {

    EditText editSirket1 , editBaslangic1 , editBitis1 ,
            editSirket2 , editBaslangic2 , editBitis2 ,
            editSirket3 , editBaslangic3 , editBitis3 ,
            editSirket4 , editBaslangic4 , editBitis4 ,
            editSirket5 , editBaslangic5 , editBitis5 ,
            editSirket6 , editBaslangic6 , editBitis6 ,
            pozisyontext1 , pozisyontext2 , pozisyontext3 , pozisyontext4 , pozisyontext5 , pozisyontext6;

    Spinner pozisyon1 , pozisyon2 , pozisyon3 ,pozisyon4 , pozisyon5 , pozisyon6;

    Button btnKaydet2 , btnEkstraSirket;

    RelativeLayout extraLayout1, extraLayout2 , extraLayout3;

    private FirebaseAuth auth2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_page);

        Toast.makeText(getApplicationContext(),"Please Don't Use Turkish Characters",Toast.LENGTH_SHORT).show();

        /////////////////////////////////////////////////////

        btnEkstraSirket = findViewById(R.id.btnEkstraSirket);
        extraLayout1 = findViewById(R.id.extraLayout1);
        extraLayout2 = findViewById(R.id.extraLayout2);
        extraLayout3 = findViewById(R.id.extraLayout3);
        /////////////////////////////////////////////////////

        editSirket1 = findViewById(R.id.editSirket1);
        editBaslangic1 = findViewById(R.id.editBaslangic1);
        editBitis1 = findViewById(R.id.editBitis1);
        editSirket2 = findViewById(R.id.editSirket2);
        editBaslangic2 = findViewById(R.id.editBaslangic2);
        editBitis2 = findViewById(R.id.editBitis2);
        editSirket3 = findViewById(R.id.editSirket3);
        editBaslangic3 = findViewById(R.id.editBaslangic3);
        editBitis3 = findViewById(R.id.editBitis3);
        pozisyontext1 = findViewById(R.id.pozisyontext1);
        pozisyontext2 = findViewById(R.id.pozisyontext2);
        pozisyontext3 = findViewById(R.id.pozisyontext3);
        pozisyontext4 = findViewById(R.id.pozisyontext4);
        pozisyontext5 = findViewById(R.id.pozisyontext5);
        pozisyontext6 = findViewById(R.id.pozisyontext6);

        //////////////////////////////////////////////////////

        pozisyon1 = findViewById(R.id.pozisyon1);
        pozisyon2 = findViewById(R.id.pozisyon2);
        pozisyon3 = findViewById(R.id.pozisyon3);
        pozisyon4 = findViewById(R.id.pozisyon4);
        pozisyon5 = findViewById(R.id.pozisyon5);
        pozisyon6 = findViewById(R.id.pozisyon6);


        //////////////////////////////////////////////////////

        btnKaydet2 = findViewById(R.id.btnKaydet2);

        //////////////////////////////////////////////////////

        btnEkstraSirket.setOnClickListener(new View.OnClickListener() {

            int i = 1;
            @Override
            public void onClick(View v) {
                if(i == 3){
                    extraLayout3.setVisibility(View.VISIBLE);
                    btnEkstraSirket.setVisibility(View.INVISIBLE);
                }
                if(i == 2){
                    extraLayout2.setVisibility(View.VISIBLE);
                    i = 3;
                }
                if(i == 1){
                    extraLayout1.setVisibility(View.VISIBLE);
                    i = 2;
                }
            }
        });


        //////////////////////////////////////////////////////


        ArrayAdapter<CharSequence> adapterpozisyon = ArrayAdapter.createFromResource(this,R.array.isdurumu,android.R.layout.simple_spinner_item);
        adapterpozisyon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pozisyon1.setAdapter(adapterpozisyon);

        Isarrayi isarrayi = new Isarrayi();
        final String[] is = isarrayi.pozisyon;


        pozisyon1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pozisyontext1.setText(is[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapterpozisyon2 = ArrayAdapter.createFromResource(this,R.array.isdurumu,android.R.layout.simple_spinner_item);
        adapterpozisyon2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pozisyon2.setAdapter(adapterpozisyon2);

        pozisyon2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pozisyontext2.setText(is[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapterpozisyon3 = ArrayAdapter.createFromResource(this,R.array.isdurumu,android.R.layout.simple_spinner_item);
        adapterpozisyon3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pozisyon3.setAdapter(adapterpozisyon3);

        pozisyon3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pozisyontext3.setText(is[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



            ArrayAdapter<CharSequence> adapterpozisyon4 = ArrayAdapter.createFromResource(this,R.array.isdurumu,android.R.layout.simple_spinner_item);
            adapterpozisyon4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pozisyon4.setAdapter(adapterpozisyon4);

            pozisyon4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pozisyontext4.setText(is[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



            ArrayAdapter<CharSequence> adapterpozisyon5 = ArrayAdapter.createFromResource(this,R.array.isdurumu,android.R.layout.simple_spinner_item);
            adapterpozisyon5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pozisyon5.setAdapter(adapterpozisyon5);

            pozisyon5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pozisyontext5.setText(is[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        ArrayAdapter<CharSequence> adapterpozisyon6 = ArrayAdapter.createFromResource(this,R.array.isdurumu,android.R.layout.simple_spinner_item);
        adapterpozisyon6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pozisyon6.setAdapter(adapterpozisyon6);

        pozisyon6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pozisyontext6.setText(is[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnKaydet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sirket1 = editSirket1.getText().toString();
                final String baslangic1 = editBaslangic1.getText().toString();
                final String bitis1 = editBitis1.getText().toString();
                final String sirket2 = editSirket2.getText().toString();
                final String baslangic2 = editBaslangic2.getText().toString();
                final String bitis2 = editBitis2.getText().toString();
                final String sirket3 = editSirket3.getText().toString();
                final String baslangic3 = editBaslangic3.getText().toString();
                final String bitis3 = editBitis3.getText().toString();
                final String pozisyon01 = pozisyontext1.getText().toString();
                final String pozisyon02 = pozisyontext2.getText().toString();
                final String pozisyon03 = pozisyontext3.getText().toString();
                /////////////////////////////////////////////////////////////
                final String sirket4 = editSirket4.getText().toString();
                final String baslangic4 = editBaslangic4.getText().toString();
                final String bitis4 = editBitis4.getText().toString();
                final String pozisyon04 = pozisyontext4.getText().toString();
                //////////////////////////////////////////////////////////
                final String sirket5 = editSirket5.getText().toString();
                final String baslangic5 = editBaslangic5.getText().toString();
                final String bitis5 = editBitis5.getText().toString();
                final String pozisyon05 = pozisyontext5.getText().toString();
                //////////////////////////////////////////////////////////
                final String sirket6 = editSirket6.getText().toString();
                final String baslangic6 = editBaslangic6.getText().toString();
                final String bitis6 = editBitis6.getText().toString();
                final String pozisyon06 = pozisyontext6.getText().toString();
                //////////////////////////////////////////////////////////

                auth2 = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth2.getCurrentUser();
                final String userUid = auth2.getCurrentUser().getUid();

                if(currentUser != null){

                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference ref = database.getReference();


                    ref.child("kullanicilar").child(userUid).child("is").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref.child("kullanicilar").child(userUid).child("is").child("sirket1").setValue(sirket1);
                            ref.child("kullanicilar").child(userUid).child("is").child("baslangic1").setValue(baslangic1);
                            ref.child("kullanicilar").child(userUid).child("is").child("bitis1").setValue(bitis1);
                            ref.child("kullanicilar").child(userUid).child("is").child("sirket2").setValue(sirket2);
                            ref.child("kullanicilar").child(userUid).child("is").child("baslangic2").setValue(baslangic2);
                            ref.child("kullanicilar").child(userUid).child("is").child("bitis2").setValue(bitis2);
                            ref.child("kullanicilar").child(userUid).child("is").child("sirket3").setValue(sirket3);
                            ref.child("kullanicilar").child(userUid).child("is").child("baslangic3").setValue(baslangic3);
                            ref.child("kullanicilar").child(userUid).child("is").child("bitis3").setValue(bitis3);
                            ref.child("kullanicilar").child(userUid).child("is").child("pozisyon1").setValue(pozisyon01);
                            ref.child("kullanicilar").child(userUid).child("is").child("pozisyon2").setValue(pozisyon02);
                            ref.child("kullanicilar").child(userUid).child("is").child("pozisyon3").setValue(pozisyon03);
                            if(true){
                                ref.child("kullanicilar").child(userUid).child("is").child("sirket4").setValue(sirket4);
                                ref.child("kullanicilar").child(userUid).child("is").child("baslangic4").setValue(baslangic4);
                                ref.child("kullanicilar").child(userUid).child("is").child("bitis4").setValue(bitis4);
                                ref.child("kullanicilar").child(userUid).child("is").child("sirket4").setValue(sirket4);
                            }
                            if(true){
                                ref.child("kullanicilar").child(userUid).child("is").child("sirket5").setValue(sirket5);
                                ref.child("kullanicilar").child(userUid).child("is").child("baslangic5").setValue(baslangic5);
                                ref.child("kullanicilar").child(userUid).child("is").child("bitis5").setValue(bitis5);
                                ref.child("kullanicilar").child(userUid).child("is").child("sirket5").setValue(sirket5);
                            }
                            if(true){
                                ref.child("kullanicilar").child(userUid).child("is").child("sirket6").setValue(sirket6);
                                ref.child("kullanicilar").child(userUid).child("is").child("baslangic6").setValue(baslangic6);
                                ref.child("kullanicilar").child(userUid).child("is").child("bitis6").setValue(bitis6);
                                ref.child("kullanicilar").child(userUid).child("is").child("sirket6").setValue(sirket6);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                        }
                    });

                }

                Toast.makeText(getApplicationContext(),"Succesfuly Saved",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HosgeldinPage.class);
                startActivity(intent);
            }
        });
    }
}

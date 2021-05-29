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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.EntityReference;

public class DigerPage extends AppCompatActivity {

    TextView yabancidiltext1 , yabancidiltext2 , hobitext1 , hobitext2,seviye1,seviye2;
    EditText bilgisayarbilgisi ,  referanslar , sertifikalar;
    Spinner yabancidil1 , yabancidil2 , hobiler1 , hobiler2 , yabancidilseviye1 , yabancidilseviye2;
    Button btnKaydet4;

    private FirebaseAuth auth4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diger_page);

        Toast.makeText(getApplicationContext(),"Please Don't Use Turkish Characters",Toast.LENGTH_SHORT).show();

        bilgisayarbilgisi = findViewById(R.id.bilgisayarbilgisi);
        referanslar = findViewById(R.id.referanslar);
        sertifikalar = findViewById(R.id.sertifikalar);
        yabancidiltext1 = findViewById(R.id.yabancidiltext1);
        yabancidiltext2 = findViewById(R.id.yabancidiltext2);
        hobitext1 = findViewById(R.id.hobitext1);
        hobitext2 = findViewById(R.id.hobitext2);
        ///////////////////////////////////////////////////////////

        yabancidil1 = findViewById(R.id.yabancidil1);
        yabancidil2 = findViewById(R.id.yabancidil2);

        yabancidilseviye1 = findViewById(R.id.yabancidilseviye1);
        yabancidilseviye2 = findViewById(R.id.yabancidilseviye2);

        seviye1 = findViewById(R.id.seviye1);
        seviye2 = findViewById(R.id.seviye2);

        hobiler1 = findViewById(R.id.hobiler1);
        hobiler2 = findViewById(R.id.hobiler2);


        ////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////

        btnKaydet4 = findViewById(R.id.btnKaydet4);

        ArrayAdapter<CharSequence> adapterdil1 = ArrayAdapter.createFromResource(this,R.array.yabancidildurumu,android.R.layout.simple_spinner_item);
        adapterdil1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yabancidil1.setAdapter(adapterdil1);

        DilArrayi dilArrayi = new DilArrayi();
        final String dildurumu[] = dilArrayi.dilbilgi;


        yabancidil1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yabancidiltext1.setText(dildurumu[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapterdil2 = ArrayAdapter.createFromResource(this,R.array.yabancidildurumu,android.R.layout.simple_spinner_item);
        adapterdil2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yabancidil2.setAdapter(adapterdil2);

        yabancidil2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yabancidiltext2.setText(dildurumu[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final String dilseviye[] = new String[6];
        dilseviye[0]= "Dil Seviyesi";
        dilseviye[1]= "Start";
        dilseviye[2]= "Mitte";
        dilseviye[3]= "Gut";
        dilseviye[4]= "Sehr gut";


        ArrayAdapter<CharSequence> adapterseviye1 = ArrayAdapter.createFromResource(this,R.array.dilseviye,android.R.layout.simple_spinner_item);
        adapterseviye1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yabancidilseviye1.setAdapter(adapterseviye1);

        yabancidilseviye1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seviye1.setText(dilseviye[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> adapterseviye2 = ArrayAdapter.createFromResource(this,R.array.dilseviye,android.R.layout.simple_spinner_item);
        adapterseviye2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yabancidilseviye2.setAdapter(adapterseviye2);

        yabancidilseviye2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seviye2.setText(dilseviye[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        HobiArrrayi hobiArrrayi = new HobiArrrayi();
        final String hobiler[] = hobiArrrayi.hobiarray;

        ArrayAdapter<CharSequence> adapterhobiler1 = ArrayAdapter.createFromResource(this,R.array.hobidurumu,android.R.layout.simple_spinner_item);
        adapterhobiler1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hobiler1.setAdapter(adapterhobiler1);

        hobiler1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hobitext1.setText(hobiler[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapterhobiler2 = ArrayAdapter.createFromResource(this,R.array.hobidurumu,android.R.layout.simple_spinner_item);
        adapterhobiler2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hobiler2.setAdapter(adapterhobiler2);

        hobiler2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hobitext2.setText(hobiler[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        btnKaydet4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String bilgisayar = bilgisayarbilgisi.getText().toString();
                final String referans = referanslar.getText().toString();
                final String sertifika = sertifikalar.getText().toString();
                final String yabancidil01 = yabancidiltext1.getText().toString();
                final String yabancidil02 = yabancidiltext2.getText().toString();
                final String seviye01 = seviye1.getText().toString();
                final String seviye02 = seviye2.getText().toString();
                final String hobi01 = hobitext1.getText().toString();
                final String hobi02 = hobitext2.getText().toString();

                auth4 = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth4.getCurrentUser();
                final String userUid = auth4.getCurrentUser().getUid();

                if(currentUser != null){

                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference ref = database.getReference();


                    ref.child("kullanicilar").child(userUid).child("diger").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref.child("kullanicilar").child(userUid).child("diger").child("bilgisayar").setValue(bilgisayar);
                            ref.child("kullanicilar").child(userUid).child("diger").child("referans").setValue(referans);
                            ref.child("kullanicilar").child(userUid).child("diger").child("sertifika").setValue(sertifika);
                            ref.child("kullanicilar").child(userUid).child("diger").child("yabancidil1").setValue(yabancidil01);
                            ref.child("kullanicilar").child(userUid).child("diger").child("yabancidil2").setValue(yabancidil02);
                            ref.child("kullanicilar").child(userUid).child("diger").child("seviye1").setValue(seviye01);
                            ref.child("kullanicilar").child(userUid).child("diger").child("seviye2").setValue(seviye02);
                            ref.child("kullanicilar").child(userUid).child("diger").child("hobiler1").setValue(hobi01);
                            ref.child("kullanicilar").child(userUid).child("diger").child("hobiler2").setValue(hobi02);
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

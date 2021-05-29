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

public class EgitimPage extends AppCompatActivity {

    EditText kolej1 , kolej2 , derece1 , derece2 , baslangic1 , baslangic2 , bitis1, bitis2 , bolumtext1 , bolumtext2 , lisanstext1 , lisanstext2;
    Spinner lisans1 , lisans2 , bolum1 , bolum2;
    Button btnKaydet3;

    private FirebaseAuth newAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitim_page);

        Toast.makeText(getApplicationContext(),"Please Don't Use Turkish Characters",Toast.LENGTH_SHORT).show();


        kolej1 = findViewById(R.id.kolej1);
        kolej2 = findViewById(R.id.kolej2);
        derece1 = findViewById(R.id.derece1);
        derece2 = findViewById(R.id.derece2);
        baslangic1 = findViewById(R.id.baslangıc1);
        baslangic2 = findViewById(R.id.baslangıc2);
        bitis1 = findViewById(R.id.bitis1);
        bitis2 = findViewById(R.id.bitis2);
        bolumtext1 = findViewById(R.id.bolumtext1);
        bolumtext2 = findViewById(R.id.bolumtext2);
        lisanstext1 = findViewById(R.id.lisanstext1);
        lisanstext2 = findViewById(R.id.lisanstext2);

        ///////////////////////////////////////////////

        lisans1 = findViewById(R.id.lisans1);
        lisans2 = findViewById(R.id.lisans2);
        bolum1 = findViewById(R.id.bolum1);
        bolum2 = findViewById(R.id.bolum2);

        ///////////////////////////////////////////////

        btnKaydet3 = findViewById(R.id.btnKaydet3);

        ///////////////////////////////////////////////

        ArrayAdapter<CharSequence> adapteregitim = ArrayAdapter.createFromResource(this,R.array.egitimdurumu,android.R.layout.simple_spinner_item);
        adapteregitim.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lisans1.setAdapter(adapteregitim);

        final String lisansdurumu[] = new String[8];
        lisansdurumu[0] = "Seciniz";//almancalarını yaz
        lisansdurumu[1] = "Grundschule";
        lisansdurumu[2] = "Mittelschule";
        lisansdurumu[3] = "Weiterführende Schule\n";
        lisansdurumu[4] = "Grundschulbildung";
        lisansdurumu[5] = "Universität";
        lisansdurumu[6] = "Meister";
        lisansdurumu[7] = "Promotion";

        lisans1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lisanstext1.setText(lisansdurumu[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapteregitim2 = ArrayAdapter.createFromResource(this,R.array.egitimdurumu,android.R.layout.simple_spinner_item);
        adapteregitim2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lisans2.setAdapter(adapteregitim2);

        lisans2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lisanstext2.setText(lisansdurumu[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        BolumArrayi bolumArrayi = new BolumArrayi();
        final String[] bolumler = bolumArrayi.okulbolumu;


        ArrayAdapter<CharSequence> adapterbolum1 = ArrayAdapter.createFromResource(this,R.array.bolumdurumu,android.R.layout.simple_spinner_item);
        adapterbolum1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolum1.setAdapter(adapterbolum1);

        bolum1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bolumtext1.setText(bolumler[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapterbolum2 = ArrayAdapter.createFromResource(this,R.array.bolumdurumu,android.R.layout.simple_spinner_item);
        adapterbolum2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolum2.setAdapter(adapterbolum2);

        bolum2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bolumtext2.setText(bolumler[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnKaydet3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kolej01 = kolej1.getText().toString();
                final String kolej02 = kolej2.getText().toString();
                final String derece01 = derece1.getText().toString();
                final String derece02 = derece2.getText().toString();
                final String baslangic01 = baslangic1.getText().toString();
                final String baslangic02 = baslangic2.getText().toString();
                final String bitis01 = bitis1.getText().toString();
                final String bitis02 = bitis2.getText().toString();
                final String bolum01 = bolumtext1.getText().toString();
                final String bolum02 = bolumtext2.getText().toString();
                final String lisans01 = lisanstext1.getText().toString();
                final String lisans02 = lisanstext2.getText().toString();



                newAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = newAuth.getCurrentUser();
                final String userUid = newAuth.getCurrentUser().getUid();

                if(currentUser != null){

                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference ref = database.getReference();


                    ref.child("kullanicilar").child(userUid).child("egitim").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref.child("kullanicilar").child(userUid).child("egitim").child("kolej1").setValue(kolej01);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("kolej2").setValue(kolej02);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("derece1").setValue(derece01);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("derece2").setValue(derece02);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("baslangic1").setValue(baslangic01);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("baslangic2").setValue(baslangic02);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("bitis1").setValue(bitis01);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("bitis2").setValue(bitis02);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("lisans1").setValue(lisans01);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("lisans2").setValue(lisans02);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("bolum1").setValue(bolum01);
                            ref.child("kullanicilar").child(userUid).child("egitim").child("bolum2").setValue(bolum02);
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

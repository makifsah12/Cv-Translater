package com.example.cv_translater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class HosgeldinPage extends AppCompatActivity {



    private FirebaseAuth mAuth;

    Button kisiselbuton , egitimbuton , isbuton , digerbuton , olusturcv , cvgor , cvpaylas;

    TextView cikisyap;

    String cvresim;

    ProgressBar progressBar;

    String ad = "";

    String pdfname = "cvpdf";

    Integer extra4 = 0;
    Integer extra5 = 0;
    Integer extra6 = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosgeldin_page);

        mAuth = FirebaseAuth.getInstance();

        kisiselbuton = findViewById(R.id.kisiselbuton);
        egitimbuton = findViewById(R.id.egitimbuton);
        isbuton = findViewById(R.id.isbuton);
        digerbuton = findViewById(R.id.digerbuton);
        olusturcv = findViewById(R.id.olusturcv);
        cvgor = findViewById(R.id.cvgor);
        cvpaylas = findViewById(R.id.cvpaylas);
        cikisyap = findViewById(R.id.cikisyap);
        progressBar = findViewById(R.id.progressBar);


        kisiselbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(HosgeldinPage.this,KisiselPage.class);
                startActivity(intent1);
            }
        });
        egitimbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(HosgeldinPage.this,EgitimPage.class);
                startActivity(intent2);
            }
        });
        isbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(HosgeldinPage.this,IsPage.class);
                startActivity(intent3);
            }
        });
        digerbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(HosgeldinPage.this,DigerPage.class);
                startActivity(intent4);
            }
        });

        cikisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
            }
        });

        //Main part of coding begins
        //Writing content to pdf

        /*
        olusturcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new async().execute();
            }
        });
        */
        FirebaseUser currentUser = mAuth.getCurrentUser();


        final String uid = mAuth.getCurrentUser().getUid();




        if(currentUser != null){
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference ref = database.getReference();

            Toast.makeText(getApplicationContext(),"Loading User Datas...",Toast.LENGTH_SHORT).show();
            // Attach a listener to read the data at your profile reference
            ref.child("kullanicilar").child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                    final String ad          = dataSnapshot.child("kisisel").child("ad").getValue(String.class);
                    final String soyad       = dataSnapshot.child("kisisel").child("soyad").getValue(String.class);
                    final String adres       = dataSnapshot.child("kisisel").child("adres").getValue(String.class);
                    final String telefon     = dataSnapshot.child("kisisel").child("telefon").getValue(String.class);
                    final String email       = dataSnapshot.child("kisisel").child("email").getValue(String.class);
                    final String ehliyet     = dataSnapshot.child("kisisel").child("ehliyet").getValue(String.class);
                    final String medenidurum = dataSnapshot.child("kisisel").child("medenidurum").getValue(String.class);
                    final String dogumyerivetarihi = dataSnapshot.child("kisisel").child("dogumyerivetarihi").getValue(String.class);
                    final String resim       = dataSnapshot.child("kisisel").child("resim").getValue(String.class);
                    final String kolej1      = dataSnapshot.child("egitim").child("kolej1").getValue(String.class);
                    final String kolej2      = dataSnapshot.child("egitim").child("kolej2").getValue(String.class);
                    final String derece1     = dataSnapshot.child("egitim").child("derece1").getValue(String.class);
                    final String derece2     = dataSnapshot.child("egitim").child("derece2").getValue(String.class);
                    final String baslangic1  = dataSnapshot.child("egitim").child("baslangic1").getValue(String.class);
                    final String baslangic2  = dataSnapshot.child("egitim").child("baslangic2").getValue(String.class);
                    final String bitis1      = dataSnapshot.child("egitim").child("bitis1").getValue(String.class);
                    final String bitis2      = dataSnapshot.child("egitim").child("bitis2").getValue(String.class);
                    final String lisans1     = dataSnapshot.child("egitim").child("lisans1").getValue(String.class);
                    final String lisans2     = dataSnapshot.child("egitim").child("lisans2").getValue(String.class);
                    final String bolum1      = dataSnapshot.child("egitim").child("bolum1").getValue(String.class);
                    final String bolum2      = dataSnapshot.child("egitim").child("bolum2").getValue(String.class);
                    final String sirket1     = dataSnapshot.child("is").child("sirket1").getValue(String.class);
                    final String baslangic01  = dataSnapshot.child("is").child("baslangic1").getValue(String.class);
                    final String bitis01     = dataSnapshot.child("is").child("bitis1").getValue(String.class);
                    final String sirket2     = dataSnapshot.child("is").child("sirket2").getValue(String.class);
                    final String baslangic02     = dataSnapshot.child("is").child("baslangic2").getValue(String.class);
                    final String bitis02     = dataSnapshot.child("is").child("bitis2").getValue(String.class);
                    final String sirket3     = dataSnapshot.child("is").child("sirket3").getValue(String.class);
                    final String baslangic3     = dataSnapshot.child("is").child("baslangic3").getValue(String.class);
                    final String bitis3     = dataSnapshot.child("is").child("bitis3").getValue(String.class);
                    final String pozisyon1     = dataSnapshot.child("is").child("pozisyon1").getValue(String.class);
                    final String pozisyon2     = dataSnapshot.child("is").child("pozisyon2").getValue(String.class);
                    final String pozisyon3     = dataSnapshot.child("is").child("pozisyon3").getValue(String.class);
                    final String bilgisayar  = dataSnapshot.child("diger").child("bilgisayar").getValue(String.class);
                    final String referans    = dataSnapshot.child("diger").child("referans").getValue(String.class);
                    final String sertifika   = dataSnapshot.child("diger").child("sertifika").getValue(String.class);
                    final String yabancidil  = dataSnapshot.child("diger").child("yabancidil1").getValue(String.class);
                    final String yabancidil2 = dataSnapshot.child("diger").child("yabancidil2").getValue(String.class);
                    final String seviye1    = dataSnapshot.child("diger").child("seviye1").getValue(String.class);
                    final String seviye2    = dataSnapshot.child("diger").child("seviye2").getValue(String.class);
                    final String hobiler1    = dataSnapshot.child("diger").child("hobiler1").getValue(String.class);
                    final String hobiler2    = dataSnapshot.child("diger").child("hobiler2").getValue(String.class);
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    final String sirket4     = dataSnapshot.child("is").child("sirket4").getValue(String.class);
                    final String baslangic04  = dataSnapshot.child("is").child("baslangic4").getValue(String.class);
                    final String bitis04     = dataSnapshot.child("is").child("bitis4").getValue(String.class);
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    final String sirket5     = dataSnapshot.child("is").child("sirket5").getValue(String.class);
                    final String baslangic05  = dataSnapshot.child("is").child("baslangic5").getValue(String.class);
                    final String bitis05     = dataSnapshot.child("is").child("bitis5").getValue(String.class);
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    final String sirket6     = dataSnapshot.child("is").child("sirket6").getValue(String.class);
                    final String baslangic06  = dataSnapshot.child("is").child("baslangic6").getValue(String.class);
                    final String bitis06     = dataSnapshot.child("is").child("bitis6").getValue(String.class);
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    final String pozisyon4     = dataSnapshot.child("is").child("pozisyon4").getValue(String.class);
                    final String pozisyon5     = dataSnapshot.child("is").child("pozisyon5").getValue(String.class);
                    final String pozisyon6     = dataSnapshot.child("is").child("pozisyon6").getValue(String.class);
                    ////////////////////////////////////////////////////////////////////////////////////////////////
                    final String pdfadimiz   = ad;
                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                    StrictMode.setVmPolicy(builder.build());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    },2000);
                    olusturcv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WriteToPdf writeToPdf1 = new WriteToPdf();
                            if(writeToPdf1.write(pdfadimiz,ad,soyad,adres,telefon,email,ehliyet,medenidurum,dogumyerivetarihi,resim,
                                    kolej1,kolej2,derece1,derece2,baslangic1,baslangic2,bitis1,bitis2,lisans1,
                                    lisans2,bolum1,bolum2,sirket1,baslangic01,bitis01,sirket2,baslangic02,bitis02,
                                    sirket3,baslangic3,bitis3,sirket4,baslangic04,bitis04,sirket5,baslangic05,bitis05,sirket6,baslangic06,bitis06,
                                    pozisyon1,pozisyon2,pozisyon3,pozisyon4,pozisyon5,pozisyon6,bilgisayar,referans,
                                    sertifika,yabancidil,yabancidil2,seviye1,seviye2,hobiler1,hobiler2)){
                                Toast.makeText(getApplicationContext(),
                                        pdfadimiz + ".pdf created", Toast.LENGTH_SHORT)
                                        .show();
                            }else{
                                Toast.makeText(getApplicationContext(), "I/O error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    cvgor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String filename =  pdfadimiz;
                            if(pdfadimiz.equals(""))
                            {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(HosgeldinPage.this);
                                builder.setMessage("Enter the name of the pdf to view");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                //    Toast.makeText(TemplateOne.this, "Enter PDF name to view pdf file", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                File file = new File("/sdcard/"+ filename +".pdf");
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                startActivity(intent);
                            }

                        }

                    });

                    cvpaylas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                    final String path = "sdcard/" + pdfadimiz + ".pdf";

                                    File file = new File(path);
                                    Uri uri = Uri.fromFile(file);

                                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                                    emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Text");
                                    emailIntent.setType("application/pdf");
                                    emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

                                    startActivity(Intent.createChooser(emailIntent, "Share using"));


                            }
                            catch (Exception e){
                                Toast.makeText(getBaseContext(),"Failed to attach",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"hata olustu",Toast.LENGTH_LONG).show();
                }
            });
        }


        Intent intent2 = getIntent();
        cvresim = intent2.getStringExtra("resimyolu");
    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(getApplicationContext(),HosgeldinPage.class);
        startActivity(setIntent);
    }


}

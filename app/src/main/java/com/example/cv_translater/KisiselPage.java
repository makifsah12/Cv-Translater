package com.example.cv_translater;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class KisiselPage extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    private Context mContext=KisiselPage.this;

    private static final int REQUEST = 112;

    EditText ad , soyad , adres , telefon , email , ehliyet , medenidurum2 , dogumyerivetarihi;
    Spinner medenidurum;
    Button btnKaydet ;
    ImageView fotoekle , circlefotoekle;




    public FirebaseAuth auth1;

    String picturePath;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            picturePath = cursor.getString(columnIndex);

            circlefotoekle.setMaxHeight(150);
            circlefotoekle.setMaxWidth(150);

            circlefotoekle.setImageURI(selectedImage);

            cursor.close();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisisel_page);

        Toast.makeText(getApplicationContext(),"Please Don't Use Turkish Characters",Toast.LENGTH_SHORT).show();


        ad = findViewById(R.id.ad1);
        soyad = findViewById(R.id.soyad1);
        adres = findViewById(R.id.adres1);
        telefon = findViewById(R.id.telefon1);
        email = findViewById(R.id.email1);
        ehliyet = findViewById(R.id.ehliyet1);
        medenidurum2 = findViewById(R.id.medenidurum2);
        dogumyerivetarihi = findViewById(R.id.dogumyerivetarihi1);
        //fotoekle = findViewById(R.id.fotoekle);
        circlefotoekle = findViewById(R.id.circlefotoekle);
        circlefotoekle.setImageResource(R.drawable.photoekle);

        //////////////////////////////////////////////////

        medenidurum = findViewById(R.id.medenidurum1);

        //////////////////////////////////////////////////

        btnKaydet = findViewById(R.id.btnKaydet);

        //////////////////////////////////////////////////
        /////Shared Preferences/////
        SharedPreferences pref2 = getSharedPreferences("deneme1",Context.MODE_PRIVATE);

        String keyAd = pref2.getString("adkey","");
        String keySoyad = pref2.getString("soyadkey","");
        String keyAdres = pref2.getString("adreskey","");
        String keyTelefon = pref2.getString("telefonkey","");
        String keyEmail = pref2.getString("emailkey","");
        String keyEhliyet = pref2.getString("ehliyetkey","");
        String keyDogum  = pref2.getString("dogumyerikey","");
        String resim = pref2.getString("resimkey","");
        int medeniIndex2 = pref2.getInt("medenikey",0);


        ad.setText(keyAd);
        soyad.setText(keySoyad);
        adres.setText(keyAdres);
        telefon.setText(keyTelefon);
        email.setText(keyEmail);
        ehliyet.setText(keyEhliyet);
        dogumyerivetarihi.setText(keyDogum);


        if(resim != ""){
            Bitmap bitmap1 = BitmapFactory.decodeFile(resim);
            circlefotoekle.setImageBitmap(bitmap1);
        }

        //////////////////////////////////////////////////


        ArrayAdapter<CharSequence> adaptermedeni = ArrayAdapter.createFromResource(this,R.array.medenidurum,android.R.layout.simple_spinner_item);
        adaptermedeni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medenidurum.setAdapter(adaptermedeni);

        final String medenidurum3[] = new String[3];
        medenidurum3[0] = "Medeni Durum Secilmedi";//almancalarını yaz
        medenidurum3[1] = "Die verheirateten\n";
        medenidurum3[2] = "Single";

        if(medeniIndex2 != 0){
            medenidurum.setSelection(medeniIndex2);
        }

        medenidurum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medenidurum2.setText(medenidurum3[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        circlefotoekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });


        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ad1 = ad.getText().toString();
                final String soyad1 = soyad.getText().toString();
                final String adres1 = adres.getText().toString();
                final String telefon1 = telefon.getText().toString();
                final String email1 = email.getText().toString();
                final String ehliyet1 = ehliyet.getText().toString();
                final String medenidurum8 = medenidurum2.getText().toString();
                final String dogumyerivetarihi1 = dogumyerivetarihi.getText().toString();
                final String kullaniciresim = picturePath;

                SharedPreferences pref1 = getSharedPreferences("deneme1",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor  = pref1.edit();
                editor.putString("adkey",ad1);
                editor.putString("soyadkey",soyad1);
                editor.putString("adreskey",adres1);
                editor.putString("telefonkey",ad1);
                editor.putString("emailkey",telefon1);
                editor.putString("ehliyetkey",email1);
                editor.putString("dogumyerikey",dogumyerivetarihi1);
                editor.putString("resimkey",kullaniciresim);
                int medeniIndex = medenidurum.getSelectedItemPosition();
                editor.putInt("medenikey",medeniIndex);
                editor.commit();


                auth1 = FirebaseAuth.getInstance();
                FirebaseUser currentUser = auth1.getCurrentUser();
                final String userUid = auth1.getCurrentUser().getUid();

                if(currentUser != null){

                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference ref = database.getReference();


                    ref.child("kullanicilar").child(userUid).child("kisisel").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("ad").setValue(ad1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("soyad").setValue(soyad1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("adres").setValue(adres1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("telefon").setValue(telefon1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("email").setValue(email1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("ehliyet").setValue(ehliyet1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("medenidurum").setValue(medenidurum8);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("dogumyerivetarihi").setValue(dogumyerivetarihi1);
                            ref.child("kullanicilar").child(userUid).child("kisisel").child("resim").setValue(kullaniciresim);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                        }
                    });

                }

                Toast.makeText(getApplicationContext(),"Succesfuly Saved",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),HosgeldinPage.class);
                intent.putExtra("resimyolu",picturePath);
                startActivity(intent);
            }
        });



        if (Build.VERSION.SDK_INT >= 24) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
            } else {
                //do here
            }
        } else {
            //do here
        }


        checkPermission();

    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(mContext, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void checkPermission(){

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(),"Permission Not Given",Toast.LENGTH_SHORT).show();

            }
        };

        TedPermission.with(KisiselPage.this).setPermissionListener(permissionListener).setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


}

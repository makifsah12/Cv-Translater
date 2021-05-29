package com.example.cv_translater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class UyeGirisiPage extends AppCompatActivity {

    private Context mContext=UyeGirisiPage.this;

    private static final int REQUEST = 112;


    EditText loginEmail, loginPassword;
    Button girisyapbutonu , uyeolbutonu;
    TextView txtuyeol;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uye_girisi_page);

        mAuth = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        girisyapbutonu = findViewById(R.id.girisyapbutonu);

        girisyapbutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                girisyap();
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

        TedPermission.with(UyeGirisiPage.this).setPermissionListener(permissionListener).setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(UyeGirisiPage.this,HosgeldinPage.class);
            startActivity(intent);
        }
    }

    public void transitionToHosgeldinPage(){
        Intent intent = new Intent(UyeGirisiPage.this,HosgeldinPage.class);
        startActivity(intent);
    }


    public void girisyap(){
        String loginmail = loginEmail.getText().toString();
        String sifre = loginPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(loginmail,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    transitionToHosgeldinPage();
                    Toast.makeText(UyeGirisiPage.this,"Succesfully Signed In",Toast.LENGTH_SHORT).show();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(UyeGirisiPage.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

 package audy.ruslan.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

    private Button mSaveBtn;
    private EditText mMainText;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirestore = FirebaseFirestore.getInstance();

        mMainText = (EditText) findViewById(R.id.mainText);
        mSaveBtn = (Button) findViewById(R.id.saveBtn);

        mSaveBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = mMainText.getText().toString();

                Map<String, String> userMap = new HashMap<>();

                userMap.put("nama", username);
                userMap.put("gambar", "gambar_link");

                mFirestore.collection("user").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Username Added te Firestore", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(MainActivity.this, "Error : "+error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

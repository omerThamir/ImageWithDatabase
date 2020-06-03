package com.example.imagewithdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.imageView);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        findViewById(R.id.insertBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.me);
                Bitmap resizedBitmap = resizeBitmapT0100x100_px(bitmap);
                dataBaseHelper.insertRow(convertBitmapToArrayOfByte(resizedBitmap));
                Toast.makeText(MainActivity.this, " one row inserted", Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.showBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dataBaseHelper.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, " there is no data to show", Toast.LENGTH_SHORT).show();
                } else {
                    if (res.moveToNext()) {
                        byte[] image = res.getBlob(1);
                        imageView.setImageBitmap(convertArrayOfByteToBitmap(image));
                    }
                }
            }
        });

    }

    /** resize bitmap bitmap to be be set with image view size */
    public static Bitmap resizeBitmapT0100x100_px(Bitmap bitmap) {
        return Bitmap.createScaledBitmap(bitmap, 100, 100, false);
    }


    /** convert from bitmap to array of byte */
    public static byte[] convertBitmapToArrayOfByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        return stream.toByteArray();
    }

    /** convert from array of byte to bitmap */
    public static Bitmap convertArrayOfByteToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }



}

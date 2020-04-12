package com.fauzi.sisfotelbrebes.uploadBerkas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fauzi.sisfotelbrebes.R;
import com.fauzi.sisfotelbrebes.api.ApiClient;
import com.fauzi.sisfotelbrebes.api.ApiInterface;
import com.fauzi.sisfotelbrebes.model.PengajuanModel;
import com.fauzi.sisfotelbrebes.utility.PrefId;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengajuanBerkas3 extends AppCompatActivity implements View.OnClickListener {
ImageView imageView;
Button pickImage, upload;
ImageButton pickImg;

private String cameraFilePath;
public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
private static final String TAG = PengajuanBerkas3.class.getSimpleName();
private static final int REQUEST_TAKE_PHOTO = 0;
private static final int REQUEST_PICK_PHOTO = 2;
private Bitmap bitmap;
private ApiInterface apiInterface;

private String tanggal_lahir, nik, nama_ibu, foto;
private String mImageFileLocation = "";
private Uri fileUri;

private static final int CAMERA_PIC_REQUEST = 1111;
private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
public static final int MEDIA_TYPE_IMAGE = 1;

String id_Pengajuan;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_pengajuan_berkas2);

    PengajuanModel id = PrefId.getID(this, PrefId.USER_SESSION);
    id_Pengajuan = String.valueOf(id.getData().getId());

    imageView = (ImageView) findViewById(R.id.preview);
    pickImg = (ImageButton) findViewById(R.id.pickImage);
    upload = (Button) findViewById(R.id.upload);

    pickImg.setOnClickListener(this);
    upload.setOnClickListener(this);

//        pickImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pilihfile();
//            }
//        });
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadFile("insert");
//            }
//        });
}

public void onClick(final View v) {
    switch (v.getId()) {
        case R.id.pickImage:
            new MaterialDialog.Builder(this)
                    .title(R.string.uploadImages)
                    .items(R.array.uploadImages)
                    .itemsIds(R.array.itemIds)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            switch (which) {
                                case 0:
                                   pilihfile();
                                    break;
                                case 1:
                                    captureImage();
                                    break;
                                case 2:
                                    imageView.setImageResource(R.drawable.ic_crop_original_black_24dp);
                                    break;
                            }
                        }
                    })
                    .show();
            break;
        case R.id.upload:
            uploadFile("insert");
            break;
    }
}
public String getStringImage(Bitmap bmp) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] imageBytes = baos.toByteArray();
    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
    return encodedImage;
}
private void pilihfile () {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
private void captureImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
//        Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, 1);
//        }

}

@Override
protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

private void uploadFile(String key) {
    //Munculkan dialog Progress
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Saving...");
    progressDialog.show();

//    String kode = "2";

    String picture = null;

    if (bitmap == null) {
        picture = "";
    } else {
        picture = getStringImage(bitmap);
    }
    //Memanggil Api Interface nya dan Api Client nya (BASE URL nya)
    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    //masukan parameternya
    Call<UploadBerkas> call = apiInterface.UploadDataAll(key, id_Pengajuan, picture);
    //masukan dalam enqueue (antrian)
    call.enqueue(new Callback<UploadBerkas>() {
        @Override
        public void onResponse(Call<UploadBerkas> call, Response<UploadBerkas> response) {
            progressDialog.dismiss();

            String value = response.body().getValue();
            String message = response.body().getMessage();

            if (value.equals("1")){
                Intent intent = new Intent(PengajuanBerkas3.this, PengajuanBerkas3.class);
                startActivity(intent);
            } else {
                Toast.makeText(PengajuanBerkas3.this, message, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<UploadBerkas> call, Throwable t) {
            progressDialog.dismiss();
            Toast.makeText(PengajuanBerkas3.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }
    });
}

}

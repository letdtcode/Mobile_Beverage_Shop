package com.iostar.beverageshop.activity.user;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.ActivityPersonalBinding;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IUserService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.RealPathUtils;
import com.iostar.beverageshop.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 113;
    private ActivityPersonalBinding binding;
    private Uri mUri = null;
    private ProgressDialog mProgressDialog;

    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            binding.imgProfile.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    public static String[] storge_permissions = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static String[] getStorge_permissions_33 = {
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_AUDIO,
            android.Manifest.permission.READ_MEDIA_VIDEO
    };

    public static String[] permissions() {
        String[] p;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            p = getStorge_permissions_33;
        } else {
            p = storge_permissions;
        }
        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPersonalBinding.inflate(getLayoutInflater());
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait ...");
        setContentView(binding.getRoot());
        getInfoUser();
        setEvent();
    }


    private void getInfoUser() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        User user = (User) bundle.get("object_user");

        String avatarUrl = user.getAvatar();
        if (avatarUrl == null || avatarUrl == "")
        {
            Glide.with(PersonalActivity.this).load(R.drawable.avatar_default).into(binding.imgProfile);
        } else {
            Glide.with(PersonalActivity.this).load(user.getAvatar()).into(binding.imgProfile);
        }
        binding.edUsername.setText(user.getUserName());
        binding.edtFirstName.setText(user.getFirstName());
        binding.edtLastName.setText(user.getLastName());
        binding.edtEmail.setText(user.getMail());
        binding.edtNumberPhone.setText(user.getPhone());
        binding.edtAddress.setText(user.getAddress());
    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(PersonalActivity.this, MainActivity.class));
                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
                intent.putExtra("data_result", DataLocalManager.getUser().getAvatar());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        binding.tvChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    mProgressDialog.show();
                    callAPIUploadInfoProfile();
                    callAPIUploadImgProfile();
                }
            }
        });
    }

    private void callAPIUploadInfoProfile() {
        User userInMemory = DataLocalManager.getUser();
        userInMemory.setUserName(binding.edUsername.getText().toString());
        userInMemory.setFirstName(binding.edUsername.getText().toString());
        userInMemory.setLastName(binding.edUsername.getText().toString());
        userInMemory.setMail(binding.edUsername.getText().toString());
        userInMemory.setPhone(binding.edUsername.getText().toString());
        userInMemory.setAddress(binding.edUsername.getText().toString());

        JsonObject jsonReq = new Gson().toJsonTree(userInMemory).getAsJsonObject();
        BaseAPIService.createService(IUserService.class).updateInfoUser(userInMemory.getId(), jsonReq).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void callAPIUploadImgProfile() {
        String strRealPath = RealPathUtils.getRealPath(this, mUri);
        Log.e("RealPath", strRealPath);
        File file = new File(strRealPath);

        RequestBody requestBodyAvatar = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyAvatar = MultipartBody.Part.createFormData("file", file.getName(), requestBodyAvatar);

        BaseAPIService.createService(IUserService.class).updateImageUser(multipartBodyAvatar,
                DataLocalManager.getUser().getId()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user != null) {
                    DataLocalManager.saveUser(user);
                }
                mProgressDialog.dismiss();
                ToastUtils.showToastCustom(PersonalActivity.this, "Cập nhật thành công");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressDialog.dismiss();
                ToastUtils.showToast(PersonalActivity.this, "Call API thất bại");
            }
        });

    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            requestPermissions(permissions(), MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }


}
package com.iostar.beverageshop.fragment.staff.notification;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.databinding.FragmentStaffAddNotificationBinding;
import com.iostar.beverageshop.model.Notification;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.INotificationService;
import com.iostar.beverageshop.utils.ToastUtils;
import com.iostar.beverageshop.utils.constants.NOTIFICATION_STATUS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffAddNotificationFragment extends Fragment {
    private FragmentStaffAddNotificationBinding binding;
    private List<String> nameStatusNotification;
    private List<Integer> imageNotiList;
    private Random randomImgNoti;
    private Integer statusNotifi;
    private Integer imgResourceId;
    private Resources resources;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffAddNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initial();
        setEvent();
    }

    private void initial() {
//        Adapter for spinner
        nameStatusNotification = new ArrayList<>();
        nameStatusNotification.add(NOTIFICATION_STATUS.DISABLE);
        nameStatusNotification.add(NOTIFICATION_STATUS.ENABLE);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, nameStatusNotification);
        binding.spinnerStatusNoti.setAdapter(spinnerAdapter);

        Glide.with(getActivity()).load(R.drawable.notifi_0).into(binding.imgNoti);
        imgResourceId = R.drawable.notifi_0;

//        Add Img Noti List
        imageNotiList = new ArrayList<>();
        imageNotiList.add(R.drawable.notifi_1);
        imageNotiList.add(R.drawable.notifi_2);
        imageNotiList.add(R.drawable.notifi_3);
        imageNotiList.add(R.drawable.notifi_4);
        imageNotiList.add(R.drawable.notifi_5);
        imageNotiList.add(R.drawable.notifi_6);
        randomImgNoti = new Random();
        gson = new Gson();
        resources = getResources();
    }

    private byte[] getBytesFromInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            Log.e("err_tranfer_img", e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void setEvent() {
        binding.tvAddImgDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomImgNoti();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edtTitle.getText().toString().trim();
                String content = binding.edtContent.getText().toString().trim();
                if (checkValid(title, content)) {
//                    File
                    Log.e("imgid", imgResourceId.toString());
                    String imageName = resources.getResourceEntryName(imgResourceId);
                    InputStream inputStream = getResources().openRawResource(imgResourceId);
                    RequestBody requestBodyImgNoti = RequestBody.create(MediaType.parse("multipart/form-data"), getBytesFromInputStream(inputStream));
                    MultipartBody.Part multipartBodyImgNoti = MultipartBody.Part.createFormData("file", imageName, requestBodyImgNoti);

//                    Model Notification
                    Notification notification = new Notification(title, content, statusNotifi);
                    String jsonModelNotification = gson.toJson(notification);
                    RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json"), jsonModelNotification);
//                    Call API
                    BaseAPIService.createService(INotificationService.class).createNotification(multipartBodyImgNoti, jsonBody).enqueue(new Callback<Notification>() {
                        @Override
                        public void onResponse(Call<Notification> call, Response<Notification> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ToastUtils.showToastCustom(getActivity(), "Thêm thông báo thành công");
                            }
                        }

                        @Override
                        public void onFailure(Call<Notification> call, Throwable t) {
                            Log.e("err_add_noti", t.getMessage());
                        }
                    });
                }
                ;
            }
        });
        binding.spinnerStatusNoti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        statusNotifi = 0;
                        break;
                    case 1:
                        statusNotifi = 1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean checkValid(String title, String content) {
        if (title.isEmpty()) {
            binding.edtTitle.setError("Vui lòng nhập tiêu đề");
            return false;
        }
        if (content.isEmpty()) {
            binding.edtContent.setError("Vui lòng nhập nội dung");
            return false;
        }
        return true;
    }

    private void randomImgNoti() {
        int randomIndex = randomImgNoti.nextInt(imageNotiList.size());
        int randomImgId = imageNotiList.get(randomIndex);
        Glide.with(getActivity()).load(randomImgId).into(binding.imgNoti);
        imgResourceId = randomImgId;
    }

}
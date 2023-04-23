package com.iostar.beverageshop.fragment.user;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.activity.user.DetailProductActivity;
import com.iostar.beverageshop.activity.user.PersonalActivity;
import com.iostar.beverageshop.adapter.user.CategoryHomeAdapter;
import com.iostar.beverageshop.adapter.user.ProductHomeAdapter;
import com.iostar.beverageshop.databinding.FragmentHomeBinding;
import com.iostar.beverageshop.model.Category;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.model.Size;
import com.iostar.beverageshop.model.Topping;
import com.iostar.beverageshop.model.User;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ICategoryService;
import com.iostar.beverageshop.service.IProductService;
import com.iostar.beverageshop.service.ISizeService;
import com.iostar.beverageshop.service.IToppingService;
import com.iostar.beverageshop.storage.DataLocalManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<Category> categoryList;
    private List<Product> productList;
    private ProductHomeAdapter productHomeAdapter;
    private List<Size> sizeList;
    private List<Topping> toppingList;

    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String imgAvatarCallBack = intent.getStringExtra("data_result");
                        loadImgAvatarUser(imgAvatarCallBack);
                    }
                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoryList = new ArrayList<>();
        binding.rcvCategory.setHasFixedSize(true);
        binding.rcvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        binding.rcvProduct.setHasFixedSize(true);
        binding.rcvProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        getAllCategories();
        getAllProduct();
        getInfoSizeOfProduct();
        getInfoTopping();
        setEvent();
    }

    private void setEvent() {
        binding.cardViewImgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = DataLocalManager.getUser();
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user", user);
                intent.putExtras(bundle);
//                startActivity(intent);
                mActivityResultLauncher.launch(intent);
            }
        });
    }

    private void loadImgAvatarUser(String avatarUrl) {
        if (avatarUrl == null || avatarUrl == "") {
            Glide.with(getActivity()).load(R.drawable.avatar_default).into(binding.imgProfile);
        } else {
            Glide.with(getActivity()).load(avatarUrl).into(binding.imgProfile);
        }
    }

    private void getAllProduct() {
        BaseAPIService.createService(IProductService.class).getInfoAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productList = response.body();
                productHomeAdapter = new ProductHomeAdapter(productList, getActivity(), product -> onClickToDetailProduct(product));
                binding.rcvProduct.setAdapter(productHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getActivity(), "Call API that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllCategories() {
        BaseAPIService.createService(ICategoryService.class).getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = response.body();
                CategoryHomeAdapter categoryHomeAdapter = new CategoryHomeAdapter(categoryList);
                binding.rcvCategory.setAdapter(categoryHomeAdapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getActivity(), "Call API that bai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickToDetailProduct(Product product) {
        Intent intent = new Intent(getActivity(), DetailProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        bundle.putSerializable("size_list", (Serializable) sizeList);
        bundle.putSerializable("topping_list", (Serializable) toppingList);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getInfoSizeOfProduct() {
        sizeList = new ArrayList<>();
        BaseAPIService.createService(ISizeService.class).getInfoSizeInfo().enqueue(new Callback<List<Size>>() {
            @Override
            public void onResponse(Call<List<Size>> call, Response<List<Size>> response) {
                sizeList = response.body();
            }

            @Override
            public void onFailure(Call<List<Size>> call, Throwable t) {
                Log.e("Error size: ", t.getMessage());
            }
        });
    }

    private void getInfoTopping() {
        toppingList = new ArrayList<>();
        BaseAPIService.createService(IToppingService.class).getInfoToppingInfo().enqueue(new Callback<List<Topping>>() {
            @Override
            public void onResponse(Call<List<Topping>> call, Response<List<Topping>> response) {
                toppingList = response.body();
            }

            @Override
            public void onFailure(Call<List<Topping>> call, Throwable t) {
                Log.e("Error topping: ", t.getMessage());
            }
        });
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (productHomeAdapter != null) {
//            productHomeAdapter.release();
//        }
//    }
}
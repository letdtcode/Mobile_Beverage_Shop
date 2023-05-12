package com.iostar.beverageshop.fragment.staff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.activity.staff.DetailProductStaffActivity;
import com.iostar.beverageshop.adapter.staff.ProductHomeStaffAdapter;
import com.iostar.beverageshop.databinding.FragmentStaffHomeBinding;
import com.iostar.beverageshop.inteface.staff.IOnItemProductListener;
import com.iostar.beverageshop.model.Category;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ICategoryService;
import com.iostar.beverageshop.service.IProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffHomeFragment extends Fragment implements IOnItemProductListener {
    private FragmentStaffHomeBinding binding;
    private List<Product> productListAll;
    private List<Category> categoryListAll;
    private ProductHomeStaffAdapter productAdapter;

    private boolean isProductListAllLoaded = false;
    private boolean isCategoryListAllLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productListAll = new ArrayList<>();
        categoryListAll = new ArrayList<>();
        binding.rvStaffProduct.setHasFixedSize(true);
        binding.rvStaffProduct.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        getAllCategories();
        getAllProduct();
    }

    private void checkIfDataLoaded() {
        if (isProductListAllLoaded && isCategoryListAllLoaded) {
            productAdapter = new ProductHomeStaffAdapter(productListAll, categoryListAll, getActivity(), this);
            binding.rvStaffProduct.setAdapter(productAdapter);
        }
    }

    private void getAllProduct() {
        BaseAPIService.createService(IProductService.class).getInfoAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                productListAll = response.body();
                isProductListAllLoaded = true;
                checkIfDataLoaded();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("staff_get_product", t.getMessage());
            }
        });
    }

    private void getAllCategories() {
        BaseAPIService.createService(ICategoryService.class).getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryListAll = response.body();
                isCategoryListAllLoaded = true;
                checkIfDataLoaded();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("staff_get_cate", t.getMessage());
            }
        });
    }

    @Override
    public void onClickItemProduct(Product product) {
        Intent intent = new Intent(getActivity(), DetailProductStaffActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product", product);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
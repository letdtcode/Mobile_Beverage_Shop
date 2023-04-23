package com.iostar.beverageshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iostar.beverageshop.R;
import com.iostar.beverageshop.adapter.SizeDetailAdapter;
import com.iostar.beverageshop.adapter.ToppingDetailAdapter;
import com.iostar.beverageshop.databinding.ActivityDetailProductBinding;
import com.iostar.beverageshop.inteface.IOnSizeClickListener;
import com.iostar.beverageshop.inteface.IOnToppingCheckedListener;
import com.iostar.beverageshop.model.CartItem;
import com.iostar.beverageshop.model.Product;
import com.iostar.beverageshop.model.Size;
import com.iostar.beverageshop.model.Topping;
import com.iostar.beverageshop.model.WishItem;
import com.iostar.beverageshop.model.request.AddCartRequest;
import com.iostar.beverageshop.model.request.AddWishRequest;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.ICartService;
import com.iostar.beverageshop.service.IWishService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity implements IOnSizeClickListener, IOnToppingCheckedListener {
    private ActivityDetailProductBinding binding;
    private SizeDetailAdapter sizeDetailAdapter;
    private ToppingDetailAdapter toppingDetailAdapter;
    private List<Size> sizeList;
    private List<Topping> toppingList;
    private Integer quantityProduct;

    private List<String> toppingNameSelected;
    private String sizeNameSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInfoDetailProduct();
        setEvent();
    }


    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailProductActivity.this, MainActivity.class));
            }
        });

        binding.frPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityProduct = Integer.valueOf(binding.tvCount.getText().toString());
                binding.tvCount.setText(String.valueOf(quantityProduct + 1));
            }
        });

        binding.frMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantityProduct = Integer.valueOf(binding.tvCount.getText().toString());
                if (quantityProduct > 1) {
                    binding.tvCount.setText(String.valueOf(quantityProduct - 1));
                }
                return;
            }
        });

        binding.frBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToBasket();
            }
        });

        binding.imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameProduct = binding.tvNameProduct.getText().toString();
                Long userId = DataLocalManager.getUser().getId();
                AddWishRequest addWishRequest = new AddWishRequest(userId, nameProduct);
                BaseAPIService.createService(IWishService.class).handleWishItem(addWishRequest).enqueue(new Callback<WishItem>() {
                    @Override
                    public void onResponse(Call<WishItem> call, Response<WishItem> response) {
                        WishItem item = response.body();
                        if (item.getStatus() == 1) {
                            ToastUtils.showToastCustom(DetailProductActivity.this, "Đã thêm vào danh sách yêu thích");
                            binding.imgFavorite.setImageResource(R.drawable.like_selected_icon);
                        } else {
                            ToastUtils.showToastCustom(DetailProductActivity.this, "Đã loại bỏ khỏi danh sách yêu thích");
                            binding.imgFavorite.setImageResource(R.drawable.like_unselected_icon);
                        }
                    }

                    @Override
                    public void onFailure(Call<WishItem> call, Throwable t) {
                        Log.e("errorHandleFavorite", t.getMessage());
                    }
                });
            }
        });
    }

    private void addProductToBasket() {
        Long userId = DataLocalManager.getUser().getId();
        String productName = binding.tvNameProduct.getText().toString();
        Integer quantity = Integer.valueOf(binding.tvCount.getText().toString());

        AddCartRequest item = new AddCartRequest(userId, productName, quantity, toppingNameSelected, sizeNameSelected);
        BaseAPIService.createService(ICartService.class).creatNewProductInCart(item).enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                ToastUtils.showToastCustom(DetailProductActivity.this, "Đã thêm sản phẩm vào giỏ hàng");
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {

            }
        });
    }

    private void getInfoDetailProduct() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
//        Get object from bundle
        Product product = (Product) bundle.get("object_product");
        sizeList = (List<Size>) bundle.get("size_list");
        toppingList = (List<Topping>) bundle.get("topping_list");

//        Show Product
        binding.tvNameProduct.setText(product.getProductName());
        binding.tvPrice.setText(product.getPriceDefault().toString() + "K");
        binding.tvDescription.setText(product.getDescription());
        Glide.with(DetailProductActivity.this).load(product.getPathImage()).into(binding.imgProduct);

//        Show Size Of Product
        binding.rcvSizeProduct.setHasFixedSize(true);
        binding.rcvSizeProduct.setLayoutManager(new LinearLayoutManager(DetailProductActivity.this, RecyclerView.VERTICAL, false));

        sizeDetailAdapter = new SizeDetailAdapter(DetailProductActivity.this, sizeList, 1, this);
        binding.rcvSizeProduct.setAdapter(sizeDetailAdapter);

//        Show Topping Of Product
        toppingNameSelected = new ArrayList<>();
        binding.rcvToppingProduct.setHasFixedSize(true);
        binding.rcvToppingProduct.setLayoutManager(new LinearLayoutManager(DetailProductActivity.this, RecyclerView.VERTICAL, false));

        toppingDetailAdapter = new ToppingDetailAdapter(DetailProductActivity.this, toppingList, this);
        binding.rcvToppingProduct.setAdapter(toppingDetailAdapter);

        String productName = product.getProductName();
        Long userId = DataLocalManager.getUser().getId();
        checkProductIsWishItemOfUser(productName, userId);
    }

    private void checkProductIsWishItemOfUser(String productName, Long userId) {
        BaseAPIService.createService(IWishService.class).checkProductIsWishItem(productName, userId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean checkIsWishItem = response.body();
                if (checkIsWishItem) {
                    binding.imgFavorite.setImageResource(R.drawable.like_selected_icon);
                } else {
                    binding.imgFavorite.setImageResource(R.drawable.like_unselected_icon);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("error_checkWishItem", t.getMessage());
            }
        });
    }

    @Override
    public void onSizeClick(String sizeNameChecked) {
        this.sizeNameSelected = sizeNameChecked;
    }

    @Override
    public void onChecked(String toppingName) {
        this.toppingNameSelected.add(toppingName);
    }

    @Override
    public void onUnchecked(String toppingName) {
        this.toppingNameSelected.remove(toppingName);
    }
}
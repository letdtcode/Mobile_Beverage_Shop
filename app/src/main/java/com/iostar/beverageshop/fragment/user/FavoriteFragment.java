package com.iostar.beverageshop.fragment.user;

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

import com.iostar.beverageshop.adapter.user.FavoriteAdapter;
import com.iostar.beverageshop.databinding.FragmentFavoriteBinding;
import com.iostar.beverageshop.model.WishItem;
import com.iostar.beverageshop.model.request.AddWishRequest;
import com.iostar.beverageshop.service.BaseAPIService;
import com.iostar.beverageshop.service.IWishService;
import com.iostar.beverageshop.storage.DataLocalManager;
import com.iostar.beverageshop.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;
    private FavoriteAdapter favoriteAdapter;
    private List<WishItem> wishItemList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wishItemList = new ArrayList<>();
        binding.rvFavorite.setHasFixedSize(true);
        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        getAllWishItem();

    }

    private void getAllWishItem() {
        Long userId = DataLocalManager.getUser().getId();
        BaseAPIService.createService(IWishService.class).getCurrentWishItemOfUser(userId).enqueue(new Callback<List<WishItem>>() {
            @Override
            public void onResponse(Call<List<WishItem>> call, Response<List<WishItem>> response) {
                wishItemList = response.body();
                if (wishItemList != null && wishItemList.size() != 0) {
                    favoriteAdapter = new FavoriteAdapter(wishItemList, getActivity());
                    binding.rvFavorite.setAdapter(favoriteAdapter);

                    favoriteAdapter.setOnItemClickListener(new FavoriteAdapter.OnImgHeartClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Long userId = DataLocalManager.getUser().getId();
                            String productName = wishItemList.get(position).getProductName();
                            BaseAPIService.createService(IWishService.class).handleWishItem(new AddWishRequest(userId, productName)).enqueue(new Callback<WishItem>() {
                                @Override
                                public void onResponse(Call<WishItem> call, Response<WishItem> response) {
                                    ToastUtils.showToastCustom(getActivity(), "Đã xóa sản phẩm khỏi danh sách yêu thích");
                                }

                                @Override
                                public void onFailure(Call<WishItem> call, Throwable t) {
                                    Log.e("err_clickItemFavorite", t.getMessage());
                                }
                            });
                            wishItemList.remove(position);
                            favoriteAdapter.notifyItemRemoved(position);
                            if (wishItemList.size() == 0) {
                                binding.imgEmpty.setVisibility(View.VISIBLE);
                                binding.tvTitle.setVisibility(View.VISIBLE);
                                binding.tvSubtitle.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                } else {
                    binding.imgEmpty.setVisibility(View.VISIBLE);
                    binding.tvTitle.setVisibility(View.VISIBLE);
                    binding.tvSubtitle.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<WishItem>> call, Throwable t) {
                Log.e("errGetListWishItem", t.getMessage());
            }
        });
    }
}
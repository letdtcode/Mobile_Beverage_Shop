package com.iostar.beverageshop.activity.user;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iostar.beverageshop.adapter.user.order_detail.ODetailWaitingConfirmAdapter;
import com.iostar.beverageshop.databinding.ActivityOrderDetailBinding;
import com.iostar.beverageshop.model.Order;
import com.iostar.beverageshop.model.OrderItem;
import com.iostar.beverageshop.utils.constants.ORDER_STATUS;

import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    private ActivityOrderDetailBinding binding;
    private List<OrderItem> orderItems;
    private Order orderCurrent;
    private ODetailWaitingConfirmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvOrderItem.setHasFixedSize(true);
        binding.rvOrderItem.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this, RecyclerView.VERTICAL, false));

        initial();
        setEvent();
    }

    private void setEvent() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initial() {
        //        Get Data From CartActivity
        Bundle bundle = getIntent().getExtras();
        orderItems = (List<OrderItem>) bundle.getSerializable("order_detail_list");
        orderCurrent = (Order) bundle.getSerializable("order_current");

        binding.tvAddress.setText(orderCurrent.getAddress());
        binding.tvNameCus.setText(orderCurrent.getNameCustomer());
        binding.tvPhoneNumber.setText(orderCurrent.getPhoneNumber());
        binding.tvMethodPayment.setText(orderCurrent.getAddress()); //
        binding.tvStatusOrder.setText(setStatusOrder(orderCurrent.getStatus()));
        binding.tvSubtotalProduct.setText(orderCurrent.getAddress());
        binding.tvSubTotalDelivery.setText(orderCurrent.getAddress());
        binding.tvTotalPrice.setText(orderCurrent.getAddress());

        adapter = new ODetailWaitingConfirmAdapter(orderItems);
        binding.rvOrderItem.setAdapter(adapter);

    }

    private String setStatusOrder(int status) {
        String nameStatusOrder = "";
        switch (status) {
            case 1:
                nameStatusOrder = ORDER_STATUS.WAIT_CONFIRM;
                break;
            case 2:
                nameStatusOrder = ORDER_STATUS.WAIT_DELIVERY;
                break;
            case 3:
                nameStatusOrder = ORDER_STATUS.RECEIVED;
                break;
            case 0:
                nameStatusOrder = ORDER_STATUS.CANCEL;
                break;
        }
        return nameStatusOrder;
    }
}
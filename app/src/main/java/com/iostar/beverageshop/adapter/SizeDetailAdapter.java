package com.iostar.beverageshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.iostar.beverageshop.R;
import com.iostar.beverageshop.model.Size;

import java.util.List;

public class SizeDetailAdapter extends BaseAdapter {
    private Context context;
    private List<Size> sizeList;
    private int mCheckedPosition = -1;

    public SizeDetailAdapter(Context context, List<Size> sizeList) {
        this.context = context;
        this.sizeList = sizeList;
    }

    @Override
    public int getCount() {
        return sizeList.size();
    }

    @Override
    public Object getItem(int position) {
        return sizeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_size_product, parent, false);
        }

        Size currentItem = (Size) getItem(position);

        RadioButton radioItemSize = convertView.findViewById(R.id.radioSize);
        TextView txtSizeName = convertView.findViewById(R.id.txtSizeName);
        TextView txtPricePlusSize = convertView.findViewById(R.id.txtPricePlusSize);

        txtSizeName.setText(currentItem.getSizeName());
        txtPricePlusSize.setText(currentItem.getPricePlus().toString());

        radioItemSize.setChecked(position == mCheckedPosition);
        radioItemSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckedPosition = position;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}

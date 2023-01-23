package com.mstech.gamesnatcherz.product.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mstech.gamesnatcherz.activities.BaseActivity;
import com.mstech.gamesnatcherz.databinding.ActivityAddressBinding;


public class AddressActivity extends BaseActivity {

    ActivityAddressBinding binding;
    String cartid, from, subtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.includeHeader.tvHeader.setText("Address");
        binding.includeHeader.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        cartid = getIntent().getStringExtra("cartid");
        from = getIntent().getStringExtra("from");
        subtotal = getIntent().getStringExtra("subtotal");
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.address.getText().toString().isEmpty()) {
                    showToast("Enter Address");
                } else if (binding.pincode.getText().toString().length() < 4) {
                    showToast("Enter PostCode");
                } else {
                    startActivity(new Intent(AddressActivity.this, FinalOrderActivity.class).putExtra("address", binding.address.getText().toString()).putExtra("pincode", binding.pincode.getText().toString()).putExtra("cartid", cartid).putExtra("subtotal", subtotal));
                }

            }
        });
    }
}
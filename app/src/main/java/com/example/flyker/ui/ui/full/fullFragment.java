package com.example.flyker.ui.ui.full;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.flyker.R;

public class fullFragment extends Fragment {

    private FullViewModel mViewModel;

    public static fullFragment newInstance() {
        return new fullFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.full_fragment, container, false);
        String url =  fullFragmentArgs.fromBundle(getArguments()).getUrl();
        ImageView imageView = v.findViewById(R.id.fullImage);
        Glide.with(getActivity()).load(url).into(imageView);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FullViewModel.class);
        // TODO: Use the ViewModel
    }

}

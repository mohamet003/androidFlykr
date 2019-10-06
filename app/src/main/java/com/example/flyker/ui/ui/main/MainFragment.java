package com.example.flyker.ui.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.flyker.R;
import com.example.flyker.databinding.MainFragmentBinding;
import com.example.flyker.modele.Photo;
import com.example.flyker.ui.ui.list.ListFragmentDirections;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    MainViewModel mainViewModel;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mainViewModel =  ViewModelProviders.of(this).get(MainViewModel.class);
        MainFragmentBinding mainFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false);
        View view = mainFragmentBinding.getRoot();
        ImageView i = view.findViewById(R.id.singleImage);
        Button next = view.findViewById(R.id.next);
        Button full = view.findViewById(R.id.allImages);

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.versListeFragment);
                System.out.println("Click  on list images");
            }
        });
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Photo photo = mainViewModel.getPhoto().getValue();
                String url = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId()+"_"+photo.getSecret() + ".jpg";
                Navigation.findNavController(v).navigate(MainFragmentDirections.versFullFragment(url));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.nextImage();
            }
        });
        mainViewModel.getPhoto().observe(this,photo -> {

            String url = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId()+"_"+photo.getSecret() + ".jpg";
            mainFragmentBinding.setPhoto(photo);
            Glide.with(getActivity()).load(url).into(i);
            mainFragmentBinding.setLifecycleOwner(this);

        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }


}

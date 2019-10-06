package com.example.flyker.ui.ui.list;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flyker.R;
import com.example.flyker.modele.Photo;
import com.example.flyker.ui.ui.main.MainFragmentDirections;
import com.example.flyker.ui.ui.main.MainViewModel;

import java.util.List;

public class ListFragment extends Fragment {
    List<Photo> photos;
   ListViewModel listViewModel;
    private ListViewModel mViewModel;



    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        listViewModel =  ViewModelProviders.of(this).get(ListViewModel .class);

        View v = inflater.inflate(R.layout.list_fragment, container, false);
        listViewModel.getPhotos().observe(this,photos ->{


            System.out.println("On create listfragment");
            RecyclerView recycler = v.findViewById(R.id.recyclerview);
            recycler.setHasFixedSize(true);
            recycler.setAdapter(new MyAdapter(photos, new MyAdapter.ItemClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Photo photo = photos.get(position);
                    String url = "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId()+"_"+photo.getSecret() + ".jpg";
                    Navigation.findNavController(v).navigate(ListFragmentDirections.versFullFragement(url));

                }
            }));
            recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        });


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        // TODO: Use the ViewModel
    }

}

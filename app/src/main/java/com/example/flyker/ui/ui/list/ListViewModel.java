package com.example.flyker.ui.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flyker.modele.Photo;
import com.example.flyker.modele.Photos;
import com.example.flyker.modele.SearchResult;
import com.example.flyker.repository.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    Repository repository = new Repository();
    public SearchResult photoRe = new SearchResult();
    MutableLiveData<List<Photo>> photos = new MutableLiveData<>();

    public ListViewModel() {

        this.repository.getPhotos(new Callback<SearchResult>() {

            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                photoRe = response.body();
                photos.postValue(photoRe.getPhotos().getPhoto());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                System.out.println("error");
                System.out.println(t);
            }
        });
    }

    public SearchResult getPhotoRe() {
        return photoRe;
    }

    public void setPhotoRe(SearchResult photoRe) {
        this.photoRe = photoRe;
    }

    public MutableLiveData<List<Photo>> getPhotos() {
        return photos;
    }

    public void setPhotos(MutableLiveData<List<Photo>> photos) {
        this.photos = photos;
    }
}

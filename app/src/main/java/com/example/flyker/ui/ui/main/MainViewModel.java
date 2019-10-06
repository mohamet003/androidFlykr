package com.example.flyker.ui.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.flyker.modele.Photo;
import com.example.flyker.modele.Photos;
import com.example.flyker.modele.SearchResult;
import com.example.flyker.repository.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    Repository repository = new Repository();
    Photos photos;
    public SearchResult photoRe = new SearchResult();
    MutableLiveData<Photo> photo = new MutableLiveData<>();
    int indice,size = 0;

    public MainViewModel() {


        this.repository.getPhotos(new Callback<SearchResult>() {

            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                photoRe = response.body();
                photos = photoRe.getPhotos();
                size = photos.getPhoto().size();
                photo.setValue(photos.getPhoto().get(indice));
                indice +=1 ;
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                System.out.println("error");
                System.out.println(t);
            }
        });
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public MutableLiveData<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(MutableLiveData<Photo> photo) {
        this.photo = photo;
    }

    public void nextImage(){
        if (indice < size){
            photo.setValue(photos.getPhoto().get(indice));
            indice += 1;
        }else{
            indice = 0;
            photo.setValue(photos.getPhoto().get(indice));
            indice += 1;
        }



    }
}

package com.hackathon.concord.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hackathon.concord.Model.PetModel;
import com.hackathon.concord.R;

import java.util.ArrayList;
import java.util.List;

public class InfoLiveViewAdapter extends BaseAdapter {

    private TextView txtRegNumber;
    private TextView txtPetName;
    private TextView txtPetDate;
    private TextView txtPetBreed;
    private ImageView imgPet;
    private ImageView imgPetLogo;
    private TextView txtLostFlag;
    private TextView txtLost;
    private View viewLost;
    private ArrayList<PetModel> listItems = new ArrayList<>();


    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(List<PetModel> pets){
        listItems.addAll(pets);
    }

    public void createItem(PetModel pet){listItems.add(pet);}

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final Context context = parent.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.pet_card, parent, false);
        }

        txtRegNumber = view.findViewById(R.id.txtRegNumber);
        txtPetName = view.findViewById(R.id.txtResultPetName);
        txtPetBreed = view.findViewById(R.id.txtPetBreed);
        txtPetDate = view.findViewById(R.id.txtPetDate);
        imgPetLogo = view.findViewById(R.id.imgPetLogo);
        imgPet = view.findViewById(R.id.imgPet);
        txtLost = view.findViewById(R.id.txtLost);
        txtLostFlag = view.findViewById(R.id.txtLostFlag);
        viewLost = view.findViewById(R.id.viewLost);
        PetModel petModel = listItems.get(i);

        txtRegNumber.setText(petModel.getRegisterNumber());
        txtPetDate.setText(petModel.getPetDate());
        txtPetBreed.setText(petModel.getPetBreed());
        txtPetName.setText(petModel.getPetName());
        txtLostFlag.setText(petModel.getPetLost());
        if(petModel.getPetGender().equals("M")){
            imgPetLogo.setImageResource(R.drawable.male);
        }else if(petModel.getPetGender().equals("F")){
            imgPetLogo.setImageResource(R.drawable.female);
        }else{
            imgPetLogo.setImageResource(R.drawable.petlogo);
        }
        imgPetLogo.setScaleType(ImageView.ScaleType.FIT_XY);
        // 이미지뷰의 크기를 동적으로 설정하여 이미지가 화면에 꽉 차게 표시
        Glide.with(view)
                .load("http://172.20.10.7/api/pets/imgload/"+petModel.getRegisterNumber()+"/")
                .apply(RequestOptions.circleCropTransform()) // 원형 이미지 효과
                .into(imgPet);
        if(petModel.getPetLost().equals("Y")){
            txtLost.setVisibility(View.VISIBLE);
            viewLost.setVisibility(View.VISIBLE);
        }else{
            txtLost.setVisibility(View.INVISIBLE);
            viewLost.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}

package com.hackathon.concord.View;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackathon.concord.Model.InfoListItem;
import com.hackathon.concord.Model.PetModel;
import com.hackathon.concord.R;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InfoLiveViewAdapter extends BaseAdapter {

    private TextView txtRegNumber;
    private TextView txtPetWorkDate;
    private TextView txtPetName;
    private TextView txtPetDate;
    private TextView txtPetBreed;
    private ImageView imgPet;
    private ImageView imgPetLogo;

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

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final Context context = parent.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.pet_card, parent, false);
        }

        txtRegNumber = view.findViewById(R.id.txtRegNumber);
        txtPetName = view.findViewById(R.id.txtPetName);
        txtPetBreed = view.findViewById(R.id.txtPetBreed);
        txtPetDate = view.findViewById(R.id.txtPetDate);
        txtPetWorkDate = view.findViewById(R.id.txtWorkDate);
        imgPetLogo = view.findViewById(R.id.imgPetLogo);
        imgPet = view.findViewById(R.id.imgPet);


        final PetModel petModel = listItems.get(i);
        txtRegNumber.setText(petModel.getRegisterNumber());
        txtPetDate.setText(petModel.getPetDate());
        txtPetBreed.setText(petModel.getPetBreed());
        txtPetWorkDate.setText("Test");
        txtPetName.setText(petModel.getPetName());
        if(petModel.getPetGender().equals("M")){
            imgPetLogo.setImageResource(R.drawable.male);
        }else if(petModel.getPetGender().equals("F")){
            imgPetLogo.setImageResource(R.drawable.female);
        }else{
            imgPetLogo.setImageResource(R.drawable.petlogo);
        }
        imgPetLogo.setScaleType(ImageView.ScaleType.FIT_XY);
        String a = "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";
        byte[] byteArray = a.getBytes(StandardCharsets.UTF_8);

        imgPet.setImageBitmap(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));

        // 이미지뷰의 크기를 동적으로 설정하여 이미지가 화면에 꽉 차게 표시

        return view;
    }
}

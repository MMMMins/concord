package com.hackathon.concord.viewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VariousViewModel extends ViewModel {

    private MutableLiveData<String> flagBtn = new MutableLiveData<>();

    public MutableLiveData<String> getFlagBtn() {
        return flagBtn;
    }

    public void setFlagBtn(String date) {
        flagBtn.setValue(date);
    }
}

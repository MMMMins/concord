package com.hackathon.concord.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hackathon.concord.Model.ImageFilePath;
import com.hackathon.concord.Model.PetModel;
import com.hackathon.concord.Model.UserPetInfoModel;
import com.hackathon.concord.R;
import com.hackathon.concord.viewModel.InfoViewModel;
import com.hackathon.concord.viewModel.RegisterViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private static final int REQUEST_PERMISSION = 100;
    private static final int REQUEST_IMAGE_GALLERY = 101;
    private static final int REQUEST_IMAGE_CAMERA = 102;
    private EditText txtPetDate;
    private EditText txtPetName;
    private EditText txtPetBreedInput;
    private RadioGroup sizeChoice;
    private RadioGroup genderChoice;
    private Button btnNext;
    private Button btnImgSelect;
    private ImageView petImage;
    private DatePickerDialog.OnDateSetListener callbackMethod;

    private Spinner breedSelect;
    private String choiceItem;
    private String choiceSize;
    private String choiceGender;
    private Uri imageUri;
    private RegisterViewModel registerViewModel;
    private UserPetInfoModel userPetInfoModel;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("반려견 등록하기");
        Intent intent = getIntent();

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        userPetInfoModel = (UserPetInfoModel) intent.getSerializableExtra("info");

        breedSelect = findViewById(R.id.petBreedSelect);
        txtPetDate = findViewById(R.id.edtRegPetDate);
        txtPetName = findViewById(R.id.edtRegPetName);
        sizeChoice = findViewById(R.id.btnSizeGroup);
        genderChoice = findViewById(R.id.btnGenderGroup);
        btnImgSelect = findViewById(R.id.btnImgSelect);
        btnNext = findViewById(R.id.btnNext);
        btnImgSelect = findViewById(R.id.btnImgSelect);
        petImage = findViewById(R.id.viewPetImg);

        InitializeListener();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array,R.layout.activity_register);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        choiceItem = "품종";
        breedSelect.setOnItemSelectedListener(this);
        btnNext.setOnClickListener(this);
        btnImgSelect.setOnClickListener(this);

        sizeChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.btnSmall:
                        choiceSize = "S";
                        break;
                    case R.id.btnMedium:
                        choiceSize = "M";
                        break;
                    case R.id.btnLarge:
                        choiceSize = "L";
                        break;
                }
            }
        });

        genderChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.btnM:
                        choiceGender = "M";
                        break;
                    case R.id.btnF:
                        choiceGender = "F";
                        break;
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                showImagePickerDialog();
            } else {
                Toast.makeText(this, "권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_GALLERY) {
                if (data != null) {
                    imageUri = data.getData();
                    setImageToImageView();
                }
            } else if (requestCode == REQUEST_IMAGE_CAMERA) {
                setImageToImageView();
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                if(validateFields()){
                    PetModel regPet = new PetModel();
                    regPet.setPetName(txtPetName.getText().toString());
                    regPet.setPetDate(txtPetDate.getText().toString());
                    regPet.setPetGender(choiceGender);
                    regPet.setPetSize(choiceSize);
                    regPet.setPetBreed(txtPetBreedInput.getText().toString());
                    regPet.setRegisterDate(dateCreate());
                    regPet.setRegisterNumber(uidCreate(regPet.getRegisterDate()));
                    regPet.setPetLost("N");

                    String imagePath = ImageFilePath.getPathFromUri(getApplicationContext(), imageUri);
                    registerViewModel.uploadImage(imagePath, userPetInfoModel.getUser().getUser_id(), regPet);

                    registerViewModel.getUploadSuccess().observe(this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){
                                userPetInfoModel.getPets().add(regPet);
                                Intent intent = new Intent();
                                intent.putExtra("request_info", regPet);
                                setResult(AppCompatActivity.RESULT_OK, intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "ERROR: 등록오류 관리자에게 문의하세요", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                break;
            case R.id.btnImgSelect:
                requestPermissions();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txtPetBreedInput = findViewById(R.id.edtRegPetBreed);
        choiceItem = parent.getItemAtPosition(position).toString();
        if(choiceItem.equals("기타")){
            txtPetBreedInput.setEnabled(true);
            txtPetBreedInput.setText("");
        }else{
            txtPetBreedInput.setEnabled(false);
            txtPetBreedInput.setText(choiceItem);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public String uidCreate(String yearMonthDay){

        UUID uuid = UUID.randomUUID();
        String registerNumber = "M" + yearMonthDay + "-" + uuid.toString().substring(0, 8);
        return registerNumber;
    }

    public String dateCreate(){
//        Date now = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        String yearMonthDay = dateFormat.format(now);
//        return yearMonthDay;
        long timestamp = System.currentTimeMillis();
        return String.valueOf(timestamp);
    }

    private boolean validateFields() {
        String petName = txtPetName.getText().toString().trim();
        String petDate = txtPetDate.getText().toString().trim();
        int checkSize = sizeChoice.getCheckedRadioButtonId();
        int checkGender = genderChoice.getCheckedRadioButtonId();
        String selectBreed = (String) breedSelect.getSelectedItem();

        if (petName.isEmpty()) {
            txtPetName.setError("반려견 이름을 입력하세요.");
            return false;
        }

        if (petDate.isEmpty()) {
            txtPetDate.setError("반려견 생일을 선택하세요.");
            return false;
        }

        if (checkGender == -1){
            Toast.makeText(this, "성별을 입력해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (checkSize == -1){
            Toast.makeText(this, "사이즈를 선택해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(selectBreed.equals("품종") || selectBreed.equals("기타") && txtPetBreedInput.getText().toString().isEmpty()){
            txtPetBreedInput.setError("품종을 입력해주세요");
            return false;
        }

        if(imageUri == null){
            Toast.makeText(this, "강아지 이미지를 추가해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        // 더 많은 필드 검사 추가 가능
        return true;
    }

    public void InitializeListener() {
        callbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtPetDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        };
    }

    public void OnClickHandler(View view) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                callbackMethod,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }


    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
        } else {
            showImagePickerDialog();
        }
    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("사진 선택")
                .setMessage("사진을 선택하거나 촬영하세요.")
                .setPositiveButton("갤러리", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openGallery();
                    }
                })
                .setNegativeButton("카메라", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openCamera();
                    }
                })
                .setNeutralButton("취소", null)
                .show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = createImageFile();
            if (photoFile != null) {
                imageUri = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_IMAGE_CAMERA);
            }
        }
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void setImageToImageView() {
        if (imageUri != null) {
            petImage.setImageURI(imageUri);
            petImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}

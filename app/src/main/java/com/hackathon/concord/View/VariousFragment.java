package com.hackathon.concord.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hackathon.concord.Model.TMResponse;
import com.hackathon.concord.Model.UserPetInfoModel;
import com.hackathon.concord.R;
import com.hackathon.concord.viewModel.LoginViewModel;
import com.hackathon.concord.viewModel.VariousViewModel;

public class VariousFragment extends Fragment {

    VariousViewModel variousViewModel;
    private LocationManager locationManager;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 123;
    Location location;
    View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_various, container, false);

        locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            // 권한이 없는 경우 권한 요청
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            variousViewModel = new ViewModelProvider(this).get(VariousViewModel.class);

            LocationListener gpsLocationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    // 위치가 업데이트될 때 호출되는 메서드
                    startLocationUpdates(location);
                }
            };
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, // 위치 공급자 (GPS)
                    1000, // 업데이트 간격 (밀리초)
                    1, // 업데이트 거리 (미터)
                    gpsLocationListener // LocationListener 객체
            );
        }

        return rootView;
    }

    private void startLocationUpdates(Location location) {
        // 권한이 있는 경우 위치 업데이트 요청 등의 작업 수행
        if(location != null) {
            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();
            variousViewModel.convertCoordinates(latitude, longitude);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한 승인 시 위치 업데이트 요청 등의 작업 수행
                startLocationUpdates(location);
            } else {
                // 권한 거부 시 처리
            }
        }
    }
}

package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Provinces;

import java.util.ArrayList;
import java.util.List;

public interface ProvinceServiceImp {
    public ArrayList<Provinces> getAllProvince();
    public boolean insertProvince(Provinces province);

    public Provinces getProvinceById(int id);
}

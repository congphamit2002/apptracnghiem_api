package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService implements ProvinceServiceImp{

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public List<Provinces> getAllProvince() {
        return provinceRepository.findAll();
    }

    @Override
    public boolean insertProvince(Provinces province) {
        try {
            boolean flag = false;

            Provinces temp = provinceRepository.save(province);

            if(temp != null) {
                flag = true;
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Provinces getProvinceById(int id) {
        return provinceRepository.findProvinceById(id);
    }


}

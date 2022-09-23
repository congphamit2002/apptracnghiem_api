package com.app.appthitracnghiem_api.controller;


import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.service.ProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {

    @Autowired
    ProvinceServiceImp provinceServiceImp;

    @GetMapping("/getAllProvince")
    public ResponseEntity<?> getAllProvince(){
        return  new ResponseEntity<List<Provinces>>(provinceServiceImp.getAllProvince(), HttpStatus.OK);
    }

    @PostMapping("/insertProvince")
    public ResponseEntity<?> insertProvince(@RequestParam("provinceName") String provinceName) {
        Provinces province = new Provinces();
        province.setProvinceName(provinceName);
        if(provinceServiceImp.insertProvince(province)) {
            return new ResponseEntity<String>("Insert Province Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<String>("Insert Province Failed", HttpStatus.OK);
    }

    @GetMapping("/getProvince/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable("id") int id) {
        Provinces province = provinceServiceImp.getProvinceById(id);

        if(province != null) {
            return new ResponseEntity<Provinces>(province, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Province ID is invalid", HttpStatus.OK);
    }
}

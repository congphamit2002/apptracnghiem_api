package com.app.appthitracnghiem_api.controller;


import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.entity.Questions;
import com.app.appthitracnghiem_api.helper.ExcelHelper;
import com.app.appthitracnghiem_api.repository.ProvinceRepository;
import com.app.appthitracnghiem_api.service.FileSystemStorageServiceImp;
import com.app.appthitracnghiem_api.service.ProvinceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {

    @Autowired
    ProvinceServiceImp provinceServiceImp;

    @Autowired
    FileSystemStorageServiceImp fileSystemStorageServiceImp;

    @Autowired
    ProvinceRepository provinceRepository;

    @GetMapping("/getAllProvince")
    public ResponseEntity<?> getAllProvince(){
        return  new ResponseEntity<ArrayList<Provinces>>(provinceServiceImp.getAllProvince(), HttpStatus.OK);
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

    @PostMapping("/saveFile")
    public ResponseEntity<?> saveFileFullTest(
            @RequestParam MultipartFile fileExcel) {

        try {

            if(!fileExcel.isEmpty()) {
                try {
                    if(ExcelHelper.hasExcelFormat(fileExcel)) {

                        ArrayList<Provinces> listData = new ArrayList<Provinces>();
                        listData = ExcelHelper.excelToProvinces(fileExcel.getInputStream());
                        provinceRepository.saveAll(listData);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            return new ResponseEntity<String>("OKE", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("FAIL  ", HttpStatus.OK);
        }
    }
}

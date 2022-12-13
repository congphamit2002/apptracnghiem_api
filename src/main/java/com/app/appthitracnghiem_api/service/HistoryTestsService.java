package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.HistoryTests;
import com.app.appthitracnghiem_api.repository.HistoryTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HistoryTestsService implements HistoryTestsServiceImp{

    @Autowired
    HistoryTestsRepository historyTestsRepository;

    @Override
    public ArrayList<Map<String, ?>> getHistoryTestByAccountIDandQGrDetailID(int accountID) {
        try {
            if(historyTestsRepository.getHistoryTestByAccountIDandQGrDetailID(accountID) != null) {
                return historyTestsRepository.getHistoryTestByAccountIDandQGrDetailID(accountID);
            }
            return null;
        } catch (Exception e) {
            return  null;
        }
    }

    @Override
    public boolean saveHistoryTest(HistoryTests historyTests) {
        if(historyTestsRepository.save(historyTests) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteHistoryTest(int id) {
        try {
            historyTestsRepository.deleteById(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

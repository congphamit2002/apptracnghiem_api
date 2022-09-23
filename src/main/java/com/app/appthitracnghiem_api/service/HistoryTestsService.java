package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.repository.HistoryTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HistoryTestsService implements HistoryTestsServiceImp{

    @Autowired
    HistoryTestsRepository historyTestsRepository;

    @Override
    public List<Map<String, ?>> getHistoryTestByAccountIDandQGrDetailID(int accountID, int qgroupDetailID) {
        try {
            if(historyTestsRepository.getHistoryTestByAccountIDandQGrDetailID(accountID,qgroupDetailID) != null) {
                return historyTestsRepository.getHistoryTestByAccountIDandQGrDetailID(accountID,qgroupDetailID);
            }
            return null;
        } catch (Exception e) {
            return  null;
        }
    }
}

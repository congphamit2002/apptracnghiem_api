package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.HistoryTests;

import java.util.List;
import java.util.Map;

public interface HistoryTestsServiceImp {
    public List<Map<String, ?>> getHistoryTestByAccountIDandQGrDetailID(int accountID, int qgroupDetailID);
    public boolean saveHistoryTest(HistoryTests historyTests);
}

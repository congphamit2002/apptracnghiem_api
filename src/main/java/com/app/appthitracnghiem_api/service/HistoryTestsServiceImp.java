package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.HistoryTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface HistoryTestsServiceImp {
    public ArrayList<Map<String, ?>> getHistoryTestByAccountIDandQGrDetailID(int accountID);
    public boolean saveHistoryTest(HistoryTests historyTests);
    public void deleteHistoryTest(int id);
}

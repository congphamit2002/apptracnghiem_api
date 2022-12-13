package com.app.appthitracnghiem_api.helper;

import com.app.appthitracnghiem_api.entity.Provinces;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.entity.Questions;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static ArrayList<Questions> excelToQuestions(InputStream is, int questionGDetailID) {

        ArrayList<Questions> listQuestions = new ArrayList<Questions>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);

                Questions questions = new Questions();

                if (row.getCell(0) != null) {
                    questions.setNum((int) row.getCell(0).getNumericCellValue());
                }


                if (row.getCell(1) != null) {
                    questions.setQuestion(row.getCell(1).getStringCellValue());
                }


                if (row.getCell(2) != null) {
                    questions.setOption1(row.getCell(2).getStringCellValue());
                }

                if (row.getCell(3) != null) {
                    questions.setOption2(row.getCell(3).getStringCellValue());
                }

                if (row.getCell(4) != null) {
                    questions.setOption3(row.getCell(4).getStringCellValue());
                }

                if (row.getCell(5) != null) {
                    questions.setOption4(row.getCell(5).getStringCellValue());
                }

                if (row.getCell(6) != null) {
                    questions.setCorrectAnswer(row.getCell(6).getStringCellValue());
                }
                QuestionGroupsDetail questionGroupsDetail = new QuestionGroupsDetail();
                questionGroupsDetail.setId(questionGDetailID);
                questions.setQuestionGroupsDetail(questionGroupsDetail);
                listQuestions.add(questions);
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("ERROR IMPORT LIST DATA QUESTION");
        }
        return listQuestions;
    }

    public static ArrayList<Provinces> excelToProvinces(InputStream is) {

        ArrayList<Provinces> listProvinces = new ArrayList<Provinces>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);

                Provinces provinces = new Provinces();

                if (row.getCell(0) != null) {
                    provinces.setProvinceName(row.getCell(0).getStringCellValue());
                }
                listProvinces.add(provinces);
            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RuntimeException("ERROR IMPORT LIST DATA QUESTION");
        }
        return listProvinces;
    }
}

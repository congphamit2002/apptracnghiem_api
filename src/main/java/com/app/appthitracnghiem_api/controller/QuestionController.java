package com.app.appthitracnghiem_api.controller;

import com.app.appthitracnghiem_api.common.Constant;
import com.app.appthitracnghiem_api.entity.QuestionGroups;
import com.app.appthitracnghiem_api.entity.QuestionGroupsDetail;
import com.app.appthitracnghiem_api.entity.Questions;
import com.app.appthitracnghiem_api.pojo.PreviewQuestionImage;
import com.app.appthitracnghiem_api.pojo.QuestionGroupDetailPojo;
import com.app.appthitracnghiem_api.pojo.QuestionPojo;
import com.app.appthitracnghiem_api.repository.QuestionGroupsDetailRepository;
import com.app.appthitracnghiem_api.service.FileSystemStorageServiceImp;
import com.app.appthitracnghiem_api.service.QuestionGroupsDetailServiceImp;
import com.app.appthitracnghiem_api.service.QuestionServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    QuestionServiceImp questionServiceImp;

    @Autowired
    QuestionGroupsDetailServiceImp questionGroupsDetailServiceImp;

    @Autowired
    FileSystemStorageServiceImp fileSystemStorageServiceImp;

    @Autowired
    QuestionGroupsDetailRepository questionGroupsDetailRepository;

    @GetMapping("/getQGrDetailByQGrId/{id}")
    public ResponseEntity<?> getQGrDetailByQGrId(@PathVariable("id") int id) {

        try {
            Gson gson = new Gson();
            String data =  gson.toJson(questionGroupsDetailServiceImp.getAllQGrDetailByQGrId(id));

            ObjectMapper mapper = new ObjectMapper();
            QuestionGroupDetailPojo[] listData = mapper.readValue(data, QuestionGroupDetailPojo[].class);
            return new ResponseEntity<QuestionGroupDetailPojo[]>(listData, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>("Question Group Detail is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getQGrDetailById/{id}")
    public ResponseEntity<?> getQGrDetailById(@PathVariable("id") int id) {

        try {
            QuestionGroupsDetail groupsDetail = questionGroupsDetailServiceImp.getQGrDetailById(id);
            QuestionGroupDetailPojo questionGroupDetailPojo = new QuestionGroupDetailPojo();
            questionGroupDetailPojo.setId(groupsDetail.getId());
            questionGroupDetailPojo.setNumber_question(groupsDetail.getNumberQuestions());
            questionGroupDetailPojo.setLink_excel(groupsDetail.getLinkExcel());
            questionGroupDetailPojo.setTime(groupsDetail.getTime());
            questionGroupDetailPojo.setDescription(groupsDetail.getDescription());
            questionGroupDetailPojo.setName_gr_detail(groupsDetail.getNameGrDetail());


            return new ResponseEntity<>(questionGroupDetailPojo, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>("Question Group Detail ID is invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/questionDetailID/{id}")
    public ResponseEntity<?> getAllQuestionByQuestionGroupsDetailId (@PathVariable("id") int id) {

        try {
            ArrayList<Map<String, ?>> list = questionServiceImp.getAllQuestionByQuestionGroupsDetailId(id);
            Gson gson = new Gson();
            String data = gson.toJson(list);
            ObjectMapper mapper = new ObjectMapper();
            QuestionPojo[] listQuestion = mapper.readValue(data, QuestionPojo[].class);
            return new ResponseEntity<QuestionPojo[]>(listQuestion, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>("Question Groups Detail Id is invalid", HttpStatus.BAD_REQUEST);
        }

    }


    //get All file preview image of listen test
	@GetMapping("/previewImage/{id}")
	public ResponseEntity<?> getAllPreviewQuestionImage(@PathVariable("id") int id, HttpServletRequest httpServletRequest) {

		ArrayList<Map<String, ?>> listData =  questionGroupsDetailServiceImp.getAllPreviewImageByQGeDId(id);
		ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
		String data = gson.toJson(listData);

		try {
			PreviewQuestionImage[] listenImageQuestion = mapper.readValue(data, PreviewQuestionImage[].class);
            ArrayList<PreviewQuestionImage> listResult = new ArrayList<>();
			for(PreviewQuestionImage image : listenImageQuestion) {
                if(Objects.nonNull(image)) {  //check object null
                    if(!image.getImage().trim().equals("")) {
                        String imagePath = "http://" + httpServletRequest.getServerName() + ":"
                                + httpServletRequest.getServerPort() + "/api/file/questionImages/" + image.getImage();
                        PreviewQuestionImage previewQuestionImage = new PreviewQuestionImage();
                        previewQuestionImage.setImage(imagePath);
                        listResult.add(previewQuestionImage);
                    }
                }

			}
			return new ResponseEntity<>(listResult, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
	}



    @PostMapping("/saveFile")
    public ResponseEntity<?> saveFileQuestions(
            @RequestParam("grDetailName") String grDetailName,
            @RequestParam("grDetailCount") int grDetailCount,
            @RequestParam("grDetailTime") int grDetailTime,
            @RequestParam("grDetailDescription") String grDetaillDescription,
            @RequestParam("qgrId") int qgrId,
            @RequestParam("questionFileExcel") MultipartFile questionFileExcel,
            @RequestParam("questionImage") MultipartFile[] questionImage) {

       try {
           fileSystemStorageServiceImp.init(Constant.questionExcel);
           fileSystemStorageServiceImp.init(Constant.questionImages);

           QuestionGroupsDetail questionGroupsDetail = new QuestionGroupsDetail();
           questionGroupsDetail.setNumberQuestions(grDetailCount);
           questionGroupsDetail.setTime(grDetailTime);
           questionGroupsDetail.setDescription(grDetaillDescription);
           questionGroupsDetail.setNameGrDetail(grDetailName);
           questionGroupsDetail.setLinkExcel(questionFileExcel.getOriginalFilename());
           QuestionGroups questionGroups = new QuestionGroups();
           questionGroups.setId(qgrId);
           questionGroupsDetail.setQuestionGroups(questionGroups);
           QuestionGroupsDetail temp = questionGroupsDetailServiceImp.insertQGrDetail(questionGroupsDetail);

           if(!questionFileExcel.isEmpty()) {
               fileSystemStorageServiceImp.save(questionFileExcel, Constant.questionExcel);

           }
            if(questionImage.length > 0) {
                for(MultipartFile file : questionImage) {
                    if(!file.isEmpty()) {
                        fileSystemStorageServiceImp.save(file, Constant.questionImages);

                    }
                }
            }
           questionServiceImp.saveFileExcel(temp.getId(), questionFileExcel);

           return new ResponseEntity<String>("OKE", HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<String>("FAIL  ", HttpStatus.OK);
       }
    }

    @PostMapping("/updateFile")
    public ResponseEntity<?> updateFileQuestions( @RequestParam("grDetailId") int grDetailId,
                                                  @RequestParam("grDetailName") String grDetailName,
                                                  @RequestParam("grDetailCount") int grDetailCount,
                                                  @RequestParam("grDetailTime") int grDetailTime,
                                                  @RequestParam("grDetailDescription") String grDetailDescription,
                                                  @RequestParam("qgrId") int qgrId,
                                                  @RequestParam("questionFileExcel") MultipartFile questionFileExcel,
                                                  @RequestParam("questionImage") MultipartFile[] questionImage) {

        try {
            System.out.println("\t\t" + grDetailName);
            System.out.println("\t\t" + grDetailCount);
            System.out.println("\t\t" + grDetailTime);
            System.out.println("\t\t" + grDetailDescription);
            fileSystemStorageServiceImp.init(Constant.questionExcel);
            fileSystemStorageServiceImp.init(Constant.questionImages);

            QuestionGroupsDetail questionGroupsDetail = new QuestionGroupsDetail();
            questionGroupsDetail.setId(grDetailId);
            questionGroupsDetail.setNumberQuestions(grDetailCount);
            questionGroupsDetail.setTime(grDetailTime);
            questionGroupsDetail.setDescription(grDetailDescription);
            questionGroupsDetail.setNameGrDetail(grDetailName);
            questionGroupsDetail.setLinkExcel(questionFileExcel.getOriginalFilename());
            QuestionGroups questionGroups = new QuestionGroups();
            questionGroups.setId(qgrId);
            questionGroupsDetail.setQuestionGroups(questionGroups);
            if(questionGroupsDetailServiceImp.updateQGrDetail(questionGroupsDetail)) {
                if(!questionFileExcel.isEmpty()) {
                    fileSystemStorageServiceImp.save(questionFileExcel, Constant.questionExcel);
                    try {
                        if(!questionServiceImp.updateFileExcel(grDetailId, questionFileExcel
                                )) {
                            return new ResponseEntity<>("Failed update files", HttpStatus.BAD_REQUEST);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                if(questionImage.length > 0) {
                    for(MultipartFile file : questionImage) {
                        if(!file.isEmpty()) {
                            fileSystemStorageServiceImp.save(file, Constant.questionImages);

                        }
                    }
                }

                return new ResponseEntity<String>("OKE", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed update files", HttpStatus.BAD_REQUEST);

            }


        } catch (Exception e) {
            return new ResponseEntity<>("Failed update files", HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/deleteByQGrDId/{qGrDId}")
    public ResponseEntity<?> deleteByQGrDId(@PathVariable("qGrDId") int qGrDId) {
        try {
                questionServiceImp.deleteQuestionByQGrDetailId(qGrDId);
                questionGroupsDetailServiceImp.deleteQGrDetailById(qGrDId);
                return new ResponseEntity<>("DELETE QUESTION SUCCESSFULLY", HttpStatus.OK );
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DELETE QUESTION FAILED", HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<?> test(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(questionGroupsDetailServiceImp.getAllByQGrId(id), HttpStatus.OK );
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DELETE QUESTION FAILED", HttpStatus.BAD_REQUEST );
        }
    }

}

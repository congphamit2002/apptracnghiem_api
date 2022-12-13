package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Comments;
import com.app.appthitracnghiem_api.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class CommentService implements CommentServiceImp{
    @Autowired
    CommentsRepository commentsRepository;
    @Override
    public ArrayList<Map<String,?>> getAllCmtByQGrDetailId(int id) {
        return commentsRepository.getAllCmtByQGrDetailId(id);
    }

    @Override
    public boolean insertComment(Comments comments) {
        try {
            boolean flag = false;
            if(commentsRepository.save(comments) != null) {
                flag = true;
            }
            return flag;
        }catch (Exception e) {
            return false;
        }
    }
}

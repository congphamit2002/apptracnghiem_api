package com.app.appthitracnghiem_api.service;

import com.app.appthitracnghiem_api.entity.Comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CommentServiceImp {
    public ArrayList<Map<String , ?>> getAllCmtByQGrDetailId(int id);
    public boolean insertComment(Comments comments);
}

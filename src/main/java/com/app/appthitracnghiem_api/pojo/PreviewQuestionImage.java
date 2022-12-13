package com.app.appthitracnghiem_api.pojo;

public class PreviewQuestionImage {
    private String image = "";

    public String getImage() {
        if(!image.isEmpty() && !image.equals("")) {
            return image;
        } else  {
           return  "";
        }
    }

    public void setImage(String image) {
        this.image = image;
    }
}

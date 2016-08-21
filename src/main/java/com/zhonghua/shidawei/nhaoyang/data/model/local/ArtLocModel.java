package com.zhonghua.shidawei.nhaoyang.data.model.local;

import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;
import com.zhonghua.shidawei.nhaoyang.data.model.ArtWrapper;
import com.zhonghua.shidawei.nhaoyang.data.model.UserModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shidawei on 16/8/20.
 */
public class ArtLocModel {

    Integer _id; Integer artid ;String title;String content;Integer userId;String userName;String userImage;String time;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getArtid() {
        return artid;
    }

    public void setArtid(Integer artid) {
        this.artid = artid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static ArtWrapper getArtWrapper(List<ArtLocModel> artLocModels){
        ArtWrapper artWrapper = new ArtWrapper();
        List<ArtModel> artModels = new ArrayList<>();
        for(ArtLocModel artLocModel:artLocModels){
            artModels.add(getArtModel(artLocModel));
        }
        artWrapper.setData(artModels);
        artWrapper.setState(0);
        return artWrapper;
    }
    public static ArtModel getArtModel(ArtLocModel artLocModel){
        ArtModel artModel = new ArtModel();
        artModel.setId(artLocModel.getArtid());
        artModel.setContent(artLocModel.getContent());
        artModel.setTime(new Date(Long.parseLong(artLocModel.getTime())));
        artModel.setTitle(artLocModel.getTitle());
        UserModel userModel = new UserModel();
        userModel.setId(artLocModel.getUserId());
        userModel.setUsername(artLocModel.getUserName());
        userModel.setUserimage(artLocModel.getUserImage());
        artModel.setUser(userModel);
        return artModel;
    }
}

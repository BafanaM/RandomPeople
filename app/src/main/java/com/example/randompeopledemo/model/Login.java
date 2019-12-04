package com.example.randompeopledemo.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("uuid")
    public String uuid;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;
    @SerializedName("salt")
    public String salt;
    @SerializedName("md5")
    public String md5;
    @SerializedName("sha1")
    public String sha1;
    @SerializedName("sha256")
    public String sha256;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }
}

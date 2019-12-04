package com.example.randompeopledemo.model;

import com.google.gson.annotations.SerializedName;

public class Timezone {
    @SerializedName("offset")
    public String offset;
    @SerializedName("description")
    public String description;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

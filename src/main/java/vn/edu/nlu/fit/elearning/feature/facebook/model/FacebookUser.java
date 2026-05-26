package vn.edu.nlu.fit.elearning.feature.facebook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FacebookUser implements Serializable {

    @SerializedName("id")
    private String id;

//    @SerializedName("name")
//    private String name;

    @SerializedName("first_name")
    private String firstName; // Tự động map từ first_name

    @SerializedName("last_name")
    private String lastName;   // Tự động map từ last_name

    @SerializedName("email")
    private String email;

    @SerializedName("picture")
    private Picture picture;

    public static class Picture {
        private Data data;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    public static class Data {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public String getAvatar() {
        if (picture != null &&
                picture.getData() != null) {
            return picture.getData().getUrl();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "FacebookUser{" +
                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture=" + picture +
                '}';
    }
}
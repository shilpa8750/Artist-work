package com.shilpa.hindishayri;

public class Model {
    String title;
    int image;

    // Blank Constructor
    public Model() {
    }

    // Constructor
    public Model(String title, int image) {
        this.title = title;
        this.image = image;
    }

    // Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

package com.gmail.etozhesergius.android_course;

class Contact{
    private final String name;
    private final String phoneNumber;
    private final int image;

    Contact(String name, String phoneNumber, int image){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;

    }
    int getImage() { return image; }
    String getName() {
            return name;
    }
    String getPhoneNumber() {
        return phoneNumber;
    }
}

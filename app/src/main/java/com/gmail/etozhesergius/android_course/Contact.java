package com.gmail.etozhesergius.android_course;

class Contact{
    private String name;
    private String phoneNumber;

    Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;

    }
    String getName() {
            return name;
    }
    String getPhoneNumber() {
        return phoneNumber;
    }
}

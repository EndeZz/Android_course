package com.gmail.etozhesergius.android_course;

class Contact{
    private final String name;
    private final String phoneNumber;
    private final int image;

    static final Contact[] contacts = {
            new Contact("Roman Romanov", "+8-800-55-35-35", R.mipmap.ic_launcher_round),
            new Contact("Тот самый", "112", R.mipmap.ic_launcher_round),
            new Contact("Мышка", "65465766786", R.mipmap.ic_launcher_round),
            new Contact("Крыска", "11212", R.mipmap.ic_launcher_round),
    };

    private Contact(String name, String phoneNumber, int image){
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

package com.example.aftercheckout.model;

public class user_info {
    String phone,address,locality,pincode;

    public String getPhone() {
        return phone;
    }

    public user_info() {
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public user_info(String phone, String address, String locality, String pincode) {
        this.phone = phone;
        this.address = address;
        this.locality = locality;
        this.pincode = pincode;
    }
}

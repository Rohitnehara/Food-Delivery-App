package com.example.project.Domain;

public class userAddress {
  private   String phone,address,locality,pincode;
    public userAddress() {

    }

    public userAddress(String phone, String address, String locality, String pincode) {
        this.phone = phone;
        this.address = address;
        this.locality = locality;
        this.pincode = pincode;
    }




    public String getPhone() {
        return phone;
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


}

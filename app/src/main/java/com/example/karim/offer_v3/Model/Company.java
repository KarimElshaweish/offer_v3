package com.example.karim.offer_v3.Model;

import android.widget.EditText;

/**
 * Created by Karim on 8/18/2018.
 */

public class Company {
    String companyName;
    String personalName;
    String email;
    String tel;
    String mobile;
    String desierdUserName;
    String companyLogo;

    public String GetCategory() {
        return category;
    }

    public void SetCategory(String category) {
        this.category = category;
    }

    String category;

    public String GetCompanyName() {
        return companyName;
    }

    public void SetCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String GetPersonalName() {
        return personalName;
    }

    public void SetPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    public String GetTel() {
        return tel;
    }

    public void SetTel(String tel) {
        this.tel = tel;
    }

    public String GetMobile() {
        return mobile;
    }

    public void SetMobile(String mobile) {
        this.mobile = mobile;
    }

    public String GetDesierdUserName() {
        return desierdUserName;
    }

    public void SetDesierdUserName(String desierdUserName) {
        this.desierdUserName = desierdUserName;
    }

    public String GetCompanyLogo() {
        return companyLogo;
    }

    public void SetCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }
}

package web_api.models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class RequestModels {
    private String back;
    private String email;
    private String password;
    private String submitLogin;
    private String idGender;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String psgdpr;
    private String submitCreate;
    @JsonProperty("id_product")
    private String idProduct;
    private String rewrite;
    private String controller;
    private String token;
    @JsonProperty("id_customization")
    private String idCustomization;
    private String qty;
    private String add;
    private String action;
    private String invalidEmail;
    private String invalidPassword;
}

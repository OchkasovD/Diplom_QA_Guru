package web_api.data;

import web_api.models.RequestModels;


public class ApiModels {

    public static RequestModels apiAutorizationRequestModels(TestData testData) {
        RequestModels formParam = new RequestModels();
        formParam.setBack(testData.back);
        formParam.setEmail(testData.email);
        formParam.setPassword(testData.password);
        formParam.setSubmitLogin(testData.submitLogin);
        return formParam;
    }

    public static RequestModels apiUnsuccessfulAutorizationRequestModels(TestData testData) {
        RequestModels formParam = new RequestModels();
        formParam.setBack(testData.back);
        formParam.setInvalidEmail(testData.invalidEmail);
        formParam.setInvalidPassword(testData.invalidPassword);
        formParam.setSubmitLogin(testData.submitLogin);
        return formParam;
    }

    public static RequestModels apiSuccessfulRegistrationRequestModels(TestData testData) {
        RequestModels formParam = new RequestModels();
        formParam.setIdGender(testData.genderId);
        formParam.setFirstName(testData.firstName);
        formParam.setLastName(testData.lastName);
        formParam.setEmail(testData.createEmail);
        formParam.setPassword(testData.passwordCreate);
        formParam.setBirthDay(testData.birthDay);
        formParam.setPsgdpr(testData.psgdpr);
        formParam.setSubmitCreate(testData.submitCreate);
        return formParam;
    }

    public static RequestModels apiUnsuccessfulRegistrationRequestModels(TestData testData) {
        RequestModels formParam = new RequestModels();
        formParam.setFirstName(testData.firstName);
        formParam.setLastName(testData.lastName);
        formParam.setEmail(testData.email);
        formParam.setPassword(testData.passwordCreate);
        formParam.setPsgdpr(testData.psgdpr);
        formParam.setSubmitCreate(testData.submitCreate);
        return formParam;
    }

    public static RequestModels apiAddToCartRequestModels(TestData testData) {
        RequestModels formParam = new RequestModels();
        formParam.setController(testData.controller);
        formParam.setToken(testData.token);
        formParam.setIdProduct(testData.id_product);
        formParam.setIdCustomization(testData.id_customization);
        formParam.setQty(testData.qty);
        formParam.setAdd(testData.add);
        formParam.setAction(testData.action);
        return formParam;
    }
}


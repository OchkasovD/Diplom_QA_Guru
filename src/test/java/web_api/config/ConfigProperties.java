package web_api.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/app.properties"
})
public interface ConfigProperties extends Config {

    @Key("login")
    String getLogin();

    @Config.Key("password")
    String getPassword();

    @Key("invalidLogin")
    String getInvalidLogin();

    @Key("invalidPassword")
    String getInvalidPassword();
}
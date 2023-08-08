package web_api.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/app.properties"
})
public interface RemoteConfig extends Config{

    @Config.Key("remoteUrl")
    String remoteURL();

    @Config.Key("loginRemote")
    String loginRemote();

    @Config.Key("passwordRemote")
    String passwordRemote();

}
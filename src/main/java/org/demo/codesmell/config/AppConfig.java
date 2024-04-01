package org.demo.codesmell.config;

import lombok.extern.log4j.Log4j2;

import javax.naming.Context;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class AppConfig {

    public static final String APP_PASSWORD = "appPassword";

    public static final String Password = "password";

    public static final String ENV = "SIT";

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("username", "name");
        map.put("password", "pass");
        map.put("userPwd", "pass");
        map.put(Context.SECURITY_CREDENTIALS, "abc");
        log.info(map);
        if (!map.get("username").equals("N")) {
            System.exit(1);
        }
    }
}

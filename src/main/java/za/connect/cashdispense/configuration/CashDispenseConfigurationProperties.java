package za.connect.cashdispense.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration("properties")
public class CashDispenseConfigurationProperties {

    private String allowedOrigin;


    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }
}

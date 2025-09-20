package br.com.cactusdigital.cedrus.infrastructure.handler.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("app")
public class ApiProperties {
    private String version;
}


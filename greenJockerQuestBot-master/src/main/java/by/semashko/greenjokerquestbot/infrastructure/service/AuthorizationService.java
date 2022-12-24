package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;

import java.io.IOException;

public interface AuthorizationService {

    AuthorizationResponse authorization(String domain, String message) throws IOException;

}

package by.semashko.greenjokerquestbot.service;

import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;

import java.io.IOException;

public interface AuthorizationService {

    AuthorizationResponse authorization(String domain, String message) throws IOException;

}

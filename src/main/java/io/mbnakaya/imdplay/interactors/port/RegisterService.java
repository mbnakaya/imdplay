package io.mbnakaya.imdplay.interactors.port;

import io.mbnakaya.imdplay.domain.User;

public interface RegisterService {
    User registry(User user);

    User getUser(String authToken);
}

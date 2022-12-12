package io.mbnakaya.imdplay.interactors.port;

import io.mbnakaya.imdplay.domain.Login;

public interface LoginService {
    Login authenticate(Login login);
}

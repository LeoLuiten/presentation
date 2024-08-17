package leoluiten.presentation.services;

import leoluiten.presentation.dtos.login.Credential;
import leoluiten.presentation.dtos.login.CredentialV2;
import leoluiten.presentation.models.Player;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing log in requests. Provides methods for
 * executing log in requests.
 */
@Service
public interface LoginService {

    Player login(Credential credential);

    Player login(CredentialV2 credentialV2);
}

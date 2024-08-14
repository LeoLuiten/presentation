package leoluiten.presentation.services.impl;

import leoluiten.presentation.dtos.login.*;
import leoluiten.presentation.models.Player;
import leoluiten.presentation.services.LoginService;
import leoluiten.presentation.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PlayerService playerService;

    @Override
    public Player login(Credential credential) {
        if(credential.getIdentity() instanceof UserNameIdentity) {
            return loginWithIdentity((UserNameIdentity) credential.getIdentity(), credential.getPassword());
        } else {
            return loginWithIdentity((EmailIdentity) credential.getIdentity(), credential.getPassword());
        }
    }

    @Override
    public Player login(CredentialV2 credential) {
        return  playerService.getPlayerByUserNameOrEmailAndPassword(credential.getIdentity(), credential.getPassword());
    }

    private Player loginWithIdentity(UserNameIdentity userNameIdentity, String password) {
        return playerService.getPlayerByUserNameAndPassword(userNameIdentity.getUsername(), password);
    }

    private Player loginWithIdentity(EmailIdentity emailIdentity, String password) {
        return playerService.getPlayerByEmailAndPassword(emailIdentity.getEmail(), password);
    }
}

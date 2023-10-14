package com.example.apisecurity.service;

import com.example.apisecurity.data.PasswordRecovery;
import com.example.apisecurity.data.Token;
import com.example.apisecurity.data.User;
import com.example.apisecurity.data.UserRepo;
import com.example.apisecurity.exception.BadCredentialError;
import com.example.apisecurity.exception.EmailNotFoundError;
import com.example.apisecurity.exception.PasswordDoNotMatchError;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MailService mailService;
    private static final String ACCESS_SECRET = "gaD-DmPUJCNdgbHsR1vM0GoKE0TtXQDxAUdaSYEOC7g";
    private static final String REFRESH_SECRET = "gaD-DmPUJCNdgbHsR1vM0GoKE0TtXQDxAUdaSYEOC7g";
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public User register(String firstName, String lastName, String email, String password, String passwordConfirm) {
        if (!Objects.equals(password, passwordConfirm)) {
            throw new PasswordDoNotMatchError();
        }
        return userRepo.save(
                new User(null, firstName, lastName, email, passwordEncoder.encode(password))
        );
    }

    @Transactional
    public boolean logout(String refreshToken){
        var jwt = Jwt.from(refreshToken, REFRESH_SECRET);
        User user = userRepo.findById(jwt.getUserId()).orElseThrow(EntityNotFoundException::new);
        user.removeToken(refreshToken);
        Set<Token> tokens = user.removeToken(refreshToken);
        user.setTokens(tokens);
        userRepo.save(user);
        return true;
    }
    public Login refreshAccess(String refreshToken) {
        var refreshJwt = Jwt.from(refreshToken, REFRESH_SECRET);
        var user = userRepo.findUserIdAndTokenByRefreshToken(
                refreshJwt.getUserId(),
                refreshJwt.getToken(),
                refreshJwt.getExpiredAt()
        ).orElseThrow(EntityNotFoundException::new);
        return Login.of(user.getId(), ACCESS_SECRET, REFRESH_SECRET);
    }

    public User getUserFromToken(String token) {
        return userRepo.findById(Jwt.from(token, ACCESS_SECRET).getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found!"));
    }

    public Login login(String email, String password) {
        var user = userRepo.findByEmail(email)
                .orElseThrow(EmailNotFoundError::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialError();
        }

        var login = Login.of(user.getId(), ACCESS_SECRET, REFRESH_SECRET);
        var refreshToken = login.getRefreshToken();

        user.addToken(new Token(
                refreshToken.getToken(),
                refreshToken.getIssuedAt(),
                refreshToken.getExpiredAt()
        ));
        userRepo.save(user);
        return login;
    }

    @Transactional
    public void forgetPassword(String email,String url) {
        var uuid = UUID.randomUUID().toString().replace("-", "");
        User user = userRepo.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        PasswordRecovery passwordRecovery = new PasswordRecovery();
        passwordRecovery.setToken(uuid);
        user.addPasswordRecovery(passwordRecovery);
        userRepo.save(user);
        mailService.sendMail(email, url, uuid);
    }

    @Transactional
    public void resetPassword(String token, String password, String confirmPassword){
        if(!Objects.equals(password,confirmPassword)){
            throw new PasswordDoNotMatchError();
        }
        User user = userRepo.findUserByPasswordRecoveriesToken(token)
                .orElseThrow(EntityNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password));
        Set<PasswordRecovery> passwordRecoveries = user.removePasswordRecovery(token);
        userRepo.save(user);
        //user.removePasswordRecovery(token);
    }
}

package muni.pa165.rest.controllers;

import muni.pa165.api.dto.UserAuthenticateDTO;
import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.rest.config.JwtTokenUtil;
import muni.pa165.rest.models.GenericResponse;
import muni.pa165.rest.models.JWTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserFacade userFacade;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(UserAuthenticateDTO userAuthenticateDTO) {
        try {
            if (!userFacade.authenticate(userAuthenticateDTO)) {
                throw new BadCredentialsException("Email or password invalid");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userAuthenticateDTO.getEmail(), userAuthenticateDTO.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            UserDTO user = userFacade.findUserByEmail(userAuthenticateDTO.getEmail());
            String token = new JwtTokenUtil().generateToken(user);

            return ResponseEntity.ok(new JWTResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Not a valid user"));
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        UserDTO user = userFacade.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring("Bearer ".length());
        String email = jwtTokenUtil.getEmailFromToken(token);

        UserDTO user = userFacade.findUserByEmail(email);
        if (user.getEmail().equals(email)) {
            String newToken = new JwtTokenUtil().generateToken(user);
            return ResponseEntity.ok(new JWTResponse(newToken));
        }

        return ResponseEntity.badRequest().body("invalid info provided");
    }
}

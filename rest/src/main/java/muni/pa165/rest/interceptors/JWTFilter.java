package muni.pa165.rest.interceptors;

import muni.pa165.api.dto.UserDTO;
import muni.pa165.api.dto.UserResponseDTO;
import muni.pa165.api.facade.UserFacade;
import muni.pa165.persistence.enums.UserType;
import muni.pa165.rest.config.JwtTokenUtil;
import muni.pa165.rest.config.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class JWTFilter  extends OncePerRequestFilter {
    @Autowired
    UserFacade userFacade;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.substring("Bearer ".length());

        if (!jwtTokenUtil.validateToken(token)) {
            chain.doFilter(request, response);
            return;
        }

        UserResponseDTO userDTO = userFacade.findUserByEmail(jwtTokenUtil.getEmailFromToken(token));
        UserDetails userDetails = null;

        if (!userDTO.getEmail().isEmpty()){
            List<Roles> roles = userDTO.getType() == UserType.MANAGER ?  List.of(new Roles(Roles.MANAGER),new Roles(Roles.TENNIS_USER)) : List.of(new Roles(Roles.TENNIS_USER));
            userDetails = new User(userDTO.getEmail(),userDTO.getPassword(), roles);
        }

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDTO.getId(), userDetails,
                        userDetails == null ?
                                List.of() : userDetails.getAuthorities()
                );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}

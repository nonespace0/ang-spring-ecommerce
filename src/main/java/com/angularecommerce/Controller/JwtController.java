package com.angularecommerce.Controller;

import com.angularecommerce.Model.JwtRequest;
import com.angularecommerce.Model.JwtResponse;
import com.angularecommerce.Service.CustomUserDetailService;
import com.angularecommerce.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class JwtController {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @RequestMapping(value="/token", method= RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword())
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        String token =this.jwtUtil.generateToken(jwtRequest.getUserName());
        System.out.println("JWT" + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

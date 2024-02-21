package com.openXcell.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openXcell.model.JwtResponse;
import com.openXcell.model.LoginRequest;
import com.openXcell.model.Product;
import com.openXcell.model.User;
import com.openXcell.model.UserDetailsImpl;
import com.openXcell.services.ProductService;
import com.openXcell.services.UserServices;
import com.openXcell.utility.JwtUtils;

@RestController
public class MainController {
	

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserServices userServices;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		 userServices.saveUser(user);
	return ResponseEntity.ok().body("saved successfully");	
	}
	
	
	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			System.out.println("Jayessh its working");
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);
			
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
			return ResponseEntity.ok(new JwtResponse(jwt, 
													 userDetails.getUserId(), 
													 userDetails.getUsername(), 
													 userDetails.getEmail() 
													 ));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.badRequest().body("sign in failed");
		}
		
	}
	
	
//	@PostMapping("/oauth/logout")
//	public ResponseEntity<String> revoke(HttpServletRequest request) {
//	    try {
//	        String authorization = request.getHeader("Authorization");
//	        if (authorization != null && authorization.contains("Bearer")) {
//	            String tokenValue = authorization.replace("Bearer", "").trim();
//	        }
//	    } catch (Exception e) {
//	        return ResponseEntity.badRequest().body("Invalid access token");
//	    }
//
//	    return ResponseEntity.ok().body("Access token invalidated successfully");
//	}
	
	@PostMapping("/createProduct")
	public ResponseEntity<String> saveUser(@RequestBody Product product) {
		productService.saveProduct(product);
	return ResponseEntity.ok().body("saved successfully");	
	}
	
	
	@GetMapping("/getProduct/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		 Optional<Product>  list = productService.getProductsById(id);
	return ResponseEntity.ok().body(list);	
	}
	

}

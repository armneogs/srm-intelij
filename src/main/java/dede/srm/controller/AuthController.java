package dede.srm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dede.srm.models.enums.ERole;
import dede.srm.models.UserRole;
import dede.srm.models.Log;
import dede.srm.models.Token;
import dede.srm.models.User;
import dede.srm.payload.request.LoginRequest;
import dede.srm.payload.request.SignupRequest;
import dede.srm.payload.response.JwtResponse;
import dede.srm.payload.response.MessageResponse;
import dede.srm.repo.interf.UserRoleRepo;
import dede.srm.repo.interf.UserRepo;
import dede.srm.repo.interf.TokenRepo;
import dede.srm.repo.interf.LogRepo;
import dede.srm.security.jwt.JwtUtils;
import dede.srm.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepo userRepository;

	@Autowired
	TokenRepo tokenRepo;

	@Autowired
	UserRoleRepo roleRepository;

	@Autowired
	LogRepo logRepo;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		Date nowDate = new Date();

		Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

		if (userOpt.isEmpty()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username not found"));
		}

		User userM = userOpt.get();
		Map<String, Object> claims = new LinkedHashMap<String, Object>();

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		claims.put("userId", userM.getId());
		claims.put("name", userM.getName());
		claims.put("email", userM.getEmail());
		claims.put("roles", roles);
		String jwt = jwtUtils.generateJwtToken(authentication, nowDate, claims);

		List<Token> existTokens = tokenRepo.findByUserId(userOpt.get().getId());

		tokenRepo.deleteAll(existTokens);

		Log log = new Log();
		log.setLogType("1");
		log.setUser(userOpt.get());
		log.setDecription("User logins to system.");
		log.setCreateDate(nowDate);

		logRepo.save(log);

		Token token = new Token();

		token.setJwtHash(jwt);
		token.setUser(userOpt.get());
		token.setCreateDate(nowDate);
		token.setExpireDate(new Date(nowDate.getTime() + jwtUtils.getJwtExpirationMs()));

		tokenRepo.save(token);

		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {

		String bearer = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getHeader("Authorization");
		String jwt = bearer.substring(7);

		if (!jwtUtils.validateJwtToken(jwt)) {
			return ResponseEntity.ok(new MessageResponse("Token's invalid."));
		}

		String username = jwtUtils.getUserNameFromJwtToken(jwt);
		List<Token> tokens = tokenRepo.findByUserNameAndJWT(username, jwt);
		if (tokens == null || tokens.isEmpty()) {
			return ResponseEntity.ok(new MessageResponse("User's already exited."));
		}

		for (Token existToken : tokens) {
			tokenRepo.delete(existToken);
		}

		Optional<User> userOpt = userRepository.findByUsername(username);
		Date nowDate = new Date();

		Log log = new Log();

		log.setLogType("1");
		log.setUser(userOpt.get());
		log.setDecription("User logout.");
		log.setCreateDate(nowDate);
		logRepo.save(log);

		return ResponseEntity.ok(new MessageResponse("logout successfully!"));
	}

	private void deleteAllValidToken(Long userId) {
		List<Token> tokens = tokenRepo.findByUserId(userId);
		if (tokens != null && !tokens.isEmpty()) {
			for (Token existToken : tokens) {
				tokenRepo.delete(existToken);
			}
		}
	}

	@PostMapping("/signup")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		String name = signUpRequest.getName();
		if (name == null || name.isEmpty()) {
			throw new RuntimeException("Error: Name is not speify.");
		}

		// Create new user's account
		User user = new User();

		user.setName(signUpRequest.getName());
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setEmail(signUpRequest.getEmail());

		Set<String> strRoles = signUpRequest.getRole();
		List<UserRole> roles = new ArrayList<>();

		if (strRoles != null) {
			if (strRoles.isEmpty()) {
				throw new RuntimeException("Error: Role is not speify.");
			}

			try {
				strRoles.stream().forEach(roleName -> {
					switch (roleName) {
						case "admin" -> {
							UserRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);
						}
						case "mod" -> {
							UserRole modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);
						}
						default -> {
							UserRole userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
						}
					}
				});
				user.setUserRole(roles.get(0));

				Date nowDate = new Date();
				user.setCreateDate(nowDate);
				user.setUpdateDate(nowDate);

				userRepository.save(user);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

//			UserRole userRole = roleRepository.findById(ERole.ROLE_USER);

		} else {
			throw new RuntimeException("Error: Role is not speify.");
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}

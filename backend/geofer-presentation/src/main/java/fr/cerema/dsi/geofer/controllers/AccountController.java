package fr.cerema.dsi.geofer.controllers;

import fr.cerema.dsi.geofer.dto.UserWithCurrentAuthorityDTO;
import fr.cerema.dsi.geofer.entities.Profil;
import fr.cerema.dsi.geofer.entities.User;
import fr.cerema.dsi.geofer.mapper.UserMapper;
import fr.cerema.dsi.geofer.services.ProfilService;
import fr.cerema.dsi.geofer.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;
import java.util.Optional;

@Tag(name = "Accounts", description = "Récupère les informations des utilisateurs")
@RestController
@RequestMapping("/api")
@CrossOrigin
@SecurityRequirement(name = "ORION_AUTH")

public class AccountController {

    private final UserService userService;
    private final ProfilService profilService;
    private final UserMapper userMapper;

    public AccountController(UserService userService, UserMapper userMapper, ProfilService profilService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.profilService = profilService;
    }


    @GetMapping("/user")
    public ResponseEntity<UserWithCurrentAuthorityDTO> getCurrentUser() {
        Optional<User> userOptional = this.userService.getCurrentUser();
        if (userOptional.isPresent()) {
            // Utilisateur existant
            return new ResponseEntity<>(userMapper.toDtoWithCurrentAuthority(userOptional.get()), HttpStatus.OK);
        } else {
            // Utilisateur GUEST
            User guestUser = new User(); // Créez un nouveau User pour GUEST
            guestUser.setEmail("guest@cerema.fr"); 
            guestUser.setProfilSet(new HashSet<>()); // Crée un ensemble vide, car un invité n'a pas de profils
            
            // Convertissez cet utilisateur guest en DTO. Assurez-vous que cette méthode dans votre mapper peut gérer un utilisateur sans profils.
            UserWithCurrentAuthorityDTO guestUserDTO = userMapper.toDtoWithCurrentAuthority(guestUser);
            return new ResponseEntity<>(guestUserDTO, HttpStatus.OK);
        }
    }
}

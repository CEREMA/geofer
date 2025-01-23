package fr.cerema.dsi.geofer.mapper;

import fr.cerema.dsi.geofer.dto.UserDTO;
import fr.cerema.dsi.geofer.dto.UserWithCurrentAuthorityDTO;
import fr.cerema.dsi.geofer.entities.User;
import fr.cerema.dsi.geofer.entities.Role;
import fr.cerema.dsi.geofer.services.ProfilService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {})
public abstract class UserMapper {

    @Autowired
    private ProfilService profilService;

    @Named("User")
    public abstract UserDTO toDto(User user);

    @Mapping(target = "profilSet", ignore = true)
    public abstract UserDTO toDtoWithoutProfilSet(User user);

    public abstract User toEntity(UserDTO userDTO);

    public abstract UserWithCurrentAuthorityDTO toDtoWithCurrentAuthorityBase(User user);

    public UserWithCurrentAuthorityDTO toDtoWithCurrentAuthority(User user) {

        UserWithCurrentAuthorityDTO dto = toDtoWithCurrentAuthorityBase(user);
        
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            // Définir le rôle pour un utilisateur GUEST
            Role guestRole = profilService.findRoleByName("GUEST"); 
            dto.setRoleActifList(Collections.singletonList(guestRole.getName())); 
        } else {
            // Pour un utilisateur normal, utilisez les rôles retournés par le service
            List<Role> roles = profilService.findAllCurrentRolesByUsername(user.getEmail());
            // Transformez les objets Role en String (noms de rôles) pour la liste
            List<String> roleNames = roles.stream()
                                          .map(Role::getName)
                                          .collect(Collectors.toList());
            dto.setRoleActifList(roleNames);
        }
        return dto;
    }
    
}

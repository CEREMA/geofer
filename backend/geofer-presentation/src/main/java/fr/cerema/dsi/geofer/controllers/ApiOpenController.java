package fr.cerema.dsi.geofer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import fr.cerema.dsi.geofer.dto.UserDTO;
import fr.cerema.dsi.geofer.entities.User;
import fr.cerema.dsi.geofer.mapper.UserMapper;
import fr.cerema.dsi.geofer.services.UserService;

@Tag(name = "API open", description = "Permet d'interroger et de parcourir les API sans authentification")
@RestController
@RequestMapping("/api-open")
@CrossOrigin
public class ApiOpenController {

  private final Logger log = LoggerFactory.getLogger(ApiOpenController.class);

  private final UserService userService;

  private final UserMapper userMapper;

  public ApiOpenController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @Operation(summary = "Test si l'application est online",
    description =
      "Permet d'obtenir le statut de l'application")
  @ApiResponse(responseCode = "200", description = "Le statut de l'application", content = {
    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
      array = @ArraySchema(schema = @Schema(implementation = User.class))
    )}
  )

  @GetMapping("/alive")
  @ResponseBody
  public boolean checkAlive() {
    return true;
  }

}

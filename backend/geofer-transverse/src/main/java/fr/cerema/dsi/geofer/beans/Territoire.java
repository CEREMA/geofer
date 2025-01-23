package fr.cerema.dsi.geofer.beans;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Territoire {
  private String nomCommune;

  private String inseeCommune;

  private String inseeDepartement;
}

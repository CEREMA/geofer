package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class GaresByCommuneDTO {
    private Integer id;
    private String nomCommune;
    private String inseeCommune;
    private String inseeDepartement;
    private Integer codeUic;
    private String nomGare;
    private Boolean siOuverte;
    private String nomAom;
    private Boolean siAutomatesTgvIntercites;
    private Boolean siAutomatesTer;
    private Boolean siPosteVenteGuichet;
    private Boolean siLibreServiceAssiste;
    private Object theGeom;
}
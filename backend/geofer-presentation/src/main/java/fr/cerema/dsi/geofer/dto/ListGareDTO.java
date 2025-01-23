package fr.cerema.dsi.geofer.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ListGareDTO {
    private Integer codeUic;
    private Integer gareId;
    private String inseeCommune;
    private String inseeDepartement;
    private String nomAom;
    private String nomCommune;
    private String nomGare;
    private Boolean siAutomatesTer;
    private Boolean siAutomatesTgvIntercites;
    private Boolean siLibreServiceAssiste;
    private Boolean siOuverte;
    private Boolean siPosteVenteGuichet;
    private Integer annee;
    private Integer nombreArret;
    private Integer refTypeArretId;
}
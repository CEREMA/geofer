package fr.cerema.dsi.geofer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cerema.dsi.geofer.entities.Communes;
import java.time.LocalDate;;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

public class CommunesDTO implements Serializable {

  private static final long serialVersionUID = 1L;  

  @Getter
  @Setter
  @JsonProperty("acaCode")
  private String acaCode;
 
  @Getter
  @Setter
  @JsonProperty("acaId")
  private String acaId;
 
  @Getter
  @Setter
  @JsonProperty("acaNom")
  private String acaNom;
 
  @Getter
  @Setter
  @JsonProperty("acaPaysage")
  private String acaPaysage;
 
  @Getter
  @Setter
  @JsonProperty("acaWikidata")
  private String acaWikidata;
 
  @Getter
  @Setter
  @JsonProperty("atlasId")
  private String atlasId;
 
  @Getter
  @Setter
  @JsonProperty("atlasNom")
  private String atlasNom;
 
  @Getter
  @Setter
  @JsonProperty("atlasPaysage")
  private String atlasPaysage;
 
  @Getter
  @Setter
  @JsonProperty("atlasWikidata")
  private String atlasWikidata;
 
  @Getter
  @Setter
  @JsonProperty("auCode")
  private String auCode;
 
  @Getter
  @Setter
  @JsonProperty("auId")
  private String auId;
 
  @Getter
  @Setter
  @JsonProperty("aucId")
  private String aucId;
 
  @Getter
  @Setter
  @JsonProperty("aucNom")
  private String aucNom;
 
  @Getter
  @Setter
  @JsonProperty("comCode")
  private String comCode;
 
  @Getter
  @Setter
  @JsonProperty("comCode1")
  private String comCode1;
 
  @Getter
  @Setter
  @JsonProperty("comCode2")
  private String comCode2;
 
  @Getter
  @Setter
  @JsonProperty("comCodeActuel")
  private String comCodeActuel;
 
  @Getter
  @Setter
  @JsonProperty("comCodeActuelDepuis")
  private LocalDate comCodeActuelDepuis;
 
  @Getter
  @Setter
  @JsonProperty("comId")
  private String comId;
 
  @Getter
  @Setter
  @JsonProperty("comNom")
  private String comNom;
 
  @Getter
  @Setter
  @JsonProperty("comNomAnciens")
  private String comNomAnciens;
 
  @Getter
  @Setter
  @JsonProperty("comNomAnciensDates")
  private String comNomAnciensDates;
 
  @Getter
  @Setter
  @JsonProperty("comNomDebut")
  private LocalDate comNomDebut;
 
  @Getter
  @Setter
  @JsonProperty("comNomFin")
  private LocalDate comNomFin;
 
  @Getter
  @Setter
  @JsonProperty("comNomMaj")
  private String comNomMaj;
 
  @Getter
  @Setter
  @JsonProperty("comNomMajCourt")
  private String comNomMajCourt;
 
  @Getter
  @Setter
  @JsonProperty("depCode")
  private String depCode;
 
  @Getter
  @Setter
  @JsonProperty("depId")
  private String depId;
 
  @Getter
  @Setter
  @JsonProperty("depNom")
  private String depNom;
 
  @Getter
  @Setter
  @JsonProperty("depNomNum")
  private String depNomNum;
 
  @Getter
  @Setter
  @JsonProperty("depNumNom")
  private String depNumNom;
 
  @Getter
  @Setter
  @JsonProperty("depPaysage")
  private String depPaysage;
 
  @Getter
  @Setter
  @JsonProperty("depWikidata")
  private String depWikidata;
 
  @Getter
  @Setter
  @JsonProperty("epciId")
  private String epciId;
 
  @Getter
  @Setter
  @JsonProperty("epciNom")
  private String epciNom;
 
  @Getter
  @Setter
  @JsonProperty("fdId")
  private String fdId;
 
  @Getter
  @Setter
  @JsonProperty("feId")
  private String feId;
 
  @Getter
  @Setter
  @JsonProperty("frId")
  private String frId;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("geom")
  private Geometry geom;
 
  @Getter
  @Setter
  @JsonSerialize(using = GeometrySerializer.class)
  @JsonDeserialize(using = GeometryDeserializer.class)
  @JsonProperty("geometry")
  private Geometry geometry;
 
  @Getter
  @Setter
  @JsonProperty("id")
  private Integer id;
 
  @Getter
  @Setter
  @JsonProperty("nutsCode1")
  private String nutsCode1;
 
  @Getter
  @Setter
  @JsonProperty("nutsCode3")
  private String nutsCode3;
 
  @Getter
  @Setter
  @JsonProperty("regCode")
  private String regCode;
 
  @Getter
  @Setter
  @JsonProperty("regCodeOld")
  private String regCodeOld;
 
  @Getter
  @Setter
  @JsonProperty("regId")
  private String regId;
 
  @Getter
  @Setter
  @JsonProperty("regIdOld")
  private String regIdOld;
 
  @Getter
  @Setter
  @JsonProperty("regNom")
  private String regNom;
 
  @Getter
  @Setter
  @JsonProperty("regNomOld")
  private String regNomOld;
 
  @Getter
  @Setter
  @JsonProperty("regPaysage")
  private String regPaysage;
 
  @Getter
  @Setter
  @JsonProperty("regWikidata")
  private String regWikidata;
 
  @Getter
  @Setter
  @JsonProperty("regrgpNom")
  private String regrgpNom;
 
  @Getter
  @Setter
  @JsonProperty("uuCode")
  private String uuCode;
 
  @Getter
  @Setter
  @JsonProperty("uuId")
  private String uuId;
 
  @Getter
  @Setter
  @JsonProperty("uuId10")
  private String uuId10;
 
  @Getter
  @Setter
  @JsonProperty("uuId99")
  private String uuId99;
 
  @Getter
  @Setter
  @JsonProperty("uuPaysage")
  private String uuPaysage;
 
  @Getter
  @Setter
  @JsonProperty("uuWikidata")
  private String uuWikidata;
 
  @Getter
  @Setter
  @JsonProperty("uucrId")
  private String uucrId;
 
  @Getter
  @Setter
  @JsonProperty("uucrNom")
  private String uucrNom;
 
  @Getter
  @Setter
  @JsonProperty("zeId")
  private String zeId;
 

  // Default constructor
  public CommunesDTO() {}

  // Constructor with arguments
  public CommunesDTO(String acaCode, String acaId, String acaNom, String acaPaysage, String acaWikidata, String atlasId, String atlasNom, String atlasPaysage, String atlasWikidata, String auCode, String auId, String aucId, String aucNom, String comCode, String comCode1, String comCode2, String comCodeActuel, LocalDate comCodeActuelDepuis, String comId, String comNom, String comNomAnciens, String comNomAnciensDates, LocalDate comNomDebut, LocalDate comNomFin, String comNomMaj, String comNomMajCourt, String depCode, String depId, String depNom, String depNomNum, String depNumNom, String depPaysage, String depWikidata, String epciId, String epciNom, String fdId, String feId, String frId, Geometry geom, Geometry geometry, Integer id, String nutsCode1, String nutsCode3, String regCode, String regCodeOld, String regId, String regIdOld, String regNom, String regNomOld, String regPaysage, String regWikidata, String regrgpNom, String uuCode, String uuId, String uuId10, String uuId99, String uuPaysage, String uuWikidata, String uucrId, String uucrNom, String zeId) {
    this.acaCode = acaCode;
    this.acaId = acaId;
    this.acaNom = acaNom;
    this.acaPaysage = acaPaysage;
    this.acaWikidata = acaWikidata;
    this.atlasId = atlasId;
    this.atlasNom = atlasNom;
    this.atlasPaysage = atlasPaysage;
    this.atlasWikidata = atlasWikidata;
    this.auCode = auCode;
    this.auId = auId;
    this.aucId = aucId;
    this.aucNom = aucNom;
    this.comCode = comCode;
    this.comCode1 = comCode1;
    this.comCode2 = comCode2;
    this.comCodeActuel = comCodeActuel;
    this.comCodeActuelDepuis = comCodeActuelDepuis;
    this.comId = comId;
    this.comNom = comNom;
    this.comNomAnciens = comNomAnciens;
    this.comNomAnciensDates = comNomAnciensDates;
    this.comNomDebut = comNomDebut;
    this.comNomFin = comNomFin;
    this.comNomMaj = comNomMaj;
    this.comNomMajCourt = comNomMajCourt;
    this.depCode = depCode;
    this.depId = depId;
    this.depNom = depNom;
    this.depNomNum = depNomNum;
    this.depNumNom = depNumNom;
    this.depPaysage = depPaysage;
    this.depWikidata = depWikidata;
    this.epciId = epciId;
    this.epciNom = epciNom;
    this.fdId = fdId;
    this.feId = feId;
    this.frId = frId;
    this.geom = geom;
    this.geometry = geometry;
    this.id = id;
    this.nutsCode1 = nutsCode1;
    this.nutsCode3 = nutsCode3;
    this.regCode = regCode;
    this.regCodeOld = regCodeOld;
    this.regId = regId;
    this.regIdOld = regIdOld;
    this.regNom = regNom;
    this.regNomOld = regNomOld;
    this.regPaysage = regPaysage;
    this.regWikidata = regWikidata;
    this.regrgpNom = regrgpNom;
    this.uuCode = uuCode;
    this.uuId = uuId;
    this.uuId10 = uuId10;
    this.uuId99 = uuId99;
    this.uuPaysage = uuPaysage;
    this.uuWikidata = uuWikidata;
    this.uucrId = uucrId;
    this.uucrNom = uucrNom;
    this.zeId = zeId;
  }

  // setters & getters
  public String getAcaCode() {
    return acaCode;
  }
 
  public void setAcaCode(String acaCode) {
    this.acaCode = acaCode;
  }
 
  public String getAcaId() {
    return acaId;
  }
 
  public void setAcaId(String acaId) {
    this.acaId = acaId;
  }
 
  public String getAcaNom() {
    return acaNom;
  }
 
  public void setAcaNom(String acaNom) {
    this.acaNom = acaNom;
  }
 
  public String getAcaPaysage() {
    return acaPaysage;
  }
 
  public void setAcaPaysage(String acaPaysage) {
    this.acaPaysage = acaPaysage;
  }
 
  public String getAcaWikidata() {
    return acaWikidata;
  }
 
  public void setAcaWikidata(String acaWikidata) {
    this.acaWikidata = acaWikidata;
  }
 
  public String getAtlasId() {
    return atlasId;
  }
 
  public void setAtlasId(String atlasId) {
    this.atlasId = atlasId;
  }
 
  public String getAtlasNom() {
    return atlasNom;
  }
 
  public void setAtlasNom(String atlasNom) {
    this.atlasNom = atlasNom;
  }
 
  public String getAtlasPaysage() {
    return atlasPaysage;
  }
 
  public void setAtlasPaysage(String atlasPaysage) {
    this.atlasPaysage = atlasPaysage;
  }
 
  public String getAtlasWikidata() {
    return atlasWikidata;
  }
 
  public void setAtlasWikidata(String atlasWikidata) {
    this.atlasWikidata = atlasWikidata;
  }
 
  public String getAuCode() {
    return auCode;
  }
 
  public void setAuCode(String auCode) {
    this.auCode = auCode;
  }
 
  public String getAuId() {
    return auId;
  }
 
  public void setAuId(String auId) {
    this.auId = auId;
  }
 
  public String getAucId() {
    return aucId;
  }
 
  public void setAucId(String aucId) {
    this.aucId = aucId;
  }
 
  public String getAucNom() {
    return aucNom;
  }
 
  public void setAucNom(String aucNom) {
    this.aucNom = aucNom;
  }
 
  public String getComCode() {
    return comCode;
  }
 
  public void setComCode(String comCode) {
    this.comCode = comCode;
  }
 
  public String getComCode1() {
    return comCode1;
  }
 
  public void setComCode1(String comCode1) {
    this.comCode1 = comCode1;
  }
 
  public String getComCode2() {
    return comCode2;
  }
 
  public void setComCode2(String comCode2) {
    this.comCode2 = comCode2;
  }
 
  public String getComCodeActuel() {
    return comCodeActuel;
  }
 
  public void setComCodeActuel(String comCodeActuel) {
    this.comCodeActuel = comCodeActuel;
  }
 
  public LocalDate getComCodeActuelDepuis() {
    return comCodeActuelDepuis;
  }
 
  public void setComCodeActuelDepuis(LocalDate comCodeActuelDepuis) {
    this.comCodeActuelDepuis = comCodeActuelDepuis;
  }
 
  public String getComId() {
    return comId;
  }
 
  public void setComId(String comId) {
    this.comId = comId;
  }
 
  public String getComNom() {
    return comNom;
  }
 
  public void setComNom(String comNom) {
    this.comNom = comNom;
  }
 
  public String getComNomAnciens() {
    return comNomAnciens;
  }
 
  public void setComNomAnciens(String comNomAnciens) {
    this.comNomAnciens = comNomAnciens;
  }
 
  public String getComNomAnciensDates() {
    return comNomAnciensDates;
  }
 
  public void setComNomAnciensDates(String comNomAnciensDates) {
    this.comNomAnciensDates = comNomAnciensDates;
  }
 
  public LocalDate getComNomDebut() {
    return comNomDebut;
  }
 
  public void setComNomDebut(LocalDate comNomDebut) {
    this.comNomDebut = comNomDebut;
  }
 
  public LocalDate getComNomFin() {
    return comNomFin;
  }
 
  public void setComNomFin(LocalDate comNomFin) {
    this.comNomFin = comNomFin;
  }
 
  public String getComNomMaj() {
    return comNomMaj;
  }
 
  public void setComNomMaj(String comNomMaj) {
    this.comNomMaj = comNomMaj;
  }
 
  public String getComNomMajCourt() {
    return comNomMajCourt;
  }
 
  public void setComNomMajCourt(String comNomMajCourt) {
    this.comNomMajCourt = comNomMajCourt;
  }
 
  public String getDepCode() {
    return depCode;
  }
 
  public void setDepCode(String depCode) {
    this.depCode = depCode;
  }
 
  public String getDepId() {
    return depId;
  }
 
  public void setDepId(String depId) {
    this.depId = depId;
  }
 
  public String getDepNom() {
    return depNom;
  }
 
  public void setDepNom(String depNom) {
    this.depNom = depNom;
  }
 
  public String getDepNomNum() {
    return depNomNum;
  }
 
  public void setDepNomNum(String depNomNum) {
    this.depNomNum = depNomNum;
  }
 
  public String getDepNumNom() {
    return depNumNom;
  }
 
  public void setDepNumNom(String depNumNom) {
    this.depNumNom = depNumNom;
  }
 
  public String getDepPaysage() {
    return depPaysage;
  }
 
  public void setDepPaysage(String depPaysage) {
    this.depPaysage = depPaysage;
  }
 
  public String getDepWikidata() {
    return depWikidata;
  }
 
  public void setDepWikidata(String depWikidata) {
    this.depWikidata = depWikidata;
  }
 
  public String getEpciId() {
    return epciId;
  }
 
  public void setEpciId(String epciId) {
    this.epciId = epciId;
  }
 
  public String getEpciNom() {
    return epciNom;
  }
 
  public void setEpciNom(String epciNom) {
    this.epciNom = epciNom;
  }
 
  public String getFdId() {
    return fdId;
  }
 
  public void setFdId(String fdId) {
    this.fdId = fdId;
  }
 
  public String getFeId() {
    return feId;
  }
 
  public void setFeId(String feId) {
    this.feId = feId;
  }
 
  public String getFrId() {
    return frId;
  }
 
  public void setFrId(String frId) {
    this.frId = frId;
  }
 
  public Geometry getGeom() {
    return geom;
  }
 
  public void setGeom(Geometry geom) {
    this.geom = geom;
  }
 
  public Geometry getGeometry() {
    return geometry;
  }
 
  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }
 
  public Integer getId() {
    return id;
  }
 
  public void setId(Integer id) {
    this.id = id;
  }
 
  public String getNutsCode1() {
    return nutsCode1;
  }
 
  public void setNutsCode1(String nutsCode1) {
    this.nutsCode1 = nutsCode1;
  }
 
  public String getNutsCode3() {
    return nutsCode3;
  }
 
  public void setNutsCode3(String nutsCode3) {
    this.nutsCode3 = nutsCode3;
  }
 
  public String getRegCode() {
    return regCode;
  }
 
  public void setRegCode(String regCode) {
    this.regCode = regCode;
  }
 
  public String getRegCodeOld() {
    return regCodeOld;
  }
 
  public void setRegCodeOld(String regCodeOld) {
    this.regCodeOld = regCodeOld;
  }
 
  public String getRegId() {
    return regId;
  }
 
  public void setRegId(String regId) {
    this.regId = regId;
  }
 
  public String getRegIdOld() {
    return regIdOld;
  }
 
  public void setRegIdOld(String regIdOld) {
    this.regIdOld = regIdOld;
  }
 
  public String getRegNom() {
    return regNom;
  }
 
  public void setRegNom(String regNom) {
    this.regNom = regNom;
  }
 
  public String getRegNomOld() {
    return regNomOld;
  }
 
  public void setRegNomOld(String regNomOld) {
    this.regNomOld = regNomOld;
  }
 
  public String getRegPaysage() {
    return regPaysage;
  }
 
  public void setRegPaysage(String regPaysage) {
    this.regPaysage = regPaysage;
  }
 
  public String getRegWikidata() {
    return regWikidata;
  }
 
  public void setRegWikidata(String regWikidata) {
    this.regWikidata = regWikidata;
  }
 
  public String getRegrgpNom() {
    return regrgpNom;
  }
 
  public void setRegrgpNom(String regrgpNom) {
    this.regrgpNom = regrgpNom;
  }
 
  public String getUuCode() {
    return uuCode;
  }
 
  public void setUuCode(String uuCode) {
    this.uuCode = uuCode;
  }
 
  public String getUuId() {
    return uuId;
  }
 
  public void setUuId(String uuId) {
    this.uuId = uuId;
  }
 
  public String getUuId10() {
    return uuId10;
  }
 
  public void setUuId10(String uuId10) {
    this.uuId10 = uuId10;
  }
 
  public String getUuId99() {
    return uuId99;
  }
 
  public void setUuId99(String uuId99) {
    this.uuId99 = uuId99;
  }
 
  public String getUuPaysage() {
    return uuPaysage;
  }
 
  public void setUuPaysage(String uuPaysage) {
    this.uuPaysage = uuPaysage;
  }
 
  public String getUuWikidata() {
    return uuWikidata;
  }
 
  public void setUuWikidata(String uuWikidata) {
    this.uuWikidata = uuWikidata;
  }
 
  public String getUucrId() {
    return uucrId;
  }
 
  public void setUucrId(String uucrId) {
    this.uucrId = uucrId;
  }
 
  public String getUucrNom() {
    return uucrNom;
  }
 
  public void setUucrNom(String uucrNom) {
    this.uucrNom = uucrNom;
  }
 
  public String getZeId() {
    return zeId;
  }
 
  public void setZeId(String zeId) {
    this.zeId = zeId;
  }
 
}
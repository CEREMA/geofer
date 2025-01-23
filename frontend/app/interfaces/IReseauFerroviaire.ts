// Interface pour la classe ReseauFerroviaire
interface IReseauFerroviaire {
  codeLigne?: string;
  id: number;
  idTypologiePetiteLigne?: number;
  infrastructure?: string;
  mnemo?: string;
  pkDebutR?: string;
  pkFinR?: string;
  retenuePetiteLigne?: string;
  rgTroncon?: string;
  theGeom?: any;
}

export default IReseauFerroviaire;
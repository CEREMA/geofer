// Import des types nécessaires
import IRefTypeEtablissement from './IRefTypeEtablissement';

// Interface pour la classe EtablissementScolaire
interface IEtablissementScolaire {
  annee?: number;
  id: number;
  nbEleve?: number;
  nomEtablissement?: string;
  refTypeEtablissementId: number;
  refTypeEtablissement: IRefTypeEtablissement;
  theGeom?: any;
}

export default IEtablissementScolaire;
// Import des types nécessaires
import IRefClassePassageNiveau from './IRefClassePassageNiveau';

// Interface pour la classe PassageNiveau
interface IPassageNiveau {
  codeLigne?: string;
  id: number;
  idPn?: number;
  libelle?: string;
  obstacle?: string;
  refClassePassageNiveauId?: number;
  refClassePassageNiveau?: IRefClassePassageNiveau;
  theGeom?: any;
}

export default IPassageNiveau;
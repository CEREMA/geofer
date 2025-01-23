// Import des types nécessaires
import IGare from './IGare';
import ILigne from './ILigne';

// Interface pour la classe LigneGare
interface ILigneGare {
  gareId: number;
  gare: IGare;
  ligneId: number;
  ligne: ILigne;
}

export default ILigneGare;
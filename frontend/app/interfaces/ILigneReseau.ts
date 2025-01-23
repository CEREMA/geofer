// Import des types nécessaires
import ILigne from './ILigne';
import IReseauFerroviaire from './IReseauFerroviaire';

// Interface pour la classe LigneReseau
interface ILigneReseau {
  ligneId: number;
  ligne: ILigne;
  reseauId: number;
  reseauFerroviaire: IReseauFerroviaire;
}

export default ILigneReseau;
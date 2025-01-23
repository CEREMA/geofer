// Import des types nécessaires
import IGare from './IGare';

// Interface pour la classe DonneeVoyageur
interface IDonneeVoyageur {
  annee: number;
  gareId: number;
  gare: IGare;
  id: number;
  nombreVoyageur?: number;
}

export default IDonneeVoyageur;
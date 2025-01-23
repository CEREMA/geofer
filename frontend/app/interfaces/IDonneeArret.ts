// Import des types nécessaires
import IGare from './IGare';
import IRefTypeArret from './IRefTypeArret';

// Interface pour la classe DonneeArret
interface IDonneeArret {
  annee: number;
  gareId: number;
  gare: IGare;
  id: number;
  nombreArret?: number;
  refTypeArretId: number;
  refTypeArret: IRefTypeArret;
}

export default IDonneeArret;
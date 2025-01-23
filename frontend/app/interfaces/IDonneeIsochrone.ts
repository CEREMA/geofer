// Import des types nécessaires
import IIsochrone from './IIsochrone';
import IRefTypeDonnee from './IRefTypeDonnee';

// Interface pour la classe DonneeIsochrone
interface IDonneeIsochrone {
  annee: number;
  id: number;
  isochroneId: number;
  isochrone: IIsochrone;
  nombre?: number;
  refTypeDonneeId: number;
  refTypeDonnee: IRefTypeDonnee;
}

export default IDonneeIsochrone;
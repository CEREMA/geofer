// Import des types nécessaires
import IGare from './IGare';
import IRefTypeIsochrone from './IRefTypeIsochrone';

// Interface pour la classe Isochrone
interface IIsochrone {
  gareId: number;
  gare: IGare;
  id: number;
  refTypeIsochroneId: number;
  refTypeIsochrone: IRefTypeIsochrone;
  theGeom?: any;
}

export default IIsochrone;
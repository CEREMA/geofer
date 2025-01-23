// Import des types nécessaires
import IRefActivitePrincipale from './IRefActivitePrincipale';

// Interface pour la classe InstallationTerm
interface IInstallationTerm {
  anneeArretTrain?: any;
  codeIte: number;
  dateEnquete?: any;
  entrepriseFerroviaire?: string;
  etatIte?: any;
  evolutionDepuis2010?: any;
  evolutionFuture?: any;
  frequenceCirculationTrain?: any;
  id: number;
  locoTracteurRail?: any;
  locoTracteurRailRoute?: any;
  longueurVoie?: number;
  nombreVoies?: number;
  raisonSociale?: string;
  refActivitePrincipaleId?: number;
  refActivitePrincipale?: IRefActivitePrincipale;
  siRegimeLotissement?: boolean;
  siRegimeTrainEntier?: boolean;
  siReutilisationPrevue?: boolean;
  siTrainEntierPossible?: boolean;
  siUtilisee?: boolean;
  theGeom?: any;
}

export default IInstallationTerm;
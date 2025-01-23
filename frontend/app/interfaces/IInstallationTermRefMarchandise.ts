// Import des types nécessaires
import IInstallationTerm from './IInstallationTerm';
import IRefMarchandise from './IRefMarchandise';

// Interface pour la classe InstallationTermRefMarchandise
interface IInstallationTermRefMarchandise {
  flux: any;
  id: number;
  installationTermId?: number;
  installationTerm?: IInstallationTerm;
  refMarchandiseId?: number;
  refMarchandise?: IRefMarchandise;
  siParTrain: boolean;
}

export default IInstallationTermRefMarchandise;
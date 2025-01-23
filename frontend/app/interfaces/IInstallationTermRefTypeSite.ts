// Import des types nécessaires
import IInstallationTerm from './IInstallationTerm';
import IRefTypeSite from './IRefTypeSite';

// Interface pour la classe InstallationTermRefTypeSite
interface IInstallationTermRefTypeSite {
  installationTermId: number;
  installationTerm: IInstallationTerm;
  refTypeSiteId: number;
  refTypeSite: IRefTypeSite;
}

export default IInstallationTermRefTypeSite;
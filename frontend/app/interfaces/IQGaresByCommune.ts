// Interface pour la classe IQGaresByCommune
interface IQGaresByCommune {
  id: number;
  nomCommune: string;
  inseeCommune: string;
  inseeDepartement: string;
  codeUic: number;
  nomGare: string;
  siOuverte: boolean;
  nomAom: string;
  siAutomatesTgvIntercites: boolean;
  siAutomatesTer: boolean;
  siPosteVenteGuichet: boolean;
  siLibreServiceAssiste: boolean;
  theGeom: any;
}

export default IQGaresByCommune;
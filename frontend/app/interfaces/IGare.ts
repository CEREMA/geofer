// Interface pour la classe Gare
interface IGare {
  codeUic?: number;
  id: number;
  inseeCommune?: string;
  inseeDepartement?: string;
  nomAom?: string;
  nomCommune?: string;
  nomGare?: string;
  siAutomatesTer?: boolean;
  siAutomatesTgvIntercites?: boolean;
  siLibreServiceAssiste?: boolean;
  siOuverte: boolean;
  siPosteVenteGuichet?: boolean;
  theGeom?: any;
}

export default IGare;
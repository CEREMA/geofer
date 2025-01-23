// Interface pour la classe IQListGare
interface IQListGare {
  codeUic: number;
  gareId: number;
  inseeCommune: string;
  inseeDepartement: string;
  nomAom: string;
  nomCommune: string;
  nomGare: string;
  siAutomatesTer: boolean;
  siAutomatesTgvIntercites: boolean;
  siLibreServiceAssiste: boolean;
  siOuverte: boolean;
  siPosteVenteGuichet: boolean;
  annee: number;
  nombreArret: number;
  refTypeArretId: number;
}

export default IQListGare;
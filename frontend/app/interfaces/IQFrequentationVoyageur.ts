// Interface pour la classe IQFrequentationVoyageur
interface IQFrequentationVoyageur {
  gareId: number;
  nomGare: string;
  annee: number;
  nombreVoyageur: number;
  siOuverte: boolean;
}

export default IQFrequentationVoyageur;
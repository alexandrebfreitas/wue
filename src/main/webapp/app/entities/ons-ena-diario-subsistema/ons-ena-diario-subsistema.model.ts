export interface IOnsEnaDiarioSubsistema {
  id: number;
  enaArmazenavelRegiaoPercentualmlt?: number | null;
}

export type NewOnsEnaDiarioSubsistema = Omit<IOnsEnaDiarioSubsistema, 'id'> & { id: null };

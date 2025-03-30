export interface IOnsEnaDiarioBacia {
  id: number;
  enaArmazenavelBaciaPercentualmlt?: number | null;
}

export type NewOnsEnaDiarioBacia = Omit<IOnsEnaDiarioBacia, 'id'> & { id: null };

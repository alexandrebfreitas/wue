export interface IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 {
  id: number;
  datPdp?: string | null;
  codSubmercado?: string | null;
  sglTipousina?: string | null;
  codUsinapdp?: string | null;
  nomUsinapdp?: string | null;
  numIntdespa?: number | null;
  valDespasup?: number | null;
}

export type NewOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024 = Omit<
  IOnsDadosHistoricoDespachoEnergiaJaneiro2021ASetembro2024,
  'id'
> & { id: null };

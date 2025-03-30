import dayjs from 'dayjs/esm';

export interface IOnsDadosRelacionamentoEntreConjuntosEUsinas {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  estadId?: string | null;
  nomEstado?: string | null;
  idTipousina?: string | null;
  idConjuntousina?: number | null;
  idOnsConjunto?: string | null;
  idOnsUsina?: string | null;
  nomConjunto?: string | null;
  nomUsina?: string | null;
  ceg?: string | null;
  datIniciorelacionamento?: dayjs.Dayjs | null;
  datFimrelacionamento?: dayjs.Dayjs | null;
}

export type NewOnsDadosRelacionamentoEntreConjuntosEUsinas = Omit<IOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'> & { id: null };

import dayjs from 'dayjs/esm';

export interface IOnsCvuUsinaTermicas {
  id: number;
  datIniciosemana?: dayjs.Dayjs | null;
  datFimsemana?: dayjs.Dayjs | null;
  anoReferencia?: number | null;
  mesReferencia?: number | null;
  numRevisao?: number | null;
  nomSemanaoperativa?: string | null;
  codModelos?: number | null;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  nomUsina?: string | null;
  valCvu?: number | null;
}

export type NewOnsCvuUsinaTermicas = Omit<IOnsCvuUsinaTermicas, 'id'> & { id: null };

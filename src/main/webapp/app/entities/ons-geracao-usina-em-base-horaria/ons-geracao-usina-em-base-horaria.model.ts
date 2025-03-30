import dayjs from 'dayjs/esm';

export interface IOnsGeracaoUsinaEmBaseHoraria {
  id: number;
  dinInstante?: dayjs.Dayjs | null;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  idEstado?: string | null;
  nomEstado?: string | null;
  codModalidadeoperacao?: string | null;
  nomTipousina?: string | null;
  nomTipocombustivel?: string | null;
  nomUsina?: string | null;
  idOns?: string | null;
  ceg?: string | null;
  valGeracao?: number | null;
}

export type NewOnsGeracaoUsinaEmBaseHoraria = Omit<IOnsGeracaoUsinaEmBaseHoraria, 'id'> & { id: null };

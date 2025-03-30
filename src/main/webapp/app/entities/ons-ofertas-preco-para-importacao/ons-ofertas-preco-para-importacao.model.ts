import dayjs from 'dayjs/esm';

export interface IOnsOfertasPrecoParaImportacao {
  id: number;
  nomPais?: string | null;
  nomAgente?: string | null;
  nomBloco?: string | null;
  datIniciovalidade?: dayjs.Dayjs | null;
  datFimvalidade?: dayjs.Dayjs | null;
  valPreco?: number | null;
}

export type NewOnsOfertasPrecoParaImportacao = Omit<IOnsOfertasPrecoParaImportacao, 'id'> & { id: null };

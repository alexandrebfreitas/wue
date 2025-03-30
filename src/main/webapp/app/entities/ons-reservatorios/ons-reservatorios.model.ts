import dayjs from 'dayjs/esm';

export interface IOnsReservatorios {
  id: number;
  nomRee?: string | null;
  datEntrada?: dayjs.Dayjs | null;
  valCotamaxima?: number | null;
  valCotaminima?: number | null;
  valVolmax?: number | null;
  valVolmin?: number | null;
  valVolutiltot?: number | null;
  valProdutibilidadeespecifica?: number | null;
  valProdutividade65volutil?: number | null;
  valTipoperda?: string | null;
  valPerda?: number | null;
  valLatitude?: number | null;
  valLongitude?: number | null;
  idReservatorio?: string | null;
}

export type NewOnsReservatorios = Omit<IOnsReservatorios, 'id'> & { id: null };

import dayjs from 'dayjs/esm';

export interface IOnsBalancoEnergiaNosSubsistemas {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valGerhidraulica?: number | null;
  valGertermica?: number | null;
  valGereolica?: number | null;
  valGersolar?: number | null;
  valCarga?: number | null;
  valIntercambio?: number | null;
}

export type NewOnsBalancoEnergiaNosSubsistemas = Omit<IOnsBalancoEnergiaNosSubsistemas, 'id'> & { id: null };

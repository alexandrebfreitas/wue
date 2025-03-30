import dayjs from 'dayjs/esm';

export interface IOnsBalancoEnergiaDessem {
  id: number;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valDemanda?: number | null;
  valGeracaohidraulicamwmed?: number | null;
  valGeracaopchmwmed?: number | null;
  valGeracaotermicamwed?: number | null;
  valGeracaopctmwmed?: number | null;
  valGeracaoeolicamwmed?: number | null;
  valGeracaofotovoltaicamwmed?: number | null;
}

export type NewOnsBalancoEnergiaDessem = Omit<IOnsBalancoEnergiaDessem, 'id'> & { id: null };

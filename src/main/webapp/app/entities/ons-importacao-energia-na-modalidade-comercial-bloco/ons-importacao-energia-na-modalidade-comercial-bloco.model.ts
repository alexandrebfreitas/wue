import dayjs from 'dayjs/esm';

export interface IOnsImportacaoEnergiaNaModalidadeComercialBloco {
  id: number;
  nomPais?: string | null;
  nomAgente?: string | null;
  nomBloco?: string | null;
  codBloco?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valImportacaoprogramada?: number | null;
  valImportacaodespachada?: number | null;
  valImportacaoverificada?: number | null;
  valPreco?: number | null;
}

export type NewOnsImportacaoEnergiaNaModalidadeComercialBloco = Omit<IOnsImportacaoEnergiaNaModalidadeComercialBloco, 'id'> & { id: null };

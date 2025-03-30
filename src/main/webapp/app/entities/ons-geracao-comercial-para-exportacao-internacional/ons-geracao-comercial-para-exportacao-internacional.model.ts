import dayjs from 'dayjs/esm';

export interface IOnsGeracaoComercialParaExportacaoInternacional {
  id: number;
  nomConversora?: string | null;
  dinInstante?: dayjs.Dayjs | null;
  valModalidadecontratual?: number | null;
  valModalidadeemergencial?: number | null;
  valModalidadeoportunidade?: number | null;
  valModalidadeteste?: number | null;
  valModalidadeexcepcional?: number | null;
}

export type NewOnsGeracaoComercialParaExportacaoInternacional = Omit<IOnsGeracaoComercialParaExportacaoInternacional, 'id'> & { id: null };

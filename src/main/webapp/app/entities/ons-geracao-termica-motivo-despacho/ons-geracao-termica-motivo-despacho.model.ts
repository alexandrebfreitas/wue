import dayjs from 'dayjs/esm';

export interface IOnsGeracaoTermicaMotivoDespacho {
  id: number;
  dinInstante?: dayjs.Dayjs | null;
  nomTipopatamar?: string | null;
  idSubsistema?: string | null;
  nomSubsistema?: string | null;
  nomUsina?: string | null;
  codUsinaplanejamento?: number | null;
  ceg?: string | null;
  valProggeracao?: number | null;
  valProgordemmerito?: number | null;
  valProgordemdemeritoref?: number | null;
  valProgordemdemeritoacimadainflex?: number | null;
  valProginflexibilidade?: number | null;
  valProginflexembutmerito?: number | null;
  valProginflexpura?: number | null;
  valPrograzaoeletrica?: number | null;
  valProggarantiaenergetica?: number | null;
  valProggfom?: number | null;
  valProgreposicaoperdas?: number | null;
  valProgexportacao?: number | null;
  valProgreservapotencia?: number | null;
  valProggsub?: number | null;
  valProgunitcommitment?: number | null;
  valProgconstrainedoff?: number | null;
  valProginflexibilidadedessem?: number | null;
  valVerifgeracao?: number | null;
  valVerifordemmerito?: number | null;
  valVerifordemdemeritoacimadainflex?: number | null;
  valVerifinflexibilidade?: number | null;
  valVerifinflexembutmerito?: number | null;
  valVerifinflexpura?: number | null;
  valVerifrazaoeletrica?: number | null;
  valVerifgarantiaenergetica?: number | null;
  valVerifgfom?: number | null;
  valVerifreposicaoperdas?: number | null;
  valVerifexportacao?: number | null;
  valFdexp?: number | null;
  valVerifreservapotencia?: number | null;
  valAtendsatisfatoriorpo?: number | null;
  valVerifgsub?: number | null;
  valVerifunitcommitment?: number | null;
  valVerifconstrainedoff?: number | null;
  tipRestricaoeletrica?: number | null;
}

export type NewOnsGeracaoTermicaMotivoDespacho = Omit<IOnsGeracaoTermicaMotivoDespacho, 'id'> & { id: null };

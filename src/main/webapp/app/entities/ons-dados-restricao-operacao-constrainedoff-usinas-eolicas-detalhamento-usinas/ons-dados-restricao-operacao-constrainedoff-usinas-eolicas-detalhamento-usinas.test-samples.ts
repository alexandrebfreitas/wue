import dayjs from 'dayjs/esm';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
} from './ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';

export const sampleWithRequiredData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = {
  id: 28744,
};

export const sampleWithPartialData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = {
  id: 22621,
  idSubsistema: 'boohoo',
  nomModalidadeoperacao: 'ack finally drat',
  nomUsina: 'little',
  ceg: 'under courageously',
  flgDadoventoinvalido: 12626.37,
  valGeracaoestimada: 14521.98,
};

export const sampleWithFullData: IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = {
  id: 30698,
  idSubsistema: 'darling license',
  nomModalidadeoperacao: 'helpful fooey',
  idEstado: 'fooey despite hmph',
  nomConjuntousina: 'deplore evenly dulcimer',
  nomUsina: 'above limply along',
  dinInstante: dayjs('2025-03-29'),
  idOns: 'giving glum',
  ceg: 'zowie pleasure',
  valVentoverificado: 16716.83,
  flgDadoventoinvalido: 29553.1,
  valGeracaoestimada: 28981.98,
  valGeracaoverificada: 31475.8,
};

export const sampleWithNewData: NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);

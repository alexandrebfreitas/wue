import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-fotovoltaicas-detalhamento-usinas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas for edit and NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupInput for create.
 */
type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupInput =
  | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas
  | PartialWithRequiredKeyOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormDefaults = Pick<
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
  'id'
>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupContent = {
  id: FormControl<
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['id']
    | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['id']
  >;
  idSubsistema: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['idSubsistema']>;
  nomModalidadeoperacao: FormControl<
    IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['nomModalidadeoperacao']
  >;
  idEstado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['idEstado']>;
  nomConjuntousina: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['nomConjuntousina']>;
  nomUsina: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['nomUsina']>;
  dinInstante: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['dinInstante']>;
  idOns: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['idOns']>;
  ceg: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['ceg']>;
  valIrradianciaverificado: FormControl<
    IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['valIrradianciaverificado']
  >;
  flgDadoirradianciainvalido: FormControl<
    IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['flgDadoirradianciainvalido']
  >;
  valGeracaoestimada: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['valGeracaoestimada']>;
  valGeracaoverificada: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas['valGeracaoverificada']>;
};

export type OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroup =
  FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormService {
  createOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroup(
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupInput = {
      id: null,
    },
  ): OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroup {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
    };
    return new FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.idSubsistema),
      nomModalidadeoperacao: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.nomModalidadeoperacao,
      ),
      idEstado: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.idEstado),
      nomConjuntousina: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.nomConjuntousina,
      ),
      nomUsina: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.nomUsina),
      dinInstante: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.dinInstante),
      idOns: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.idOns),
      ceg: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.ceg),
      valIrradianciaverificado: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.valIrradianciaverificado,
      ),
      flgDadoirradianciainvalido: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.flgDadoirradianciainvalido,
      ),
      valGeracaoestimada: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.valGeracaoestimada,
      ),
      valGeracaoverificada: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.valGeracaoverificada,
      ),
    });
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroup,
  ):
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas
    | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas {
    return form.getRawValue() as
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas;
  }

  resetForm(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroup,
    onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas: OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormGroupInput,
  ): void {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinas,
    };
    form.reset(
      {
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue,
        id: { value: onsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosRestricaoOperacaoConstrainedoffUsinasFotovoltaicasDetalhamentoUsinasFormDefaults {
    return {
      id: null,
    };
  }
}

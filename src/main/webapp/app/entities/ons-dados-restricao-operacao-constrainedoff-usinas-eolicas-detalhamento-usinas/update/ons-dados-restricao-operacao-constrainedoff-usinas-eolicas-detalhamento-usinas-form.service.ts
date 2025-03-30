import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
} from '../ons-dados-restricao-operacao-constrainedoff-usinas-eolicas-detalhamento-usinas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas for edit and NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupInput for create.
 */
type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupInput =
  | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
  | PartialWithRequiredKeyOf<NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormDefaults = Pick<
  NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
  'id'
>;

type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupContent = {
  id: FormControl<
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['id']
    | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['id']
  >;
  idSubsistema: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['idSubsistema']>;
  nomModalidadeoperacao: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['nomModalidadeoperacao']>;
  idEstado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['idEstado']>;
  nomConjuntousina: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['nomConjuntousina']>;
  nomUsina: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['nomUsina']>;
  dinInstante: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['dinInstante']>;
  idOns: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['idOns']>;
  ceg: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['ceg']>;
  valVentoverificado: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['valVentoverificado']>;
  flgDadoventoinvalido: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['flgDadoventoinvalido']>;
  valGeracaoestimada: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['valGeracaoestimada']>;
  valGeracaoverificada: FormControl<IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas['valGeracaoverificada']>;
};

export type OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup =
  FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormService {
  createOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup(
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupInput = {
      id: null,
    },
  ): OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
    };
    return new FormGroup<OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.idSubsistema),
      nomModalidadeoperacao: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.nomModalidadeoperacao,
      ),
      idEstado: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.idEstado),
      nomConjuntousina: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.nomConjuntousina),
      nomUsina: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.nomUsina),
      dinInstante: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.dinInstante),
      idOns: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.idOns),
      ceg: new FormControl(onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.ceg),
      valVentoverificado: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.valVentoverificado,
      ),
      flgDadoventoinvalido: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.flgDadoventoinvalido,
      ),
      valGeracaoestimada: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.valGeracaoestimada,
      ),
      valGeracaoverificada: new FormControl(
        onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.valGeracaoverificada,
      ),
    });
  }

  getOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup,
  ):
    | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
    | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas {
    return form.getRawValue() as
      | IOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas
      | NewOnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas;
  }

  resetForm(
    form: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroup,
    onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas: OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormGroupInput,
  ): void {
    const onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinas,
    };
    form.reset(
      {
        ...onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue,
        id: { value: onsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosRestricaoOperacaoConstrainedoffUsinasEolicasDetalhamentoUsinasFormDefaults {
    return {
      id: null,
    };
  }
}

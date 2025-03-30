import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
} from '../ons-dados-indicadores-confiabilidade-rede-basica-ccat-controle-carregamento-transformadores.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores for edit and NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupInput for create.
 */
type OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupInput =
  | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
  | PartialWithRequiredKeyOf<NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores>;

type OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormDefaults = Pick<
  NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
  'id'
>;

type OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupContent = {
  id: FormControl<
    | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['id']
    | NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['id']
  >;
  codTipoagregacao: FormControl<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['codTipoagregacao']>;
  idPeriodicidade: FormControl<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['idPeriodicidade']>;
  nomAgregacao: FormControl<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['nomAgregacao']>;
  dinReferencia: FormControl<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['dinReferencia']>;
  numTransformadoresoperacao: FormControl<
    IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['numTransformadoresoperacao']
  >;
  numTransformadoresviolados: FormControl<
    IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['numTransformadoresviolados']
  >;
  valCcat: FormControl<IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores['valCcat']>;
};

export type OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup =
  FormGroup<OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormService {
  createOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup(
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupInput = {
      id: null,
    },
  ): OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup {
    const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
    };
    return new FormGroup<OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupContent>({
      id: new FormControl(
        { value: onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      codTipoagregacao: new FormControl(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.codTipoagregacao,
      ),
      idPeriodicidade: new FormControl(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.idPeriodicidade,
      ),
      nomAgregacao: new FormControl(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.nomAgregacao,
      ),
      dinReferencia: new FormControl(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.dinReferencia,
      ),
      numTransformadoresoperacao: new FormControl(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.numTransformadoresoperacao,
      ),
      numTransformadoresviolados: new FormControl(
        onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.numTransformadoresviolados,
      ),
      valCcat: new FormControl(onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.valCcat),
    });
  }

  getOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores(
    form: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup,
  ):
    | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
    | NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores {
    return form.getRawValue() as
      | IOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores
      | NewOnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores;
  }

  resetForm(
    form: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroup,
    onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores: OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormGroupInput,
  ): void {
    const onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadores,
    };
    form.reset(
      {
        ...onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue,
        id: { value: onsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosIndicadoresConfiabilidadeRedeBasicaCcatControleCarregamentoTransformadoresFormDefaults {
    return {
      id: null,
    };
  }
}

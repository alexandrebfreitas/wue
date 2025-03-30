import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
} from '../ons-dados-relacionamento-entre-grupos-pequenas-usinas-e-usinas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas for edit and NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupInput for create.
 */
type OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupInput =
  | IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
  | PartialWithRequiredKeyOf<NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas>;

type OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormDefaults = Pick<
  NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
  'id'
>;

type OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupContent = {
  id: FormControl<
    IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['id'] | NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['id']
  >;
  idSubsistema: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['nomSubsistema']>;
  estadId: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['estadId']>;
  nomEstado: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['nomEstado']>;
  idTipousina: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['idTipousina']>;
  idOnsPequenasusinas: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['idOnsPequenasusinas']>;
  idOnsUsina: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['idOnsUsina']>;
  nomPequenasusinas: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['nomPequenasusinas']>;
  nomUsina: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['nomUsina']>;
  ceg: FormControl<IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas['ceg']>;
};

export type OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup =
  FormGroup<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormService {
  createOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup(
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupInput = {
      id: null,
    },
  ): OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup {
    const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
    };
    return new FormGroup<OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.nomSubsistema),
      estadId: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.estadId),
      nomEstado: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.nomEstado),
      idTipousina: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.idTipousina),
      idOnsPequenasusinas: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.idOnsPequenasusinas),
      idOnsUsina: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.idOnsUsina),
      nomPequenasusinas: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.nomPequenasusinas),
      nomUsina: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.nomUsina),
      ceg: new FormControl(onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.ceg),
    });
  }

  getOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas(
    form: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup,
  ): IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas | NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas {
    return form.getRawValue() as
      | IOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas
      | NewOnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas;
  }

  resetForm(
    form: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroup,
    onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas: OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormGroupInput,
  ): void {
    const onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinas,
    };
    form.reset(
      {
        ...onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue,
        id: { value: onsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosRelacionamentoEntreGruposPequenasUsinasEUsinasFormDefaults {
    return {
      id: null,
    };
  }
}

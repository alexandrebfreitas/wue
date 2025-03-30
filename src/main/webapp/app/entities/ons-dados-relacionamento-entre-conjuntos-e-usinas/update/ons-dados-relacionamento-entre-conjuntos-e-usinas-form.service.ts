import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsDadosRelacionamentoEntreConjuntosEUsinas,
  NewOnsDadosRelacionamentoEntreConjuntosEUsinas,
} from '../ons-dados-relacionamento-entre-conjuntos-e-usinas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsDadosRelacionamentoEntreConjuntosEUsinas for edit and NewOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupInput for create.
 */
type OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupInput =
  | IOnsDadosRelacionamentoEntreConjuntosEUsinas
  | PartialWithRequiredKeyOf<NewOnsDadosRelacionamentoEntreConjuntosEUsinas>;

type OnsDadosRelacionamentoEntreConjuntosEUsinasFormDefaults = Pick<NewOnsDadosRelacionamentoEntreConjuntosEUsinas, 'id'>;

type OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupContent = {
  id: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['id'] | NewOnsDadosRelacionamentoEntreConjuntosEUsinas['id']>;
  idSubsistema: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['nomSubsistema']>;
  estadId: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['estadId']>;
  nomEstado: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['nomEstado']>;
  idTipousina: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['idTipousina']>;
  idConjuntousina: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['idConjuntousina']>;
  idOnsConjunto: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['idOnsConjunto']>;
  idOnsUsina: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['idOnsUsina']>;
  nomConjunto: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['nomConjunto']>;
  nomUsina: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['nomUsina']>;
  ceg: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['ceg']>;
  datIniciorelacionamento: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['datIniciorelacionamento']>;
  datFimrelacionamento: FormControl<IOnsDadosRelacionamentoEntreConjuntosEUsinas['datFimrelacionamento']>;
};

export type OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup = FormGroup<OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsDadosRelacionamentoEntreConjuntosEUsinasFormService {
  createOnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup(
    onsDadosRelacionamentoEntreConjuntosEUsinas: OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupInput = { id: null },
  ): OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup {
    const onsDadosRelacionamentoEntreConjuntosEUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRelacionamentoEntreConjuntosEUsinas,
    };
    return new FormGroup<OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupContent>({
      id: new FormControl(
        { value: onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.nomSubsistema),
      estadId: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.estadId),
      nomEstado: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.nomEstado),
      idTipousina: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.idTipousina),
      idConjuntousina: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.idConjuntousina),
      idOnsConjunto: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.idOnsConjunto),
      idOnsUsina: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.idOnsUsina),
      nomConjunto: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.nomConjunto),
      nomUsina: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.nomUsina),
      ceg: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.ceg),
      datIniciorelacionamento: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.datIniciorelacionamento),
      datFimrelacionamento: new FormControl(onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.datFimrelacionamento),
    });
  }

  getOnsDadosRelacionamentoEntreConjuntosEUsinas(
    form: OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup,
  ): IOnsDadosRelacionamentoEntreConjuntosEUsinas | NewOnsDadosRelacionamentoEntreConjuntosEUsinas {
    return form.getRawValue() as IOnsDadosRelacionamentoEntreConjuntosEUsinas | NewOnsDadosRelacionamentoEntreConjuntosEUsinas;
  }

  resetForm(
    form: OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroup,
    onsDadosRelacionamentoEntreConjuntosEUsinas: OnsDadosRelacionamentoEntreConjuntosEUsinasFormGroupInput,
  ): void {
    const onsDadosRelacionamentoEntreConjuntosEUsinasRawValue = {
      ...this.getFormDefaults(),
      ...onsDadosRelacionamentoEntreConjuntosEUsinas,
    };
    form.reset(
      {
        ...onsDadosRelacionamentoEntreConjuntosEUsinasRawValue,
        id: { value: onsDadosRelacionamentoEntreConjuntosEUsinasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsDadosRelacionamentoEntreConjuntosEUsinasFormDefaults {
    return {
      id: null,
    };
  }
}

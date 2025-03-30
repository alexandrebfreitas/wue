import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsTaxasTeifaETeip, NewOnsTaxasTeifaETeip } from '../ons-taxas-teifa-e-teip.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsTaxasTeifaETeip for edit and NewOnsTaxasTeifaETeipFormGroupInput for create.
 */
type OnsTaxasTeifaETeipFormGroupInput = IOnsTaxasTeifaETeip | PartialWithRequiredKeyOf<NewOnsTaxasTeifaETeip>;

type OnsTaxasTeifaETeipFormDefaults = Pick<NewOnsTaxasTeifaETeip, 'id'>;

type OnsTaxasTeifaETeipFormGroupContent = {
  id: FormControl<IOnsTaxasTeifaETeip['id'] | NewOnsTaxasTeifaETeip['id']>;
  nomUsina: FormControl<IOnsTaxasTeifaETeip['nomUsina']>;
  codCeg: FormControl<IOnsTaxasTeifaETeip['codCeg']>;
  idTipousina: FormControl<IOnsTaxasTeifaETeip['idTipousina']>;
  dinMes: FormControl<IOnsTaxasTeifaETeip['dinMes']>;
  nomTaxa: FormControl<IOnsTaxasTeifaETeip['nomTaxa']>;
  valTaxa: FormControl<IOnsTaxasTeifaETeip['valTaxa']>;
  numVersao: FormControl<IOnsTaxasTeifaETeip['numVersao']>;
  dinInstante: FormControl<IOnsTaxasTeifaETeip['dinInstante']>;
};

export type OnsTaxasTeifaETeipFormGroup = FormGroup<OnsTaxasTeifaETeipFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsTaxasTeifaETeipFormService {
  createOnsTaxasTeifaETeipFormGroup(onsTaxasTeifaETeip: OnsTaxasTeifaETeipFormGroupInput = { id: null }): OnsTaxasTeifaETeipFormGroup {
    const onsTaxasTeifaETeipRawValue = {
      ...this.getFormDefaults(),
      ...onsTaxasTeifaETeip,
    };
    return new FormGroup<OnsTaxasTeifaETeipFormGroupContent>({
      id: new FormControl(
        { value: onsTaxasTeifaETeipRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomUsina: new FormControl(onsTaxasTeifaETeipRawValue.nomUsina),
      codCeg: new FormControl(onsTaxasTeifaETeipRawValue.codCeg),
      idTipousina: new FormControl(onsTaxasTeifaETeipRawValue.idTipousina),
      dinMes: new FormControl(onsTaxasTeifaETeipRawValue.dinMes),
      nomTaxa: new FormControl(onsTaxasTeifaETeipRawValue.nomTaxa),
      valTaxa: new FormControl(onsTaxasTeifaETeipRawValue.valTaxa),
      numVersao: new FormControl(onsTaxasTeifaETeipRawValue.numVersao),
      dinInstante: new FormControl(onsTaxasTeifaETeipRawValue.dinInstante),
    });
  }

  getOnsTaxasTeifaETeip(form: OnsTaxasTeifaETeipFormGroup): IOnsTaxasTeifaETeip | NewOnsTaxasTeifaETeip {
    return form.getRawValue() as IOnsTaxasTeifaETeip | NewOnsTaxasTeifaETeip;
  }

  resetForm(form: OnsTaxasTeifaETeipFormGroup, onsTaxasTeifaETeip: OnsTaxasTeifaETeipFormGroupInput): void {
    const onsTaxasTeifaETeipRawValue = { ...this.getFormDefaults(), ...onsTaxasTeifaETeip };
    form.reset(
      {
        ...onsTaxasTeifaETeipRawValue,
        id: { value: onsTaxasTeifaETeipRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsTaxasTeifaETeipFormDefaults {
    return {
      id: null,
    };
  }
}

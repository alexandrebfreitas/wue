import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsBalancoEnergiaDessem, NewOnsBalancoEnergiaDessem } from '../ons-balanco-energia-dessem.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsBalancoEnergiaDessem for edit and NewOnsBalancoEnergiaDessemFormGroupInput for create.
 */
type OnsBalancoEnergiaDessemFormGroupInput = IOnsBalancoEnergiaDessem | PartialWithRequiredKeyOf<NewOnsBalancoEnergiaDessem>;

type OnsBalancoEnergiaDessemFormDefaults = Pick<NewOnsBalancoEnergiaDessem, 'id'>;

type OnsBalancoEnergiaDessemFormGroupContent = {
  id: FormControl<IOnsBalancoEnergiaDessem['id'] | NewOnsBalancoEnergiaDessem['id']>;
  idSubsistema: FormControl<IOnsBalancoEnergiaDessem['idSubsistema']>;
  nomSubsistema: FormControl<IOnsBalancoEnergiaDessem['nomSubsistema']>;
  dinInstante: FormControl<IOnsBalancoEnergiaDessem['dinInstante']>;
  valDemanda: FormControl<IOnsBalancoEnergiaDessem['valDemanda']>;
  valGeracaohidraulicamwmed: FormControl<IOnsBalancoEnergiaDessem['valGeracaohidraulicamwmed']>;
  valGeracaopchmwmed: FormControl<IOnsBalancoEnergiaDessem['valGeracaopchmwmed']>;
  valGeracaotermicamwed: FormControl<IOnsBalancoEnergiaDessem['valGeracaotermicamwed']>;
  valGeracaopctmwmed: FormControl<IOnsBalancoEnergiaDessem['valGeracaopctmwmed']>;
  valGeracaoeolicamwmed: FormControl<IOnsBalancoEnergiaDessem['valGeracaoeolicamwmed']>;
  valGeracaofotovoltaicamwmed: FormControl<IOnsBalancoEnergiaDessem['valGeracaofotovoltaicamwmed']>;
};

export type OnsBalancoEnergiaDessemFormGroup = FormGroup<OnsBalancoEnergiaDessemFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsBalancoEnergiaDessemFormService {
  createOnsBalancoEnergiaDessemFormGroup(
    onsBalancoEnergiaDessem: OnsBalancoEnergiaDessemFormGroupInput = { id: null },
  ): OnsBalancoEnergiaDessemFormGroup {
    const onsBalancoEnergiaDessemRawValue = {
      ...this.getFormDefaults(),
      ...onsBalancoEnergiaDessem,
    };
    return new FormGroup<OnsBalancoEnergiaDessemFormGroupContent>({
      id: new FormControl(
        { value: onsBalancoEnergiaDessemRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsBalancoEnergiaDessemRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsBalancoEnergiaDessemRawValue.nomSubsistema),
      dinInstante: new FormControl(onsBalancoEnergiaDessemRawValue.dinInstante),
      valDemanda: new FormControl(onsBalancoEnergiaDessemRawValue.valDemanda),
      valGeracaohidraulicamwmed: new FormControl(onsBalancoEnergiaDessemRawValue.valGeracaohidraulicamwmed),
      valGeracaopchmwmed: new FormControl(onsBalancoEnergiaDessemRawValue.valGeracaopchmwmed),
      valGeracaotermicamwed: new FormControl(onsBalancoEnergiaDessemRawValue.valGeracaotermicamwed),
      valGeracaopctmwmed: new FormControl(onsBalancoEnergiaDessemRawValue.valGeracaopctmwmed),
      valGeracaoeolicamwmed: new FormControl(onsBalancoEnergiaDessemRawValue.valGeracaoeolicamwmed),
      valGeracaofotovoltaicamwmed: new FormControl(onsBalancoEnergiaDessemRawValue.valGeracaofotovoltaicamwmed),
    });
  }

  getOnsBalancoEnergiaDessem(form: OnsBalancoEnergiaDessemFormGroup): IOnsBalancoEnergiaDessem | NewOnsBalancoEnergiaDessem {
    return form.getRawValue() as IOnsBalancoEnergiaDessem | NewOnsBalancoEnergiaDessem;
  }

  resetForm(form: OnsBalancoEnergiaDessemFormGroup, onsBalancoEnergiaDessem: OnsBalancoEnergiaDessemFormGroupInput): void {
    const onsBalancoEnergiaDessemRawValue = { ...this.getFormDefaults(), ...onsBalancoEnergiaDessem };
    form.reset(
      {
        ...onsBalancoEnergiaDessemRawValue,
        id: { value: onsBalancoEnergiaDessemRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsBalancoEnergiaDessemFormDefaults {
    return {
      id: null,
    };
  }
}

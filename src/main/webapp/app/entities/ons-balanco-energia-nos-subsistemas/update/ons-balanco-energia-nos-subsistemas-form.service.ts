import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsBalancoEnergiaNosSubsistemas, NewOnsBalancoEnergiaNosSubsistemas } from '../ons-balanco-energia-nos-subsistemas.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsBalancoEnergiaNosSubsistemas for edit and NewOnsBalancoEnergiaNosSubsistemasFormGroupInput for create.
 */
type OnsBalancoEnergiaNosSubsistemasFormGroupInput =
  | IOnsBalancoEnergiaNosSubsistemas
  | PartialWithRequiredKeyOf<NewOnsBalancoEnergiaNosSubsistemas>;

type OnsBalancoEnergiaNosSubsistemasFormDefaults = Pick<NewOnsBalancoEnergiaNosSubsistemas, 'id'>;

type OnsBalancoEnergiaNosSubsistemasFormGroupContent = {
  id: FormControl<IOnsBalancoEnergiaNosSubsistemas['id'] | NewOnsBalancoEnergiaNosSubsistemas['id']>;
  idSubsistema: FormControl<IOnsBalancoEnergiaNosSubsistemas['idSubsistema']>;
  nomSubsistema: FormControl<IOnsBalancoEnergiaNosSubsistemas['nomSubsistema']>;
  dinInstante: FormControl<IOnsBalancoEnergiaNosSubsistemas['dinInstante']>;
  valGerhidraulica: FormControl<IOnsBalancoEnergiaNosSubsistemas['valGerhidraulica']>;
  valGertermica: FormControl<IOnsBalancoEnergiaNosSubsistemas['valGertermica']>;
  valGereolica: FormControl<IOnsBalancoEnergiaNosSubsistemas['valGereolica']>;
  valGersolar: FormControl<IOnsBalancoEnergiaNosSubsistemas['valGersolar']>;
  valCarga: FormControl<IOnsBalancoEnergiaNosSubsistemas['valCarga']>;
  valIntercambio: FormControl<IOnsBalancoEnergiaNosSubsistemas['valIntercambio']>;
};

export type OnsBalancoEnergiaNosSubsistemasFormGroup = FormGroup<OnsBalancoEnergiaNosSubsistemasFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsBalancoEnergiaNosSubsistemasFormService {
  createOnsBalancoEnergiaNosSubsistemasFormGroup(
    onsBalancoEnergiaNosSubsistemas: OnsBalancoEnergiaNosSubsistemasFormGroupInput = { id: null },
  ): OnsBalancoEnergiaNosSubsistemasFormGroup {
    const onsBalancoEnergiaNosSubsistemasRawValue = {
      ...this.getFormDefaults(),
      ...onsBalancoEnergiaNosSubsistemas,
    };
    return new FormGroup<OnsBalancoEnergiaNosSubsistemasFormGroupContent>({
      id: new FormControl(
        { value: onsBalancoEnergiaNosSubsistemasRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistema: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.idSubsistema),
      nomSubsistema: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.nomSubsistema),
      dinInstante: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.dinInstante),
      valGerhidraulica: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.valGerhidraulica),
      valGertermica: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.valGertermica),
      valGereolica: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.valGereolica),
      valGersolar: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.valGersolar),
      valCarga: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.valCarga),
      valIntercambio: new FormControl(onsBalancoEnergiaNosSubsistemasRawValue.valIntercambio),
    });
  }

  getOnsBalancoEnergiaNosSubsistemas(
    form: OnsBalancoEnergiaNosSubsistemasFormGroup,
  ): IOnsBalancoEnergiaNosSubsistemas | NewOnsBalancoEnergiaNosSubsistemas {
    return form.getRawValue() as IOnsBalancoEnergiaNosSubsistemas | NewOnsBalancoEnergiaNosSubsistemas;
  }

  resetForm(
    form: OnsBalancoEnergiaNosSubsistemasFormGroup,
    onsBalancoEnergiaNosSubsistemas: OnsBalancoEnergiaNosSubsistemasFormGroupInput,
  ): void {
    const onsBalancoEnergiaNosSubsistemasRawValue = { ...this.getFormDefaults(), ...onsBalancoEnergiaNosSubsistemas };
    form.reset(
      {
        ...onsBalancoEnergiaNosSubsistemasRawValue,
        id: { value: onsBalancoEnergiaNosSubsistemasRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsBalancoEnergiaNosSubsistemasFormDefaults {
    return {
      id: null,
    };
  }
}

import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IOnsEarDiarioReservatorio, NewOnsEarDiarioReservatorio } from '../ons-ear-diario-reservatorio.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsEarDiarioReservatorio for edit and NewOnsEarDiarioReservatorioFormGroupInput for create.
 */
type OnsEarDiarioReservatorioFormGroupInput = IOnsEarDiarioReservatorio | PartialWithRequiredKeyOf<NewOnsEarDiarioReservatorio>;

type OnsEarDiarioReservatorioFormDefaults = Pick<NewOnsEarDiarioReservatorio, 'id'>;

type OnsEarDiarioReservatorioFormGroupContent = {
  id: FormControl<IOnsEarDiarioReservatorio['id'] | NewOnsEarDiarioReservatorio['id']>;
  idSubsistemaJusante: FormControl<IOnsEarDiarioReservatorio['idSubsistemaJusante']>;
  nomSubsistemaJusante: FormControl<IOnsEarDiarioReservatorio['nomSubsistemaJusante']>;
  earData: FormControl<IOnsEarDiarioReservatorio['earData']>;
  earReservatorioSubsistemaProprioMwmes: FormControl<IOnsEarDiarioReservatorio['earReservatorioSubsistemaProprioMwmes']>;
  earReservatorioSubsistemaJusanteMwmes: FormControl<IOnsEarDiarioReservatorio['earReservatorioSubsistemaJusanteMwmes']>;
  earmaxReservatorioSubsistemaProprioMwmes: FormControl<IOnsEarDiarioReservatorio['earmaxReservatorioSubsistemaProprioMwmes']>;
  earmaxReservatorioSubsistemaJusanteMwmes: FormControl<IOnsEarDiarioReservatorio['earmaxReservatorioSubsistemaJusanteMwmes']>;
  earReservatorioPercentual: FormControl<IOnsEarDiarioReservatorio['earReservatorioPercentual']>;
  earTotalMwmes: FormControl<IOnsEarDiarioReservatorio['earTotalMwmes']>;
  earMaximaTotalMwmes: FormControl<IOnsEarDiarioReservatorio['earMaximaTotalMwmes']>;
  valContribearbacia: FormControl<IOnsEarDiarioReservatorio['valContribearbacia']>;
  valContribearmaxbacia: FormControl<IOnsEarDiarioReservatorio['valContribearmaxbacia']>;
  valContribearsubsistema: FormControl<IOnsEarDiarioReservatorio['valContribearsubsistema']>;
  valContribearmaxsubsistema: FormControl<IOnsEarDiarioReservatorio['valContribearmaxsubsistema']>;
  valContribearsubsistemajusante: FormControl<IOnsEarDiarioReservatorio['valContribearsubsistemajusante']>;
  valContribearmaxsubsistemajusante: FormControl<IOnsEarDiarioReservatorio['valContribearmaxsubsistemajusante']>;
  valContribearsin: FormControl<IOnsEarDiarioReservatorio['valContribearsin']>;
  valContribearmaxsin: FormControl<IOnsEarDiarioReservatorio['valContribearmaxsin']>;
};

export type OnsEarDiarioReservatorioFormGroup = FormGroup<OnsEarDiarioReservatorioFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsEarDiarioReservatorioFormService {
  createOnsEarDiarioReservatorioFormGroup(
    onsEarDiarioReservatorio: OnsEarDiarioReservatorioFormGroupInput = { id: null },
  ): OnsEarDiarioReservatorioFormGroup {
    const onsEarDiarioReservatorioRawValue = {
      ...this.getFormDefaults(),
      ...onsEarDiarioReservatorio,
    };
    return new FormGroup<OnsEarDiarioReservatorioFormGroupContent>({
      id: new FormControl(
        { value: onsEarDiarioReservatorioRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      idSubsistemaJusante: new FormControl(onsEarDiarioReservatorioRawValue.idSubsistemaJusante),
      nomSubsistemaJusante: new FormControl(onsEarDiarioReservatorioRawValue.nomSubsistemaJusante),
      earData: new FormControl(onsEarDiarioReservatorioRawValue.earData),
      earReservatorioSubsistemaProprioMwmes: new FormControl(onsEarDiarioReservatorioRawValue.earReservatorioSubsistemaProprioMwmes),
      earReservatorioSubsistemaJusanteMwmes: new FormControl(onsEarDiarioReservatorioRawValue.earReservatorioSubsistemaJusanteMwmes),
      earmaxReservatorioSubsistemaProprioMwmes: new FormControl(onsEarDiarioReservatorioRawValue.earmaxReservatorioSubsistemaProprioMwmes),
      earmaxReservatorioSubsistemaJusanteMwmes: new FormControl(onsEarDiarioReservatorioRawValue.earmaxReservatorioSubsistemaJusanteMwmes),
      earReservatorioPercentual: new FormControl(onsEarDiarioReservatorioRawValue.earReservatorioPercentual),
      earTotalMwmes: new FormControl(onsEarDiarioReservatorioRawValue.earTotalMwmes),
      earMaximaTotalMwmes: new FormControl(onsEarDiarioReservatorioRawValue.earMaximaTotalMwmes),
      valContribearbacia: new FormControl(onsEarDiarioReservatorioRawValue.valContribearbacia),
      valContribearmaxbacia: new FormControl(onsEarDiarioReservatorioRawValue.valContribearmaxbacia),
      valContribearsubsistema: new FormControl(onsEarDiarioReservatorioRawValue.valContribearsubsistema),
      valContribearmaxsubsistema: new FormControl(onsEarDiarioReservatorioRawValue.valContribearmaxsubsistema),
      valContribearsubsistemajusante: new FormControl(onsEarDiarioReservatorioRawValue.valContribearsubsistemajusante),
      valContribearmaxsubsistemajusante: new FormControl(onsEarDiarioReservatorioRawValue.valContribearmaxsubsistemajusante),
      valContribearsin: new FormControl(onsEarDiarioReservatorioRawValue.valContribearsin),
      valContribearmaxsin: new FormControl(onsEarDiarioReservatorioRawValue.valContribearmaxsin),
    });
  }

  getOnsEarDiarioReservatorio(form: OnsEarDiarioReservatorioFormGroup): IOnsEarDiarioReservatorio | NewOnsEarDiarioReservatorio {
    return form.getRawValue() as IOnsEarDiarioReservatorio | NewOnsEarDiarioReservatorio;
  }

  resetForm(form: OnsEarDiarioReservatorioFormGroup, onsEarDiarioReservatorio: OnsEarDiarioReservatorioFormGroupInput): void {
    const onsEarDiarioReservatorioRawValue = { ...this.getFormDefaults(), ...onsEarDiarioReservatorio };
    form.reset(
      {
        ...onsEarDiarioReservatorioRawValue,
        id: { value: onsEarDiarioReservatorioRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsEarDiarioReservatorioFormDefaults {
    return {
      id: null,
    };
  }
}

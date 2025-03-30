import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import {
  IOnsGeracaoComercialParaExportacaoInternacional,
  NewOnsGeracaoComercialParaExportacaoInternacional,
} from '../ons-geracao-comercial-para-exportacao-internacional.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOnsGeracaoComercialParaExportacaoInternacional for edit and NewOnsGeracaoComercialParaExportacaoInternacionalFormGroupInput for create.
 */
type OnsGeracaoComercialParaExportacaoInternacionalFormGroupInput =
  | IOnsGeracaoComercialParaExportacaoInternacional
  | PartialWithRequiredKeyOf<NewOnsGeracaoComercialParaExportacaoInternacional>;

type OnsGeracaoComercialParaExportacaoInternacionalFormDefaults = Pick<NewOnsGeracaoComercialParaExportacaoInternacional, 'id'>;

type OnsGeracaoComercialParaExportacaoInternacionalFormGroupContent = {
  id: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['id'] | NewOnsGeracaoComercialParaExportacaoInternacional['id']>;
  nomConversora: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['nomConversora']>;
  dinInstante: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['dinInstante']>;
  valModalidadecontratual: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['valModalidadecontratual']>;
  valModalidadeemergencial: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['valModalidadeemergencial']>;
  valModalidadeoportunidade: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['valModalidadeoportunidade']>;
  valModalidadeteste: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['valModalidadeteste']>;
  valModalidadeexcepcional: FormControl<IOnsGeracaoComercialParaExportacaoInternacional['valModalidadeexcepcional']>;
};

export type OnsGeracaoComercialParaExportacaoInternacionalFormGroup =
  FormGroup<OnsGeracaoComercialParaExportacaoInternacionalFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OnsGeracaoComercialParaExportacaoInternacionalFormService {
  createOnsGeracaoComercialParaExportacaoInternacionalFormGroup(
    onsGeracaoComercialParaExportacaoInternacional: OnsGeracaoComercialParaExportacaoInternacionalFormGroupInput = { id: null },
  ): OnsGeracaoComercialParaExportacaoInternacionalFormGroup {
    const onsGeracaoComercialParaExportacaoInternacionalRawValue = {
      ...this.getFormDefaults(),
      ...onsGeracaoComercialParaExportacaoInternacional,
    };
    return new FormGroup<OnsGeracaoComercialParaExportacaoInternacionalFormGroupContent>({
      id: new FormControl(
        { value: onsGeracaoComercialParaExportacaoInternacionalRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      nomConversora: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.nomConversora),
      dinInstante: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.dinInstante),
      valModalidadecontratual: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.valModalidadecontratual),
      valModalidadeemergencial: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.valModalidadeemergencial),
      valModalidadeoportunidade: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.valModalidadeoportunidade),
      valModalidadeteste: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.valModalidadeteste),
      valModalidadeexcepcional: new FormControl(onsGeracaoComercialParaExportacaoInternacionalRawValue.valModalidadeexcepcional),
    });
  }

  getOnsGeracaoComercialParaExportacaoInternacional(
    form: OnsGeracaoComercialParaExportacaoInternacionalFormGroup,
  ): IOnsGeracaoComercialParaExportacaoInternacional | NewOnsGeracaoComercialParaExportacaoInternacional {
    return form.getRawValue() as IOnsGeracaoComercialParaExportacaoInternacional | NewOnsGeracaoComercialParaExportacaoInternacional;
  }

  resetForm(
    form: OnsGeracaoComercialParaExportacaoInternacionalFormGroup,
    onsGeracaoComercialParaExportacaoInternacional: OnsGeracaoComercialParaExportacaoInternacionalFormGroupInput,
  ): void {
    const onsGeracaoComercialParaExportacaoInternacionalRawValue = {
      ...this.getFormDefaults(),
      ...onsGeracaoComercialParaExportacaoInternacional,
    };
    form.reset(
      {
        ...onsGeracaoComercialParaExportacaoInternacionalRawValue,
        id: { value: onsGeracaoComercialParaExportacaoInternacionalRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): OnsGeracaoComercialParaExportacaoInternacionalFormDefaults {
    return {
      id: null,
    };
  }
}

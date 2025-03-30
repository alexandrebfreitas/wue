import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent } from './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento-detail.component';

describe('OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento Management Detail Component', () => {
  let comp: OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import(
                  './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento-detail.component'
                ).then(m => m.OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent),
              resolve: { onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento: () => of({ id: 2357 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(
      OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent,
    );
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl(
        '/',
        OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoDetailComponent,
      );

      // THEN
      expect(instance.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento()).toEqual(
        expect.objectContaining({ id: 2357 }),
      );
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});

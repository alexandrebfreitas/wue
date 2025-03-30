import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent } from './ons-indicadores-confiabilidade-rede-basica-dreq-freq-detail.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaDreqFreq Management Detail Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-indicadores-confiabilidade-rede-basica-dreq-freq-detail.component').then(
                  m => m.OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent,
                ),
              resolve: { onsIndicadoresConfiabilidadeRedeBasicaDreqFreq: () => of({ id: 4905 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIndicadoresConfiabilidadeRedeBasicaDreqFreq on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIndicadoresConfiabilidadeRedeBasicaDreqFreqDetailComponent);

      // THEN
      expect(instance.onsIndicadoresConfiabilidadeRedeBasicaDreqFreq()).toEqual(expect.objectContaining({ id: 4905 }));
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

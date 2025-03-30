import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent } from './ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-detail.component';

describe('OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs Management Detail Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-indicadores-confiabilidade-rede-basica-robustez-rmal-rmcs-rrb-e-rrbcs-detail.component').then(
                  m => m.OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent,
                ),
              resolve: { onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs: () => of({ id: 8009 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcsDetailComponent);

      // THEN
      expect(instance.onsIndicadoresConfiabilidadeRedeBasicaRobustezRmalRmcsRrbERrbcs()).toEqual(expect.objectContaining({ id: 8009 }));
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

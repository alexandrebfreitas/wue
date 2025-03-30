import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCapacidadeTransformacaoRedeBasicaDetailComponent } from './ons-capacidade-transformacao-rede-basica-detail.component';

describe('OnsCapacidadeTransformacaoRedeBasica Management Detail Component', () => {
  let comp: OnsCapacidadeTransformacaoRedeBasicaDetailComponent;
  let fixture: ComponentFixture<OnsCapacidadeTransformacaoRedeBasicaDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCapacidadeTransformacaoRedeBasicaDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-capacidade-transformacao-rede-basica-detail.component').then(
                  m => m.OnsCapacidadeTransformacaoRedeBasicaDetailComponent,
                ),
              resolve: { onsCapacidadeTransformacaoRedeBasica: () => of({ id: 25632 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCapacidadeTransformacaoRedeBasicaDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCapacidadeTransformacaoRedeBasicaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCapacidadeTransformacaoRedeBasica on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCapacidadeTransformacaoRedeBasicaDetailComponent);

      // THEN
      expect(instance.onsCapacidadeTransformacaoRedeBasica()).toEqual(expect.objectContaining({ id: 25632 }));
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

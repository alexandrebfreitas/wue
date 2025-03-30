import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsDadosIntercambioEnergiaModalidadeDetailComponent } from './ons-dados-intercambio-energia-modalidade-detail.component';

describe('OnsDadosIntercambioEnergiaModalidade Management Detail Component', () => {
  let comp: OnsDadosIntercambioEnergiaModalidadeDetailComponent;
  let fixture: ComponentFixture<OnsDadosIntercambioEnergiaModalidadeDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsDadosIntercambioEnergiaModalidadeDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-dados-intercambio-energia-modalidade-detail.component').then(
                  m => m.OnsDadosIntercambioEnergiaModalidadeDetailComponent,
                ),
              resolve: { onsDadosIntercambioEnergiaModalidade: () => of({ id: 15687 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsDadosIntercambioEnergiaModalidadeDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsDadosIntercambioEnergiaModalidadeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsDadosIntercambioEnergiaModalidade on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsDadosIntercambioEnergiaModalidadeDetailComponent);

      // THEN
      expect(instance.onsDadosIntercambioEnergiaModalidade()).toEqual(expect.objectContaining({ id: 15687 }));
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

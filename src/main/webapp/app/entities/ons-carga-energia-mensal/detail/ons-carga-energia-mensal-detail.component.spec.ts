import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsCargaEnergiaMensalDetailComponent } from './ons-carga-energia-mensal-detail.component';

describe('OnsCargaEnergiaMensal Management Detail Component', () => {
  let comp: OnsCargaEnergiaMensalDetailComponent;
  let fixture: ComponentFixture<OnsCargaEnergiaMensalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsCargaEnergiaMensalDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-carga-energia-mensal-detail.component').then(m => m.OnsCargaEnergiaMensalDetailComponent),
              resolve: { onsCargaEnergiaMensal: () => of({ id: 16554 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsCargaEnergiaMensalDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsCargaEnergiaMensalDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsCargaEnergiaMensal on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsCargaEnergiaMensalDetailComponent);

      // THEN
      expect(instance.onsCargaEnergiaMensal()).toEqual(expect.objectContaining({ id: 16554 }));
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

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsTaxasTeifaETeipDetailComponent } from './ons-taxas-teifa-e-teip-detail.component';

describe('OnsTaxasTeifaETeip Management Detail Component', () => {
  let comp: OnsTaxasTeifaETeipDetailComponent;
  let fixture: ComponentFixture<OnsTaxasTeifaETeipDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsTaxasTeifaETeipDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./ons-taxas-teifa-e-teip-detail.component').then(m => m.OnsTaxasTeifaETeipDetailComponent),
              resolve: { onsTaxasTeifaETeip: () => of({ id: 9188 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsTaxasTeifaETeipDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsTaxasTeifaETeipDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsTaxasTeifaETeip on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsTaxasTeifaETeipDetailComponent);

      // THEN
      expect(instance.onsTaxasTeifaETeip()).toEqual(expect.objectContaining({ id: 9188 }));
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

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsBalancoEnergiaDessemDetailComponent } from './ons-balanco-energia-dessem-detail.component';

describe('OnsBalancoEnergiaDessem Management Detail Component', () => {
  let comp: OnsBalancoEnergiaDessemDetailComponent;
  let fixture: ComponentFixture<OnsBalancoEnergiaDessemDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsBalancoEnergiaDessemDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-balanco-energia-dessem-detail.component').then(m => m.OnsBalancoEnergiaDessemDetailComponent),
              resolve: { onsBalancoEnergiaDessem: () => of({ id: 3629 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsBalancoEnergiaDessemDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsBalancoEnergiaDessemDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsBalancoEnergiaDessem on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsBalancoEnergiaDessemDetailComponent);

      // THEN
      expect(instance.onsBalancoEnergiaDessem()).toEqual(expect.objectContaining({ id: 3629 }));
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

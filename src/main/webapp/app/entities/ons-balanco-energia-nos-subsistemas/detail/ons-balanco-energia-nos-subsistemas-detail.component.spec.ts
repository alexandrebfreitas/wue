import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { OnsBalancoEnergiaNosSubsistemasDetailComponent } from './ons-balanco-energia-nos-subsistemas-detail.component';

describe('OnsBalancoEnergiaNosSubsistemas Management Detail Component', () => {
  let comp: OnsBalancoEnergiaNosSubsistemasDetailComponent;
  let fixture: ComponentFixture<OnsBalancoEnergiaNosSubsistemasDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OnsBalancoEnergiaNosSubsistemasDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () =>
                import('./ons-balanco-energia-nos-subsistemas-detail.component').then(
                  m => m.OnsBalancoEnergiaNosSubsistemasDetailComponent,
                ),
              resolve: { onsBalancoEnergiaNosSubsistemas: () => of({ id: 3586 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(OnsBalancoEnergiaNosSubsistemasDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OnsBalancoEnergiaNosSubsistemasDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load onsBalancoEnergiaNosSubsistemas on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', OnsBalancoEnergiaNosSubsistemasDetailComponent);

      // THEN
      expect(instance.onsBalancoEnergiaNosSubsistemas()).toEqual(expect.objectContaining({ id: 3586 }));
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

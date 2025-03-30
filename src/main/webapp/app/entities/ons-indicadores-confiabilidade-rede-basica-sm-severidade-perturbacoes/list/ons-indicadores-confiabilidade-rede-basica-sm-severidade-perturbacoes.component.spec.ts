import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subject, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { sampleWithRequiredData } from '../ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.test-samples';
import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService } from '../service/ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.service';

import { OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent } from './ons-indicadores-confiabilidade-rede-basica-sm-severidade-perturbacoes.component';
import SpyInstance = jest.SpyInstance;

describe('OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes Management Component', () => {
  let comp: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent;
  let fixture: ComponentFixture<OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent>;
  let service: OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent],
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            ),
            snapshot: {
              queryParams: {},
              queryParamMap: jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              }),
            },
          },
        },
      ],
    })
      .overrideTemplate(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    jest
      .spyOn(service, 'query')
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 25854 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=1&size=20>; rel="next"',
            }),
          }),
        ),
      )
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 10204 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=0&size=20>; rel="prev",<http://localhost/api/foo?page=2&size=20>; rel="next"',
            }),
          }),
        ),
      );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoes()[0]).toEqual(expect.objectContaining({ id: 25854 }));
  });

  describe('trackId', () => {
    it('Should forward to onsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesService', () => {
      const entity = { id: 25854 };
      jest.spyOn(service, 'getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier');
      const id = comp.trackId(entity);
      expect(service.getOnsIndicadoresConfiabilidadeRedeBasicaSmSeveridadePerturbacoesIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });

  it('should calculate the sort attribute for a non-id attribute', () => {
    // WHEN
    comp.navigateToWithComponentValues({ predicate: 'non-existing-column', order: 'asc' });

    // THEN
    expect(routerNavigateSpy).toHaveBeenLastCalledWith(
      expect.anything(),
      expect.objectContaining({
        queryParams: expect.objectContaining({
          sort: ['non-existing-column,asc'],
        }),
      }),
    );
  });

  it('should calculate the sort attribute for an id', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenLastCalledWith(expect.objectContaining({ sort: ['id,desc'] }));
  });

  describe('delete', () => {
    let ngbModal: NgbModal;
    let deleteModalMock: any;

    beforeEach(() => {
      deleteModalMock = { componentInstance: {}, closed: new Subject() };
      // NgbModal is not a singleton using TestBed.inject.
      // ngbModal = TestBed.inject(NgbModal);
      ngbModal = (comp as any).modalService;
      jest.spyOn(ngbModal, 'open').mockReturnValue(deleteModalMock);
    });

    it('on confirm should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next('deleted');
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).toHaveBeenCalled();
      }),
    ));

    it('on dismiss should call load', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(comp, 'load');

        // WHEN
        comp.delete(sampleWithRequiredData);
        deleteModalMock.closed.next();
        tick();

        // THEN
        expect(ngbModal.open).toHaveBeenCalled();
        expect(comp.load).not.toHaveBeenCalled();
      }),
    ));
  });
});

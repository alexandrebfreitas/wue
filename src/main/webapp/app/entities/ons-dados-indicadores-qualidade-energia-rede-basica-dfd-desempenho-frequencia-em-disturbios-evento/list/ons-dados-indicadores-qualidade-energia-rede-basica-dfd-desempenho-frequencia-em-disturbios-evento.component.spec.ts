import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpHeaders, HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subject, of } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { sampleWithRequiredData } from '../ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento.test-samples';
import { OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoService } from '../service/ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento.service';

import { OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoComponent } from './ons-dados-indicadores-qualidade-energia-rede-basica-dfd-desempenho-frequencia-em-disturbios-evento.component';
import SpyInstance = jest.SpyInstance;

describe('OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEvento Management Component', () => {
  let comp: OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoComponent;
  let fixture: ComponentFixture<OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoComponent>;
  let service: OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoService;
  let routerNavigateSpy: SpyInstance<Promise<boolean>>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoComponent],
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
      .overrideTemplate(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(OnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoService);
    routerNavigateSpy = jest.spyOn(comp.router, 'navigate');

    jest
      .spyOn(service, 'query')
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 2357 }],
            headers: new HttpHeaders({
              link: '<http://localhost/api/foo?page=1&size=20>; rel="next"',
            }),
          }),
        ),
      )
      .mockReturnValueOnce(
        of(
          new HttpResponse({
            body: [{ id: 20631 }],
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
    expect(comp.onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventos()[0]).toEqual(
      expect.objectContaining({ id: 2357 }),
    );
  });

  describe('trackId', () => {
    it('Should forward to onsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoService', () => {
      const entity = { id: 2357 };
      jest.spyOn(service, 'getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoIdentifier');
      const id = comp.trackId(entity);
      expect(
        service.getOnsDadosIndicadoresQualidadeEnergiaRedeBasicaDfdDesempenhoFrequenciaEmDisturbiosEventoIdentifier,
      ).toHaveBeenCalledWith(entity);
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

import * as L from 'leaflet';
import 'leaflet-polylinedecorator';
import 'leaflet-groupedlayercontrol';
import 'leaflet-draw';
import * as turf from '@turf/turf';
import {
  ChangeDetectorRef,
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
  ViewChildren,
} from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { latLng, tileLayer, Map } from 'leaflet';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { LeafletMarkerClusterModule } from '@asymmetrik/ngx-leaflet-markercluster';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { GareService } from 'app/services/gare.service';
import { PNiveauService } from 'app/services/pniveau.service';
import { EtablissementsScolaires } from 'app/services/etablissements-scolaires.service';
import { IsochronesService } from 'app/services/isochrones.service';
import { DonneeArretService } from 'app/services/donneeArret.service';
import { FrequentationService } from 'app/services/frequentation.service';
import { DonneeIsochroneService } from 'app/services/donnee-isochrone.service';

import { AutoUnsubscribe } from 'layout/auto-unsubscribe.decorator';
import { ReseauFerroviaireService } from 'app/services/reseau-ferroviaire.service';
import { ChartModule } from 'primeng/chart';
import { AccordionDirective } from 'layout/shared/accordion.directive';
import { AccordionModule } from 'primeng/accordion';
import { InstallationTermService } from 'app/services/installation-term';
import { ChipModule } from 'primeng/chip';
import { TooltipModule } from 'primeng/tooltip';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormActionService } from 'layout/api/formactionservice';
import { NgSelectModule } from '@ng-select/ng-select';
import { TableModule } from 'primeng/table';
import { Subscription } from 'rxjs';
import { CheckboxModule } from 'primeng/checkbox';
import { SliderModule } from 'primeng/slider';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { CommunesServices } from 'app/services/communes.services';
import { RegionsServices } from 'app/services/regions.services';
import { DptRegionsServices } from 'app/services/dpt-regions.service';
import { DepartementsService } from 'app/services/departements.service';
import { DonneeVoyageursService } from 'app/services/donneeVoyageurs.service';
import { CommunesDetailsService } from 'app/services/communes-details.service';

import { Item, GroupedItem } from '../../interfaces/GeoUtils';
import IDepartements from '../../interfaces/IDepartements';
import IQInfoByIsochrone from "../../interfaces/IQInfoByIsochrone";

@Component({
  imports: [
    ToastModule,
    SliderModule,
    CheckboxModule,
    NgSelectModule,
    AutoCompleteModule,
    TooltipModule,
    ChipModule,
    AccordionModule,
    AccordionDirective,
    ChartModule,
    DropdownModule,
    ButtonModule,
    SidebarModule,
    LeafletModule,
    CommonModule,
    FormsModule,
    ScrollPanelModule,
    LeafletMarkerClusterModule,
    TableModule,
  ],
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss'],
  standalone: true,
  providers: [MessageService],
})
@AutoUnsubscribe
export class RootComponent implements OnInit {
  constructor(
    private dptRegions: DptRegionsServices,
    private communesDetails: CommunesDetailsService,
    private donneeVoyageur: DonneeVoyageursService,
    private regions: RegionsServices,
    private departements: DepartementsService,
    private communes: CommunesServices,
    private messageService: MessageService,
    private gares: GareService,
    private pNiveau: PNiveauService,
    private scolar: EtablissementsScolaires,
    private isochrones: IsochronesService,
    private reseauxFerroviaires: ReseauFerroviaireService,
    private cdref: ChangeDetectorRef,
    private dataArret: DonneeArretService,
    private frequentation: FrequentationService,
    private donneeIsochrone: DonneeIsochroneService,
    private etablissements: InstallationTermService,
    private renderer: Renderer2,
    private actionService: FormActionService
  ) {}

  @ViewChildren('accordionContent') accordionContent: ElementRef;

  private subscription: Subscription;
  sumUpYear: string = '';
  selectedPassagesANiveau: any[] = [];
  isEraserVisible: boolean = false;
  currentLayer: any = {};
  FirstTime: boolean = true;
  drawnItems: L.FeatureGroup;
  layersControl: L.Control.Layers;
  height: number = 0;
  top: number = 70;
  lastSelectedITE: L.Marker | null = null;
  lastDrawnLayer: any = null;
  searchData: any[] = [];
  selectedSearch: any;
  nbStats: any = {};
  statsFrequentation: any = {};
  statsFrequentationParGare: any = {};
  lastYearFrequentation: number = 0;
  valueOpacity: number = 0;
  statTitle: String = '';
  gareOuverte: any[] = [];
  gareClosed: any[] = [];
  isochroneLayers: { [key: string]: L.LayerGroup } = {};
  groupedLayerControl: any = {};
  polygonLayer: any;
  groupedItems: GroupedItem[];
  selectedItem: Item;
  searchItem: any = {};
  lastSelectedItem: string = '';
  selectedBaseLayer: string = 'OSM';
  aoms: any = {};
  first = 0;
  disabledExport: boolean = false;
  displaySideMenu: boolean = false;
  zOffets = {
    GaresOuvertes: 1500,
    GaresClosed: 1400,
    ITEUtilise: 1300,
    ITENeuves: 1200,
    ITEBon: 1100,
    ITEMauvais: 1000,
    ITEInutile: 900,
    PN: 800,
    CollegesLycee: 700,
    Isochrone15: 600,
    Isochrone30: 500,
    Isochrone10: 400,
    ReseauFerroviaire: 300,
  };
  datastore: any = {};
  datalayers: any = {};
  displayStatsPanel: boolean = false;
  Markers: any = {};
  currentMarker: any = {};
  curLatitude: any = null;
  curLongitude: any = null;
  clusterEnabled: boolean = true;
  Pictures: string[] = [];
  myLegendes: any[] = [];
  alreadyLoadedIsochrone: any[] = [];
  alreadyLoadedPNiveau: any[] = [];
  selectedIsochrones: L.LayerGroup | null = null;
  dataIsochrone: any = {};
  marks: any = {};
  emptyCurrentData: boolean = true;
  currentData: any[] = [];
  dataF: any;
  optionsF: any;
  optionsStackedF: any;
  optionsStackedFreq: any;
  dataA: any;
  optionsA: any;
  totalArrets: number = 0;
  polygonLayerGroup: any = {};
  SearchList: any[] = [];
  selectedGare: any = null;
  selectedGareRegion: string = '';
  selectedITE: any = null;
  Legende: boolean = false;
  listGares: any[] = [];
  listGaresClosed: any[] = [];
  overlayMaps: any = {};
  statLabel: string = '';
  lastSelectedSearch: string = '';
  currentITE: string = '';
  currentGareIcon: string = '';
  refIsochrones: any[] = [];
  colors: any[] = ['red', 'green', 'blue'];
  polygons: any[] = [];
  GMenu: any = {
    iso1: true,
    iso2: true,
    iso3: false,
    isGareOpen: true,
    isGareClosed: true,
    pn: false,
    es: false,
    ite_neuf: false,
    ite_utilise: false,
    ite_bon: false,
    ite_inutile: false,
    ite_mauvais: false,
    fer_lgv: true,
    fer_mixte: true,
    fer_fret: true,
    fer_off: true,
  };

  displayInfo: boolean = false;
  displayInfoITE: boolean = false;

  overlaysGroup = L.layerGroup();

  expandedMarker: L.Marker | null = null;

  map: any;

  isochroneLayerGare: any = {};

  display0: boolean = false;
  display1: boolean = false;
  display2: boolean = false;
  display3: boolean = false;
  display4: boolean = false;
  display5: boolean = false;
  display6: boolean = false;

  /** colors */
  bluePastelTranslucent = 'rgba(173, 216, 230, 0.3)';
  redPastelTranslucent = 'rgba(255, 182, 193, 0.3)';
  blue500 = 'rgba(59, 130, 246, 0.3)';
  green500 = 'rgba(34, 197, 94, 0.3)';
  yellow500 = 'rgba(234, 179, 8, 0.3)';
  cyan500 = 'rgba(6, 182, 212, 0.3)';
  red500 = 'rgba(239, 68, 68, 0.3)';
  blue800 = 'rgba(37, 99, 235, 0.3)';
  green800 = 'rgba(22, 163, 74, 0.3)';
  yellow800 = 'rgba(202, 138, 4, 0.3)';
  cyan800 = 'rgba(8, 145, 178, 0.3)';
  red800 = 'rgba(185, 28, 28, 0.3)';

  /** stats */
  statsGareOnOff: any = {};
  optionsGareOnOff: any = {};
  statsBassinPopulation: any = {};
  optionsBassinPopulation: any = {};
  statsAmenities: any = {};
  optionsAmenities: any = {};
  tableStacks: any[] = [];
  dataDesserte: any = {};
  optionsStacked: any = {};
  optionsStackedNoLegend: any = {};
  dataAmenitiesParGare: any = {};
  optionsAmenitiesParGare: any = {};
  dataPopulationParGare: any = {};

  /** ne pas afficher la légende pour fréquentation */
  optionsFrequentation = {
    responsive: true,
    maintainAspectRatio: false,
    aspectRatio: 1.3,
    height: 500,
    plugins: {
      legend: {
        display: true,
      },
    },
  };

  /** Définition de la map */
  LayerOSM = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    minZoom: 6,
    attribution: '...',
  });

  LayerTransport = tileLayer(
    'https://data.geopf.fr/private/wmts?apikey=ign_scan_ws&layer=GEOGRAPHICALGRIDSYSTEMS.MAPS&style=normal&tilematrixset=PM&Service=WMTS&Request=GetTile&Version=1.0.0&Format=image/jpeg&TileMatrix={z}&TileCol={x}&TileRow={y}',
    {
      tileSize: 256, // Taille par défaut des tuiles, ajuste si nécessaire
      zoomOffset: 0, // Dépend des spécificités de la couche
      minZoom: 0,
      maxZoom: 18, // Ajuste en fonction du zoom supporté
      attribution: '&copy; <a href="https://data.geopf.fr/">GéoPF</a>', // Attribution, à ajuster si nécessaire
    }
  );

  LayerSat = tileLayer(
    'https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}',
    {
      attribution: 'Tiles © Esri',
    }
  );

  LayerIGN = tileLayer(
    'https://data.geopf.fr/wmts?SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=GEOGRAPHICALGRIDSYSTEMS.PLANIGNV2&STYLE=normal&TILEMATRIXSET=PM&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}&FORMAT=image/png',
    {
      minZoom: 0,
      maxZoom: 18,
      attribution: 'IGN-F/Geoportail',
      tileSize: 256, // les tuiles du Géooportail font 256x256px
    }
  );

  // Constants

  /** init */
  latitude = 46.603354;
  longitude = 1.888334;
  zoom = 7;

  private readonly ICON_SIZES = {
    VERY_SMALL: { width: 15, height: 15 },
    SMALL: { width: 25, height: 36 },
    MEDIUM: { width: 35, height: 46 },
    LARGE: { width: 50, height: 72 },
  };

  private readonly ZOOM_LEVELS = {
    SMALL: 6,
    MEDIUM: 9,
    LARGE: 12,
  };

  private readonly ICON_CONFIGS = {
    GARE: {
      url: 'assets/images/marker-gares.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 1500,
    },
    GARE_CLOSED: {
      url: 'assets/images/marker-gares-closed.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 1400,
    },
    ITE_UTILE: {
      url: 'assets/images/marker-ite_utile.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 1300,
    },
    ITE_NEUF: {
      url: 'assets/images/marker-ite_neuf.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 1200,
    },
    ITE_BON: {
      url: 'assets/images/marker-ite_bon.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 1100,
    },
    ITE_MAUVAIS: {
      url: 'assets/images/marker-ite_mauvais.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 1000,
    },
    ITE_INUTILE: {
      url: 'assets/images/marker-ite_inutile.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 900,
    },
    PN: {
      url: 'assets/images/marker-pniveau.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 800,
    },
    ECOLE: {
      url: 'assets/images/marker-ecole.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
      zIndexOffset: 700,
    },
  };

  options = {
    detectRetina: false,
    layers: [this.LayerOSM],
    zoom: this.zoom,
    center: latLng(this.latitude, this.longitude),
  };

  baseMaps = {};

  setLayerOpacity() {
    const value = 100 - this.valueOpacity;
    this.currentLayer.setOpacity(value / 100);
  }

  toggleLayer(itemTitle: string, shouldBeVisible: boolean) {
    const layerGroup = this.overlayMaps[itemTitle];
    if (layerGroup) {
      if (shouldBeVisible) {
        this.map.addLayer(layerGroup); // Ajouter la couche à la carte
      } else {
        this.map.removeLayer(layerGroup); // Retirer la couche de la carte
      }
    } else {
      console.warn(`La couche "${itemTitle}" n'existe pas.`);
    }
  }

  onMenuChange(event?: any) {
    if (this.GMenu.fer_lgv === false) {
      this.toggleLayer('LGV', false);
    } else {
      this.toggleLayer('LGV', true);
    }
    if (this.GMenu.iso1 === false) {
      this.toggleIsochroneLayer('15 minutes à pied', false);
      this.resetIsochronesHighlight();
    } else {
      this.toggleIsochroneLayer('15 minutes à pied', true);
      if (this.selectedGare) this.loadMyIsochrone(this.selectedGare.id, true);
    }
    if (this.GMenu.iso2 === false) {
      this.toggleIsochroneLayer('30 minutes à pied', false);
      this.resetIsochronesHighlight();
    } else {
      this.toggleIsochroneLayer('30 minutes à pied', true);
      if (this.selectedGare) this.loadMyIsochrone(this.selectedGare.id, true);
    }
    if (this.GMenu.iso3 === false) {
      this.toggleIsochroneLayer('10 minutes en voiture', false);
      this.resetIsochronesHighlight();
    } else {
      this.toggleIsochroneLayer('10 minutes en voiture', true);
      if (this.selectedGare) this.loadMyIsochrone(this.selectedGare.id, true);
    }
    if (this.GMenu.fer_mixte === false) {
      this.toggleLayer('Double voie', false);
      this.toggleLayer('Double voie électrifiée', false);
      this.toggleLayer('Voie unique électrifiée', false);
      this.toggleLayer('Voie unique', false);
    } else {
      this.toggleLayer('Double voie', true);
      this.toggleLayer('Double voie électrifiée', true);
      this.toggleLayer('Voie unique électrifiée', true);
      this.toggleLayer('Voie unique', true);
    }
    if (this.GMenu.fer_fret === false) {
      this.toggleLayer('Double voie fret', false);
      this.toggleLayer('Double voie fret électrifiée', false);
      this.toggleLayer('Voie unique fret électrifiée', false);
      this.toggleLayer('Voie unique fret', false);
      this.toggleLayer('Double voie électrifiée fret', false);
    } else {
      this.toggleLayer('Double voie fret', true);
      this.toggleLayer('Double voie fret électrifiée', true);
      this.toggleLayer('Voie unique fret électrifiée', true);
      this.toggleLayer('Voie unique fret', true);
      this.toggleLayer('Double voie électrifiée fret', true);
    }
    if (this.GMenu.fer_off === false) {
      this.toggleLayer('Voie unique suspendue', false);
      this.toggleLayer('Voie unique fret suspendue', false);
      this.toggleLayer('Double voie suspendue', false);
      this.toggleLayer('Double voie électrifiée suspendue', false);
    } else {
      this.toggleLayer('Voie unique suspendue', true);
      this.toggleLayer('Voie unique fret suspendue', true);
      this.toggleLayer('Double voie suspendue', true);
      this.toggleLayer('Double voie électrifiée suspendue', true);
    }

    const layers = [
      'ite_utilise',
      'ite_bon',
      'ite_inutile',
      'ite_mauvais',
      'ite_neuf',
    ];

    layers.forEach((layer) => {
      if (this.GMenu[layer] === false) {
        this.map.removeLayer(this.datalayers[layer].markersCluster);
      } else {
        this.map.addLayer(this.datalayers[layer].markersCluster);
      }
    });

    if (this.GMenu.isGareClosed === false) {
      this.map.removeLayer(this.datalayers.garesClosed.markersCluster);
      this.hideAllIsochrones();
    } else {
      this.map.addLayer(this.datalayers.garesClosed.markersCluster);
      this.showAllIsochrones();
    }

    if (this.GMenu.isGareOpen === false) {
      this.map.removeLayer(this.datalayers.gares.markersCluster);
      this.hideAllIsochrones();
    } else {
      this.map.addLayer(this.datalayers.gares.markersCluster);
      this.showAllIsochrones();
    }
    if (this.GMenu.pn === false) {
      this.map.removeLayer(this.datalayers.pn.markersCluster);
    } else {
      this.map.addLayer(this.datalayers.pn.markersCluster);
    }
    if (this.GMenu.es === false) {
      this.map.removeLayer(this.datalayers.es.markersCluster);
    } else {
      this.map.addLayer(this.datalayers.es.markersCluster);
    }
    this.handleMapChange();
  }

  onGareClosedChange(event: any) {
    console.log('Checkbox changed:', event.checked); // Vérifier le changement
  }

  ngAfterViewInit() {
    this.adjustAccordionHeight();
  }

  changeMap(map: any) {
    this.map.removeLayer(this.currentLayer);
    this.map.addLayer(map);
    this.currentLayer = map;
    this.setLayerOpacity();
  }

  adjustAccordionHeight() {
    const content = this.accordionContent.nativeElement;
    let contentHeight;
    // Obtenez la hauteur réelle du contenu
    if (content) {
      contentHeight = content.scrollHeight;

      // Appliquez cette hauteur pour forcer l'affichage complet du contenu
      this.renderer.setStyle(content, 'height', `${contentHeight}px`);
    }
  }

  // Appelez cette fonction à chaque fois que le contenu change
  ngOnChanges() {
    this.adjustAccordionHeight();
  }

  groupByFn = (item: any) => item.group;

  closeStatsSidebar() {
    this.displayStatsPanel = false;
    this.cdref.detectChanges();
  }

  hideStatsSidebar() {
    this.displayStatsPanel = false;
    this.cdref.detectChanges();
  }

  onOpen() {
    this.hideStatsSidebar();
    this.displayInfo = false;
    this.displayInfoITE = false;
    this.selectedSearch = '';
    this.deleteLastSearch();
    this.cdref.detectChanges();
  }

  resetTable() {
    this.first = 0;
  }

  // Search functions
  onSearch(event: string) {
    if (!event) return;
    this.selectedPassagesANiveau = null;
    const type = event.split('-')[0];
    const id = event.split('-')[1];
    this.deleteLastSearch();
    this.lastSelectedSearch = id;
    this.resetMarkerGare();
    /** sélection gares */
    if (type == 'gare') {
      const item = this.datastore.gares.filter((o: any) => o.id == id);
      this.moveToLocation({ value: item[0] });
    }
    /** sélection région */
    if (type == 'region') {
      this.regions.getRegionsById(id).subscribe({
        next: (data: any) => {
          this.isEraserVisible = true;
          this.statTitle = 'Informations agrégées sur la région ' + data[0].nom;
          const geom = data[0].geom;
          this.selectedPassagesANiveau = this.checkPNInMultiPolygon(geom);
          this.dptRegions.checkRegion(data[0].code).subscribe({
            next: (p: any) => {
              let codes: any[] = [];
              for (let i = 0; i < p.length; i++)
                codes.push(p[i].departmentCode);
              const gares = this.datastore.gares.filter((o: any) => {
                return codes.includes(o.inseeDepartement);
              });
              this.stats(gares);
              this.gotoLocation(data, 7);
              this.displayStatsPanel = true;
              this.displayInfoITE = false;
              this.displayInfo = false;
            },
          });
        },
      });
    }
    /** sélection Départements */
    if (type == 'dpt') {
      this.departements.getDepartementsById(id).subscribe({
        next: (data: IDepartements[]) => {
          this.isEraserVisible = true;
          this.statTitle =
            'Informations agrégées sur le département ' + data[0].nom;
          const p = this.datastore.gares.filter(
            (o: any) => o.inseeDepartement == data[0].code
          );
          const geom = data[0].geom;
          this.selectedPassagesANiveau = this.checkPNInMultiPolygon(geom);
          this.stats(p);
          this.gotoLocation(data, 7);
          this.displayStatsPanel = true;
          this.displayInfoITE = false;
          this.displayInfo = false;
        },
      });
    }
    /** sélection AOM */
    if (type == 'aom') {
      this.statTitle = "Informations agrégées sur l'AOM " + id;
      if (!this.aoms[id]) {
        this.messageService.add({
          severity: 'error',
          summary: 'Erreur',
          detail: 'Aucune statistique pour cet AOM',
        });
        return this.cdref.detectChanges();
      }
      this.addAOMPolygon(this.aoms[id]);
      const p = this.datastore.gares.filter((o: any) => {
        return o.nomAom == id;
      });

      this.stats(p);
      this.displayStatsPanel = true;
      this.displayInfoITE = false;
      this.displayInfo = false;
    }
    /** sélection Communes */
    if (type == 'commune') {
      this.communesDetails.lookUp(id).subscribe({
        next: (data) => {
          let insee: any;
          this.isEraserVisible = true;
          if (data.length > 0) insee = data[0].insee;
          this.gares.getGareByCommune(insee).subscribe({
            next: (dta) => {
              if (dta.length == 0) {
                this.displayInfoITE = false;
                this.displayInfo = false;
                this.displayStatsPanel = false;
                return this.gotoLocation(data);
              }

              if (dta.length == 1) {
                let id = dta[0].id;
                const item = this.datastore.gares.filter(
                  (o: any) => o.id == id
                );
                this.moveToLocation({ value: item[0] });
                return;
              }
              this.statTitle = `Informations agrégées sur la commune ${dta[0].nomCommune} (${insee})`;
              const geom = data[0].geom;
              this.selectedPassagesANiveau = this.checkPNInMultiPolygon(geom);
              this.gotoLocation(data);
              this.stats(dta);
              this.displayStatsPanel = true;
              this.displayInfoITE = false;
              this.displayInfo = false;
            },
          });
        },
        error: (err) => {},
      });
    }
  }

  // Clear search
  onSearchClear() {
    this.hideITESidebar();
    this.hideStatsSidebar();
    this.displayInfo = false;
    this.deleteLastSearch();
    this.resetIsochronesHighlight();
  }

  // Exporting CSV
  exportCSV() {
    this.disabledExport = true;
    const transformJsonData = (inputData: any) => {
      const output: any = {};
      for (let i = 0; i < inputData.length; i++) {
        const item = inputData[i];

        if (!output[item.gareId]) {
          output[item.gareId] = {
            codeUic: item.codeUic,
            //id: item.gareId,
            inseeCommune: item.inseeCommune,
            inseeDepartement: item.inseeDepartement,
            nomAom: item.nomAom,
            nomCommune: item.nomCommune,
            nomGare: item.nomGare,
            siAutomatesTer: item.siAutomatesTer,
            siAutomatesTgvIntercites: item.siAutomatesTgvIntercites,
            siLibreServiceAssiste: item.siLibreServiceAssiste,
            siOuverte: item.siOuverte === true,
            siPosteVenteGuichet: item.siPosteVenteGuichet,
          };
        }
        let label = '';
        let yearLabel = 'totalArrets';
        if (item.refTypeArretId == 1) label = 'arretsCars';
        if (item.refTypeArretId == 2) label = 'arretsTer';
        if (item.refTypeArretId == 3) label = 'arretsIntercites';
        if (item.refTypeArretId == 4) label = 'arretsTgv';

        if (item.annee) {
          label += item.annee;
          yearLabel += item.annee;
        }
        output[item.gareId][label] = item.nombreArret;
        let compteArret = 0;
        if (item.nombreArret) compteArret = item.nombreArret * 1;
        if (!output[item.gareId][yearLabel]) output[item.gareId][yearLabel] = 0;
        const total = output[item.gareId][yearLabel] * 1 + compteArret;
        output[item.gareId][yearLabel] = total;
      }
      return output;
    };

    const generatePrefix = () => {
      const today = new Date();

      // Récupère l'année, le mois et le jour sous forme AAAAMMJJ
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, '0'); // Les mois vont de 0 à 11, donc ajouter 1
      const day = String(today.getDate()).padStart(2, '0'); // Ajoute un zéro au début si nécessaire

      // Créer le préfixe en y ajoutant les informations spécifiques
      const prefix = `${year}${month}${day}_geofer_gares`;

      return prefix;
    };

    const removeAccents = (str: string) => {
      return str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
    };

    function reorganizeData(inputDataObject: any, fieldOrder: string[]): any {
      let result: { [key: string]: any } = {}; // Déclare result avec des clés dynamiques

      // Parcours de chaque clé dans l'objet, mais on ignore les clés
      for (let el in inputDataObject) {
        const item = inputDataObject[el];
        console.log(item);
        let result: any = {};
        for (let i = 0; i < fieldOrder.length; i++) {
          result[fieldOrder[i]] = item[fieldOrder[i]];
        }
        inputDataObject[el] = result;
      }

      return inputDataObject;
    }

    function filterEmptyColumnsFromArrayData(fields: any[], data: any[]) {
      // Convertir chaque ligne de data en un tableau de valeurs
      const dataArray = data.map((row: string) =>
        row.split('","').map((value: string) => value.replace(/^"|"$/g, ''))
      );

      // Détecter les colonnes vides
      const columnIsEmpty = fields.map((_, colIndex) =>
        dataArray.every((row) => row[colIndex].trim() === '')
      );

      // Filtrer les indices des colonnes non vides
      const validColumnsIndices = columnIsEmpty
        .map((isEmpty, index) => (isEmpty ? null : index))
        .filter((index) => index !== null);

      // Reconstruire les fields en filtrant les colonnes vides
      const filteredFields = fields.filter((_, index) => !columnIsEmpty[index]);

      // Reconstruire les data en filtrant les colonnes vides
      const filteredData = dataArray.map((row) =>
        validColumnsIndices
          .map((columnIndex) => `"${row[columnIndex]}"`)
          .join(',')
      );

      return {
        fields: filteredFields,
        data: filteredData,
      };
    }

    // Extraction des noms des colonnes
    let gareIDS: any[] = [];

    for (let i = 0; i < this.tableStacks.length; i++)
      gareIDS.push(this.tableStacks[i].gareId);
    this.isochrones.getIsochroneInfo(gareIDS).subscribe({
      next: (infoiso :IQInfoByIsochrone[]) => {
        this.donneeVoyageur.getFrequentationByGare(gareIDS).subscribe({
          next: (frequentation) => {
            console.log(frequentation);
            this.gares.getList(gareIDS).subscribe({
              next: (d) => {
                const habYears = infoiso.filter((item) => item.typeDonneeCamelcase == 'habitants').map((item) => item.annee);
                const habMaxYear =  Math.max(...habYears);
                const eleveYears = infoiso.filter((item) => item.typeDonneeCamelcase == 'eleves').map((item) => item.annee);
                const eleveMaxYear =  Math.max(...eleveYears);
                const hotelYears = infoiso.filter((item) => item.typeDonneeCamelcase == 'chambresHotelEtEmplacementsCamping').map((item) => item.annee);
                const hotelMaxYear =  Math.max(...hotelYears);
                const years = d.map((item) => item.annee);
                const minYear = Math.min(...years);
                const maxYear = Math.max(...years);
                const fYears = frequentation.map((item) => item.annee);
                const fminYear = Math.min(...fYears);
                const fmaxYear = Math.max(...fYears);

                // Créer un tableau contenant toutes les années de minYear à maxYear
                const allYears = Array.from(
                  { length: maxYear - minYear + 1 },
                  (_, index) => minYear + index
                );
                const fAllYears = Array.from(
                  { length: fmaxYear - fminYear + 1 },
                  (_, index) => fminYear + index
                );
                let dta: any = transformJsonData(d);
                for (let i = 0; i < frequentation.length; i++) {
                  dta[frequentation[i].gareId][
                    'Frequentation' + frequentation[i].annee
                  ] = frequentation[i].nombreVoyageur;
                }
                for (let i = 0; i < infoiso.length; i++) {
                  dta[infoiso[i].gareId][infoiso[i].nommageCamelcase] =
                    infoiso[i].nombre;
                }

                let Fields = [
                  'codeUic',
                  'inseeCommune',
                  'inseeDepartement',
                  'nomAom',
                  'nomCommune',
                  'nomGare',
                  'siAutomatesTer',
                  'siAutomatesTgvIntercites',
                  'siLibreServiceAssiste',
                  'siOuverte',
                  'siPosteVenteGuichet',
                ];

                for (let j = 0; j < allYears.length; j++) {
                  const item0 = 'arretsCarsXXXX'.replace(
                    'XXXX',
                    String(allYears[j])
                  );
                  const item1 = 'arretsTerXXXX'.replace(
                    'XXXX',
                    String(allYears[j])
                  );
                  const item2 = 'arretsCarsXXXX'.replace(
                    'XXXX',
                    String(allYears[j])
                  );
                  const item3 = 'arretsTgvXXXX'.replace(
                    'XXXX',
                    String(allYears[j])
                  );
                  const item4 = 'arretsIntercitesXXXX'.replace(
                    'XXXX',
                    String(allYears[j])
                  );
                  const item5 = 'totalArretsXXXX'.replace(
                    'XXXX',
                    String(allYears[j])
                  );
                  Fields.push(item0);
                  Fields.push(item1);
                  Fields.push(item2);
                  Fields.push(item3);
                  Fields.push(item4);
                  Fields.push(item5);
                }
                for (let j = 0; j < fAllYears.length; j++)
                  Fields.push('Frequentation' + fAllYears[j]);
                const amenites = [
                  'habitants15MinAPiedXXXX',
                  'habitants30MinAPiedXXXX',
                  'habitants10MinEnVoitureXXXX',
                  'eleves15MinAPiedXXXX',
                  'eleves30MinAPiedXXXX',
                  'eleves10MinEnVoitureXXXX',
                  'salaries15MinAPiedXXXX',
                  'salaries30MinAPiedXXXX',
                  'salaries10MinEnVoitureXXXX',
                  'restaurants15MinAPiedXXXX',
                  'restaurants30MinAPiedXXXX',
                  'restaurants10MinEnVoitureXXXX',
                  'commerces15MinAPiedXXXX',
                  'commerces30MinAPiedXXXX',
                  'commerces10MinEnVoitureXXXX',
                  'loisirs15MinAPiedXXXX',
                  'loisirs30MinAPiedXXXX',
                  'loisirs10MinEnVoitureXXXX',
                  'sante15MinAPiedXXXX',
                  'sante30MinAPiedXXXX',
                  'sante10MinEnVoitureXXXX',
                  'sport15MinAPiedXXXX',
                  'sport30MinAPiedXXXX',
                  'sport10MinEnVoitureXXXX',
                  'hotelsEtCampings15MinAPiedXXXX',
                  'hotelsEtCampings30MinAPiedXXXX',
                  'hotelsEtCampings10MinEnVoitureXXXX',
                ];
                for (let j = 0; j < amenites.length; j++)
                  if (amenites[j].startsWith('habitants')) {
                    Fields.push(amenites[j].replace('XXXX', String(habMaxYear)));
                  } else if (amenites[j].startsWith('eleves')) {
                    Fields.push(amenites[j].replace('XXXX', String(eleveMaxYear)));
                  } else if (amenites[j].startsWith('hotel')) {
                    Fields.push(amenites[j].replace('XXXX', String(hotelMaxYear)));
                  } else {
                    Fields.push(amenites[j].replace('XXXX', String(maxYear)));
                  }
                dta = reorganizeData(dta, Fields);
                let csvTable: any[] = [];
                for (let el in dta) csvTable.push(dta[el]);
                let fields = [];
                for (let el in csvTable[0]) fields.push(el);

                // Génération des données CSV
                let data = [];
                for (let i = 0; i < csvTable.length; i++) {
                  const item = [];
                  for (let el in csvTable[i]) {
                    item.push(csvTable[i][el]);
                  }
                  data.push(`"${item.join('","')}"`); // Encadrement par des guillemets pour chaque valeur
                }

                // Ajout des noms des colonnes et des lignes de données
                const o = filterEmptyColumnsFromArrayData(fields, data);
                const csvContent = [o.fields.join(','), ...o.data].join('\n');

                // Création du fichier Blob de type CSV
                const blob = new Blob([csvContent], {
                  type: 'text/csv;charset=utf-8;',
                });

                // Génération d'un lien de téléchargement
                const link = document.createElement('a');
                const url = URL.createObjectURL(blob);
                link.setAttribute('href', url);

                const s = this.searchData.filter(
                  (o) => o.id == this.selectedSearch
                );
                let title = '';
                try {
                  title =
                    removeAccents(
                      s[0].name.split(' (')[0].replace(/ /g, '-')
                    ).toLowerCase() + '_';
                } catch (e) {
                  console.log(s, e);
                }

                link.setAttribute(
                  'download',
                  generatePrefix() + title + '.csv'
                ); // Nom du fichier CSV

                // Ajout du lien au document et déclenchement du téléchargement
                link.style.visibility = 'hidden';
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link); // Nettoyage après le téléchargement
                this.disabledExport = false;
              },
            });
          },
        });
      },
    });
  }

  sumByGare(data: any[]): number {
    const garesRecents: {
      [gareId: number]: { annee: number; nombre: number };
    } = {};

    data.forEach((entree) => {
      const gareId = entree.gareId;
      const annee = entree.annee;
      const nombre = parseInt(entree.nombre, 10);

      // Si la gare n'est pas encore dans garesRecents ou si l'année de l'entrée est plus récente
      if (!garesRecents[gareId] || annee > garesRecents[gareId].annee) {
        garesRecents[gareId] = { annee, nombre };
      }
    });

    // Faire la somme totale des nombres les plus récents
    let sommeTotale = 0;
    Object.keys(garesRecents).forEach((gareId) => {
      sommeTotale += garesRecents[parseInt(gareId, 10)].nombre;
    });

    return sommeTotale;
  }

  // Drawing charts
  drawChart(type: string, data: any = {}, options: any = {}) {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue(
      '--text-color-secondary'
    );
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
    if (type == 'stacked') {
      this.optionsStacked = {
        maintainAspectRatio: false,
        aspectRatio: 0.8,
        layout: {
          padding: 10,
        },
        plugins: {
          tooltip: {
            mode: 'index',
            intersect: false,
          },
          legend: {
            labels: {
              color: textColor,
            },
          },
        },
        scales: {
          x: {
            stacked: true,
            ticks: {
              color: textColorSecondary,
            },
            grid: {
              color: surfaceBorder,
              drawBorder: false,
            },
          },
          y: {
            stacked: true,
            ticks: {
              color: textColorSecondary,
            },
            grid: {
              color: surfaceBorder,
              drawBorder: false,
            },
          },
        },
      };

      (this as any)['data' + data.name] = {
        labels: data.labels,
        datasets: data.data,
      };
    }
  }

  // Fonction pour vérifier si au moins un des tableaux est non vide
  hasAtLeastOneNonEmptyArray = (gare: any): boolean => {
    return (
      gare['commerce(s)'].length > 0 ||
      gare["chambre(s) d'hôtel et emplacement(s) de camping"].length > 0 ||
      gare['élève(s)'].length > 0 ||
      gare['établissement(s) de loisir'].length > 0 ||
      gare['habitant(s)'].length > 0 ||
      gare['restaurant(s)'].length > 0 ||
      gare['salarié(s)'].length > 0 ||
      gare['établissement(s) de santé'].length > 0 ||
      gare['établissement(s) de sport'].length > 0
    );
  };

  stats(items: any[]) {
    this.cdref.detectChanges();

    // style par défaut des graphiques
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue(
      '--text-color-secondary'
    );
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

    // Calcul du pourcentage gare ouverte/gare fermée
    let isOpen = 0;
    let isClosed = 0;
    for (let i = 0; i < items.length; i++) {
      if (items[i].siOuverte === true) isOpen++;
      else isClosed++;
    }
    const percentOpen = Math.round((isOpen / (isOpen + isClosed)) * 100);
    const percentClosed = Math.round((isClosed / (isOpen + isClosed)) * 100);
    this.nbStats = {
      gares: items.length,
      isOpen,
      isClosed,
      percentOpen,
      percentClosed,
    };

    let open: number = 0;
    let closed: number = 0;

    const gareIDS: any[] = [];

    items.forEach((o) => {
      if (o.siOuverte === true) open++;
      else closed++;
      gareIDS.push(o.id);
    });

    const gares: any = {};
    // Tableau statistiques (CSV)
    this.gares.getStatsTable(gareIDS).subscribe({
      next: (d: any) => {
        console.log(d);
        // 1. Trouver la dernière année
        const lastYearA = Math.max(...d.map((item: any) => item.anneearrets));
        // 2. Filtrer pour ne garder que les objets de la dernière année
        let filteredData = d.filter(
          (item: any) => item.anneearrets === lastYearA
        );
        const lastYearV = Math.max(
          ...d.map((item: any) => item.anneevoyageurs)
        );
        filteredData = d.filter((item: any) => item.anneearrets === lastYearV);
        for (let i = 0; i < filteredData.length; i++) {
          filteredData[i].voyageurs = filteredData[i].voyageurs * 1;
        }

        this.tableStacks = filteredData.sort((a: any, b: any) =>
          a.nomGare.localeCompare(b.nomGare)
        );
        this.cdref.detectChanges();
        this.resetTable();
      },
    });
    // Fréquentation (total)
    this.donneeVoyageur.getFrequentation(gareIDS).subscribe({
      next: (d) => {
        let labels: any[] = [];
        let datasets: any[] = [];
        for (let i = 0; i < d.length; i++) {
          labels.push(d[i].annee);
          datasets.push(d[i].totalParAnnee);
        }
        const sum = datasets.reduce(
          (accumulator, currentValue) => accumulator + currentValue,
          0
        );
        if (sum == 0) this.display4 = true;
        else this.display4 = false;
        const data = {
          labels: labels,
          datasets: [
            {
              label: 'Nombre de voyageurs',
              data: datasets,
              fill: false,
              borderColor: documentStyle.getPropertyValue('--blue-500'),
              tension: 0.4,
            },
          ],
        };
        this.statsFrequentation = data;
      },
    });
    // Données arrêts (total)
    var dataFrequentationGares: any = {};
    this.dataArret.getDonneesArret(gareIDS).subscribe({
      next: (d: any[]) => {
        const labels: any[] = [];
        let sortedArrets = d.sort(
          (a, b) => Number(b.nombreArret) - Number(a.nombreArret)
        );
        const maxYear = Math.max(...d.map((item) => item.annee));
        sortedArrets = sortedArrets.filter((item) => item.annee === maxYear);
        // Construction de la structure de données avec les labels
        sortedArrets.forEach((item) => {
          const gare = this.datastore.gares.find(
            (o: any) => o.id == item.gareId
          );

          if (gare && !dataFrequentationGares[gare.nomGare]) {
            labels.push(gare.nomGare);
            dataFrequentationGares[gare.nomGare] = {};
          }
          if (gare) {
            dataFrequentationGares[gare.nomGare][item.refTypeArret] =
              item.nombreArret;
          }
        });

        // Par gares
        let frequentationLabels: string[] = [];
        this.donneeVoyageur.getFrequentationByGares(gareIDS).subscribe({
          next: (d) => {
            let labels: any[] = [];
            let datasets: any[] = [];
            let sorted = d.sort(
              (a: any, b: any) =>
                Number(b.nombreVoyageur) - Number(a.nombreVoyageur)
            );
            this.lastYearFrequentation = Math.max(
              ...d.map((item) => item.annee)
            );
            sorted = sorted.filter(
              (item) => item.annee === this.lastYearFrequentation
            );

            let counter = 0;
            for (let i = 0; i < sorted.length; i++) {
              if (labels.indexOf(d[i].nomGare) == -1) {
                if (counter <= 14) {
                  labels.push(sorted[i].nomGare);
                  datasets.push(sorted[i].nombreVoyageur);
                  counter++;
                }
              }
            }

            //
            // Initialisation des tableaux de données
            const dTGV: number[] = [];
            const dIntercites: number[] = [];
            const dTER: number[] = [];
            const dCars: number[] = [];
            const myLabels: string[] = [];

            const df = dataFrequentationGares;

            labels.slice(0, 16).forEach((label) => {
              if (df[label]) {
                dTGV.push(dataFrequentationGares[label][4] ?? 0);
                dCars.push(dataFrequentationGares[label][1] ?? 0);
                dIntercites.push(dataFrequentationGares[label][3] ?? 0);
                dTER.push(dataFrequentationGares[label][2] ?? 0);
                myLabels.push(label);
              }
            });

            const sum0 = dTGV.reduce(
              (accumulator, currentValue) => accumulator + currentValue,
              0
            );
            const sum1 = dIntercites.reduce(
              (accumulator, currentValue) => accumulator + currentValue,
              0
            );
            const sum2 = dTER.reduce(
              (accumulator, currentValue) => accumulator + currentValue,
              0
            );
            const sum3 = dCars.reduce(
              (accumulator, currentValue) => accumulator + currentValue,
              0
            );
            if (sum0 + sum1 + sum2 + sum3 == 0) this.display3 = true;
            else this.display3 = false;

            // Création du graphique
            this.drawChart('stacked', {
              name: 'Desserte',
              labels: myLabels,
              data: [
                {
                  type: 'bar',
                  label: 'TGV',
                  backgroundColor: this.blue500,
                  borderColor: this.blue800,
                  borderWidth: 2,
                  data: dTGV,
                },
                {
                  type: 'bar',
                  label: 'Intercités',
                  backgroundColor: this.green500,
                  borderColor: this.green800,
                  borderWidth: 2,
                  data: dIntercites,
                },
                {
                  type: 'bar',
                  label: 'TER',
                  backgroundColor: this.yellow500,
                  borderColor: this.yellow800,
                  borderWidth: 2,
                  data: dTER,
                },
                {
                  type: 'bar',
                  label: 'Cars',
                  backgroundColor: this.cyan500,
                  borderColor: this.cyan800,
                  borderWidth: 2,
                  data: dCars,
                },
              ],
            });
            //
            frequentationLabels = labels;
            const data = {
              labels: labels,
              datasets: [
                {
                  label: 'Nombre de voyageurs',
                  data: datasets,
                  fill: true,
                  backgroundColor: this.blue500,
                  borderColor: this.blue800,
                  borderWidth: 2,
                  tension: 0.4,
                },
              ],
            };
            const sum = data.datasets[0].data.reduce(
              (acc, val) => acc + val,
              0
            );
            if (sum == 0) this.display6 = true;
            else this.display6 = false;
            this.statsFrequentationParGare = data;
            //
            this.donneeIsochrone.getStatsByGares(gareIDS).subscribe({
              next: (data) => {
                data.forEach((d: any) => {
                  if (!gares[d.libelleTypeIsochrone])
                    gares[d.libelleTypeIsochrone] = {};
                  if (!gares[d.libelleTypeIsochrone][d.typeDonnee])
                    gares[d.libelleTypeIsochrone][d.typeDonnee] = [];
                  gares[d.libelleTypeIsochrone][d.typeDonnee].push(d);
                });

                const stat = gares['30 mn à pied'];

                const filterMostRecentEntry = (data: any) => {
                  const mostRecentByGare: any = {};

                  data.forEach((item: any) => {
                    const gareId = item.gareId;

                    // Si la gare n'existe pas encore ou si l'année actuelle est plus récente, remplace l'entrée
                    if (
                      !mostRecentByGare[gareId] ||
                      item.annee > mostRecentByGare[gareId].annee
                    ) {
                      mostRecentByGare[gareId] = item;
                    }
                  });

                  // Retourne les objets les plus récents pour chaque gare sous forme de tableau
                  return Object.values(mostRecentByGare);
                };

                let statAmenities: any = {};
                const max = 15;
                let count = 0;
                for (let s in stat) {
                  let items = stat[s];
                  items = filterMostRecentEntry(items);
                  for (let i = 0; i < items.length; i++) {
                    const item = items[i];
                    const gare = this.datastore.gares.find(
                      (o: any) => o.id == item.gareId
                    );
                    if (!statAmenities[gare.nomGare])
                      statAmenities[gare.nomGare] = {};

                    if (item.nombre > 0) {
                      statAmenities[gare.nomGare][s] = item;
                    }
                  }

                  count++;
                }

                let s0 = [];
                let s1 = [];
                let s2 = [];
                let t0 = [];
                let t1 = [];
                let t2 = [];
                let s3 = [];

                for (let j = 0; j < frequentationLabels.length; j++) {
                  const item = statAmenities[frequentationLabels[j]];

                  try {
                    t0.push(item['habitant(s)'].nombre);
                  } catch (e) {
                    t0.push('0');
                  }
                  try {
                    t1.push(item['salarié(s)'].nombre);
                  } catch (e) {
                    t1.push('0');
                  }
                  try {
                    t2.push(item['élève(s)'].nombre);
                  } catch (e) {
                    t2.push('0');
                  }
                  try {
                    s0.push(item['établissement(s) de santé'].nombre);
                  } catch (e) {
                    s0.push('0');
                  }
                  try {
                    s1.push(item['commerce(s)'].nombre);
                  } catch (e) {
                    s1.push('0');
                  }
                  try {
                    s2.push(item['établissement(s) de loisir'].nombre);
                  } catch (e) {
                    s2.push('0');
                  }
                  try {
                    s3.push(item['restaurant(s)'].nombre);
                  } catch (e) {
                    s3.push('0');
                  }
                }

                const sumt0 = t0.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );
                const sumt1 = t1.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );
                const sumt2 = t2.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );

                const sum0 = s0.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );
                const sum1 = s1.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );
                const sum2 = s2.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );
                const sum3 = s3.reduce(
                  (accumulator, currentValue) => accumulator + currentValue,
                  0
                );
                if (sum0 + sum1 + sum2 + sum3 == 0) this.display1 = true;
                else this.display1 = false;
                if (sumt0 + sumt1 + sumt2 == 0) this.display5 = true;
                else this.display5 = false;

                this.drawChart('stacked', {
                  name: 'AmenitiesParGare',
                  labels: frequentationLabels,
                  data: [
                    {
                      type: 'bar',
                      label: 'Etablissements de santé',
                      backgroundColor: this.blue500,
                      borderColor: this.blue800,
                      borderWidth: 2,
                      data: s0,
                    },
                    {
                      type: 'bar',
                      label: 'Commerces',
                      backgroundColor: this.red500,
                      borderColor: this.red800,
                      borderWidth: 2,
                      data: s1,
                    },
                    {
                      type: 'bar',
                      label: 'Loisirs',
                      backgroundColor: this.green500,
                      borderColor: this.green800,
                      borderWidth: 2,
                      data: s2,
                    },
                    {
                      type: 'bar',
                      label: 'Restauration',
                      backgroundColor: this.yellow500,
                      borderColor: this.yellow800,
                      borderWidth: 2,
                      data: s3,
                    },
                  ],
                });

                // Population par gare
                this.drawChart('stacked', {
                  name: 'PopulationParGare',
                  labels: frequentationLabels,
                  data: [
                    {
                      type: 'bar',
                      label: 'Habitants',
                      borderColor: this.blue800,
                      borderWidth: 2,
                      backgroundColor: this.blue500,
                      data: t0,
                    },
                    {
                      type: 'bar',
                      label: 'Salariés',
                      borderColor: this.red800,
                      borderWidth: 2,
                      backgroundColor: this.red500,
                      data: t1,
                    },
                    {
                      type: 'bar',
                      label: 'Elèves',
                      borderColor: this.green800,
                      borderWidth: 2,
                      backgroundColor: this.green500,
                      data: t2,
                    },
                  ],
                });

                // graphique #2
                const h15 = gares['15 mn à pied'];
                const h30 = gares['30 mn à pied'];

                const h151 = this.sumByGare(h15['habitant(s)']);
                const h152 = this.sumByGare(h15['élève(s)']);
                const h150 = this.sumByGare(h15['salarié(s)']);
                const h301 = this.sumByGare(h30['habitant(s)']);
                const h302 = this.sumByGare(h30['élève(s)']);
                const h300 = this.sumByGare(h30['salarié(s)']);

                const sum = h151 + h152 + h150 + h301 + h302 + h300;
                if (sum == 0) this.display2 = true;
                else this.display2 = false;

                this.statsBassinPopulation = {
                  labels: ['Salariés', 'Habitants', 'Elèves'],
                  datasets: [
                    {
                      label: 'à 15 minutes',
                      borderColor: this.blue800,
                      borderWidth: 2,
                      backgroundColor: this.blue500,
                      data: [h150, h151, h152],
                    },
                    {
                      label: 'à 30 minutes',
                      backgroundColor: this.red500,
                      borderWidth: 2,
                      borderColor: this.red800,
                      data: [h300, h301, h302],
                    },
                  ],
                };

                this.optionsBassinPopulation = {
                  indexAxis: 'y', // Barres horizontales
                  maintainAspectRatio: false,
                  aspectRatio: 1.5,
                  plugins: {
                    legend: {
                      labels: {
                        color: textColor,
                      },
                    },
                  },
                  scales: {
                    x: {
                      stacked: true, // Empiler les barres sur l'axe X
                      ticks: {
                        color: textColorSecondary,
                        font: {
                          weight: 500,
                        },
                      },
                      grid: {
                        color: surfaceBorder,
                        drawBorder: false,
                      },
                    },
                    y: {
                      stacked: true, // Empiler les barres sur l'axe Y
                      ticks: {
                        color: textColorSecondary,
                      },
                      grid: {
                        color: surfaceBorder,
                        drawBorder: false,
                      },
                    },
                  },
                };

                // graphique #3
                const h153 = this.sumByGare(h15['restaurant(s)']);
                const h154 = this.sumByGare(h15['établissement(s) de santé']);
                const h155 = this.sumByGare(h15['établissement(s) de loisir']);
                const h156 = this.sumByGare(h15['commerce(s)']);
                const h303 = this.sumByGare(h30['restaurant(s)']);
                const h304 = this.sumByGare(h30['établissement(s) de santé']);
                const h305 = this.sumByGare(h30['établissement(s) de loisir']);
                const h306 = this.sumByGare(h30['commerce(s)']);

                if (h153 + h154 + h155 + h156 + h303 + h304 + h305 + h306 == 0)
                  this.display0 = true;
                else this.display0 = false;

                this.statsAmenities = {
                  labels: [
                    'Restaurants',
                    'Etablissements de santé',
                    'Etablissements de loisir',
                    'Commerces',
                  ],
                  datasets: [
                    {
                      label: 'à 15 minutes',
                      backgroundColor: this.blue500,
                      borderColor: this.blue800,
                      borderWidth: 2,
                      data: [h153, h154, h155, h156],
                    },
                    {
                      label: 'à 30 minutes',
                      backgroundColor: this.red500,
                      borderColor: this.red800,
                      borderWidth: 2,
                      data: [h303, h304, h305, h306],
                    },
                  ],
                };

                this.optionsAmenities = {
                  indexAxis: 'y', // Barres horizontales
                  maintainAspectRatio: false,
                  aspectRatio: 1.5,
                  plugins: {
                    legend: {
                      labels: {
                        color: textColor,
                      },
                    },
                  },
                  scales: {
                    x: {
                      stacked: true, // Empiler les barres sur l'axe X
                      ticks: {
                        color: textColorSecondary,
                        font: {
                          weight: 500,
                        },
                      },
                      grid: {
                        color: surfaceBorder,
                        drawBorder: false,
                      },
                    },
                    y: {
                      stacked: true, // Empiler les barres sur l'axe Y
                      ticks: {
                        color: textColorSecondary,
                      },
                      grid: {
                        color: surfaceBorder,
                        drawBorder: false,
                      },
                    },
                  },
                };
                this.cdref.detectChanges();
              },
            });
          },
        });
      },
    });
  }

  eraseSelection() {
    this.isEraserVisible = false;
    this.deleteLastSearch();
    this.resetMarkerGare();
    this.displayStatsPanel = false;
    this.displayInfoITE = false;
    this.displayInfo = false;
    this.selectedSearch = null;
    this.cdref.detectChanges();
  }

  showStats() {
    this.displayStatsPanel = true;
    this.cdref.detectChanges();
  }

  // Dessin polygones
  onPolygonDrawn(event: any) {
    this.isEraserVisible = true;
    this.deleteLastSearch();
    this.resetMarkerGare();
    this.selectedSearch = '';
    const drawnLayer = event.layer; // Récupère le polygone dessiné
    const latLngs = drawnLayer.getLatLngs(); // Récupère les coordonnées du polygone

    // Convertir les coordonnées en format GeoJSON pour Turf.js
    const coordinates = latLngs[0].map((latlng: any) => [
      latlng.lng,
      latlng.lat,
    ]); // [lng, lat] pour GeoJSON

    // Vérifier si le polygone est fermé (le premier point doit être identique au dernier)
    if (
      coordinates.length > 0 &&
      (coordinates[0][0] !== coordinates[coordinates.length - 1][0] ||
        coordinates[0][1] !== coordinates[coordinates.length - 1][1])
    ) {
      // Ajouter le premier point à la fin pour fermer le polygone
      coordinates.push(coordinates[0]);
    }

    const polygonGeoJSON = {
      type: 'Polygon',
      coordinates: [coordinates], // GeoJSON s'attend à ce que les coordonnées soient dans l'ordre [longitude, latitude]
    };

    // Vérifier quelles gares sont à l'intérieur du polygone dessiné
    const selectedGares = this.checkGaresInPolygon(polygonGeoJSON);
    this.selectedPassagesANiveau = this.checkPNInPolygon(polygonGeoJSON);
    if (selectedGares.length == 0) {
      this.messageService.add({
        severity: 'error',
        summary: 'Erreur',
        detail: 'Aucune gare dans la zone sélectionnée',
      });
      this.cdref.detectChanges();
      this.deleteLastSearch();
      this.isEraserVisible = false;
      return;
    }

    if (selectedGares.length == 1) {
      this.onGareSelect(
        { value: { geom: selectedGares[0].theGeom } },
        selectedGares[0],
        true
      );
      return;
    }

    this.statTitle =
      "Informations agrégées pour l'ensemble des gares sélectionnées";

    this.stats(selectedGares);
/*
    this.gares.getStatsTable(selectedGares.map((obj) => obj.id)).subscribe({
      next: (d: any) => {
        for (let i = 0; i < d.length; i++) d[i].voyageurs = d[i].voyageurs * 1;
        this.tableStacks = d.sort((a: any, b: any) =>
          a.nomGare.localeCompare(b.nomGare)
        );
        this.cdref.detectChanges();
      },
    });
*/
    this.displayStatsPanel = true;
    this.displayInfoITE = false;
    this.displayInfo = false;
    this.cdref.detectChanges();

    // Ajouter le polygone dessiné à la carte
    drawnLayer.addTo(this.map);

    // Stocker la référence au dernier polygone dessiné
    this.lastDrawnLayer = drawnLayer;
    this.resetTable();
  }

  deleteLastSearch() {
    if (this.lastDrawnLayer) {
      this.map.removeLayer(this.lastDrawnLayer); // Supprimer le polygone de la carte
      this.lastDrawnLayer = null; // Réinitialiser la variable
      console.log('Dernier polygone supprimé');
    } else {
      console.log('Aucun polygone à supprimer');
    }
  }

  checkGaresInMultiPolygon(multipolygon: any): any[] {
    const selectedGares: any[] = [];

    // Crée un multipolygon Turf.js
    const turfMultiPolygon = turf.multiPolygon(multipolygon.coordinates);

    this.datastore.gares.forEach((gare: any) => {
      const garePoint = turf.point(gare.theGeom.coordinates); // Convertir les coordonnées de la gare en point Turf.js

      // Vérifie si le point (gare) est dans le multipolygon
      if (turf.booleanPointInPolygon(garePoint, turfMultiPolygon)) {
        selectedGares.push(gare); // Ajouter la gare à la liste si elle est à l'intérieur du multipolygon
      }
    });

    return selectedGares; // Retourne la liste des gares à l'intérieur du multipolygon
  }

  checkPNInMultiPolygon(multipolygon: any): any[] {
    const selectedPN: any[] = [];

    // Crée un multipolygon Turf.js
    const turfMultiPolygon = turf.multiPolygon(multipolygon.coordinates);

    this.datastore.pniveau.forEach((markerData: any) => {
      const pnPoint = turf.point(markerData.theGeom.coordinates);

      if (turf.booleanPointInPolygon(pnPoint, turfMultiPolygon)) {
        selectedPN.push(markerData);
      }
    });

    return selectedPN;
  }

  checkPNInPolygon(polygon: any): any[] {
    const selectedPN: any[] = [];
    let turfPolygon: any;
    try {
      turfPolygon = turf.polygon(polygon.coordinates);
    } catch (e) {
      turfPolygon = turf.polygon(polygon);
    }
    this.datastore.pniveau.filter((markerData: any) => {
      const pnPoint = turf.point(markerData.theGeom.coordinates);

      // Vérifie si le point (gare) est dans le polygone
      if (turf.booleanPointInPolygon(pnPoint, turfPolygon)) {
        selectedPN.push(markerData);
      }
    });
    return selectedPN;
  }

  // Méthode pour vérifier si les gares sont à l'intérieur d'un polygone
  checkGaresInPolygon(polygon: any): any[] {
    const selectedGares: any[] = [];

    // Crée un polygone Turf.js à partir du multipolygon ou du polygone dessiné
    let turfPolygon: any;
    try {
      turfPolygon = turf.polygon(polygon.coordinates);
    } catch (e) {
      turfPolygon = turf.polygon(polygon);
    }

    this.datastore.gares.forEach((gare: any) => {
      const garePoint = turf.point(gare.theGeom.coordinates); // Convertir les coordonnées de la gare en point Turf.js

      // Vérifie si le point (gare) est dans le polygone
      if (turf.booleanPointInPolygon(garePoint, turfPolygon)) {
        selectedGares.push(gare); // Ajouter la gare à la liste si elle est à l'intérieur du polygone
      }
    });

    return selectedGares; // Retourne la liste des gares à l'intérieur du polygone
  }

  gotoLocation(data: any, zoom?: number): void {
    if (data && data.length > 0) {
      setTimeout(() => {
        let geom = data[0].geom;
        if (!data[0].geom) geom = data[0].theGeom;
        if (geom.type === 'Point') {
          this.map.setView(geom.coordinates.reverse(), 15);
        }
        if (geom.type === 'MultiPolygon') {
          const bounds = L.latLngBounds([]);
          const polygonLayers: L.Polygon[] = [];

          geom.coordinates.forEach((polygon: any) => {
            polygon.forEach((coords: any) => {
              const latlngs = coords.map((coord: any) => [coord[1], coord[0]]);
              const polygonLayer = L.polygon(latlngs, {
                color: 'navy',
                weight: 2,
              });
              polygonLayers.push(polygonLayer);
              bounds.extend(polygonLayer.getBounds());
            });
          });

          // Supprimer l'ancien polygone dessiné, si existant
          if (this.lastDrawnLayer) {
            this.map.removeLayer(this.lastDrawnLayer);
          }

          // Ajouter tous les polygones à la carte en une seule fois
          const polygonGroup = L.featureGroup(polygonLayers).addTo(this.map);

          // Ajuster le centrage de la carte avec un padding asymétrique
          this.map.fitBounds(bounds, {
            paddingTopLeft: [100, 0], // Pas de padding en haut à gauche
            paddingBottomRight: [980, 20], // Plus de padding à droite pour déplacer la carte à gauche
          });

          // Stocker la référence du groupe de polygones comme le dernier polygone dessiné
          this.lastDrawnLayer = polygonGroup;
        }
      }, 100);
    }
  }

  updateMarkerIcons(): void {
    const zoomLevel = this.map.getZoom(); // Obtenez le niveau de zoom actuel
    let newSize: any;

    // Déterminez la nouvelle taille des icônes en fonction du niveau de zoom
    if (zoomLevel >= this.ZOOM_LEVELS.LARGE) {
      newSize = this.ICON_SIZES.LARGE;
    } else if (zoomLevel >= this.ZOOM_LEVELS.MEDIUM) {
      newSize = this.ICON_SIZES.MEDIUM;
    } else {
      newSize = this.ICON_SIZES.MEDIUM;
    }

    const bounds = this.map.getBounds(); // Obtenez les limites actuelles de la carte

    // Parcourez tous les marqueurs et ajustez la taille des icônes uniquement s'ils sont visibles
    Object.keys(this.Markers).forEach((key) => {
      this.Markers[key].forEach((markerObj: any) => {
        var marker = markerObj.marker;
        if (!marker) marker = markerObj;
        if (bounds.contains(marker.getLatLng())) {
          const iconOptions = marker.options.icon.options;
          const newIcon = L.icon({
            iconUrl: iconOptions.iconUrl,
            shadowUrl: iconOptions.shadowUrl,
            iconSize: [newSize.width, newSize.height],
            iconAnchor: [newSize.width / 2, newSize.height],
          });
          marker.setIcon(newIcon);
        }
      });
    });

    // Mettez également à jour la taille des icônes des marqueurs actuellement agrandis (si nécessaire)
    if (
      this.expandedMarker &&
      bounds.contains(this.expandedMarker.getLatLng())
    ) {
      this.changeIconSize(this.expandedMarker, true); // Ou false, en fonction de la logique d'expansion
    }
  }

  displayLegende() {
    this.Legende = !this.Legende;
  }

  focusOnMarkerGare(event: any) {
    if (
      this.selectedGare &&
      this.selectedGare.theGeom &&
      this.selectedGare.theGeom.coordinates
    ) {
      const coords = [...this.selectedGare.theGeom.coordinates].reverse();
      this.moveToLocation({
        target: this.currentMarker,
      });
    } else {
      console.error(
        'Les coordonnées ne sont pas disponibles dans selectedGare.theGeom.coordinates'
      );
    }
  }

  focusOnMarkerITE(event: any) {
    if (
      this.selectedITE &&
      this.selectedITE.theGeom &&
      this.selectedITE.theGeom.coordinates
    ) {
      if (this.selectedITE.etatIte == 'Utilisée') this.currentITE = 'ite-utile';
      const coords = [...this.selectedITE.theGeom.coordinates].reverse();
      this.moveToLocation(
        {
          target: this.currentMarker,
        },
        17
      );
    }
  }

  changeIconSize(
    marker: L.Marker,
    isExpanded: boolean,
    noAnchor: boolean = false
  ) {
    const iconUrl = marker.options.icon.options.iconUrl;
    const shadowUrl = marker.options.icon.options.shadowUrl;
    const o: any = {
      iconUrl: iconUrl,
      shadowUrl: shadowUrl,
      iconSize: isExpanded ? [50, 72] : [36, 36], // Nouvelle taille
    };
    if (noAnchor === false) {
      o.iconSize = isExpanded ? [50, 72] : [36, 36]; // Nouvelle taille
      o.iconAnchor = isExpanded ? [25, 72] : [12, 46];
    } else {
      o.iconSize = isExpanded ? [36, 36] : [26, 26]; // Nouvelle taille
    }

    if (o.iconUrl.indexOf('gares') > -1) {
      if (o.iconSize[0] == 26) o.iconSize = [36, 36];
    }
    const newIcon = L.icon(o);

    marker.setIcon(newIcon);

    // Modifier le z-index du marqueur
    if (isExpanded) {
      marker.setZIndexOffset(9999); // Une valeur élevée pour s'assurer qu'il soit au-dessus
    } else {
      marker.setZIndexOffset(0); // Remettre à la valeur par défaut
    }
  }

  hideITESidebar() {
    if (this.expandedMarker) {
      this.changeIconSize(this.expandedMarker, false, true);
    }
    this.selectedITE = null;
    this.displayInfoITE = false;
    this.cdref.detectChanges();
  }

  hideSidebar() {
    if (this.expandedMarker) {
      this.changeIconSize(this.expandedMarker, false);
    }
    this.resetIsochronesHighlight();
    this.selectedGare = null;
    this.displayInfo = false;
    this.cdref.detectChanges();
  }

  changeIteIconSize(marker: L.Marker, isExpanded: boolean) {
    const iconUrl = marker.options.icon.options.iconUrl;
    const shadowUrl = marker.options.icon.options.shadowUrl;

    const newIcon = L.icon({
      iconUrl: iconUrl,
      shadowUrl: shadowUrl,
      iconSize: isExpanded ? [50, 72] : [26, 46], // Nouvelle taille
      iconAnchor: isExpanded ? [12, 46] : [12, 46],
    });

    marker.setIcon(newIcon);

    // Modifier le z-index du marqueur
    if (isExpanded) {
      marker.setZIndexOffset(9999); // Une valeur élevée pour s'assurer qu'il soit au-dessus
    } else {
      marker.setZIndexOffset(0); // Remettre à la valeur par défaut
    }
  }

  loadIcon(name: string) {
    const xxl: L.IconOptions = {
      iconSize: [50, 72],
      iconAnchor: [25, 72],
      iconUrl: 'assets/images/marker-' + name + '.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
    };

    const small: L.IconOptions = {
      iconSize: [35, 46],
      iconAnchor: [12, 46],
      iconUrl: 'assets/images/marker-' + name + '.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
    };

    const verysmall: L.IconOptions = {
      iconSize: [26, 26], // Taille par défaut pour les ITE
      iconAnchor: [13, 26], // Ajustement de l'ancre pour l'ITE
      iconUrl: 'assets/images/marker-' + name + '.svg',
    };

    const ite: L.IconOptions = {
      iconSize: [26, 26], // Taille par défaut pour les ITE
      iconAnchor: [13, 26], // Ajustement de l'ancre pour l'ITE
      iconUrl: 'assets/images/marker-' + name + '.svg',
    };

    return { ite, verysmall, small, xxl };
  }

  loadSmallIcon(name: string) {
    const xxl: L.IconOptions = {
      iconSize: [40, 62],
      iconAnchor: [5, 52],
      iconUrl: 'assets/images/marker-' + name + '.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
    };

    const small: L.IconOptions = {
      iconSize: [30, 52],
      iconAnchor: [5, 52],
      iconUrl: 'assets/images/marker-' + name + '.svg',
      shadowUrl: 'assets/images/marker-shadow.png',
    };

    const verysmall: L.IconOptions = {
      iconSize: [15, 15],
      //iconAnchor: [15, 15],
      iconUrl: 'assets/images/marker-' + name + '.svg',
      //shadowUrl: 'assets/images/marker-shadow.png',
    };

    const ite: L.IconOptions = {
      iconSize: [26, 26],
      //iconAnchor: [15, 15],
      iconUrl: 'assets/images/marker-' + name + '.svg',
      //shadowUrl: 'assets/images/marker-shadow.png',
    };

    return { ite, verysmall, small, xxl };
  }

  goToStreetView(curLatitude: any, curLongitude: any) {
    const url: string =
      'http://maps.google.com/maps?q=&layer=c&cbll=' +
      curLatitude +
      ',' +
      curLongitude;
    window.open(url, '_blank');
  }

  loadIsochrone(gare: string) {
    this.isochrones.get(gare).subscribe({
      next: (d: any) => {
        let dd = d;
        d = d.filter((o: any) => {
          return o.refTypeIsochrone.id == 2;
        });
        this.donneeIsochrone.get(d[0].id).subscribe({
          next: (data) => {
            // Fonction pour regrouper les données par typeDonnee et récupérer la plus récente
            const groupedData = data.reduce((acc: any, current: any) => {
              const { typeDonnee } = current.refTypeDonnee;
              const existing = acc[typeDonnee];

              // Si le typeDonnee existe déjà et que l'année est plus récente, on remplace
              if (!existing || current.annee > existing.annee) {
                acc[typeDonnee] = {
                  typeDonnee: current.refTypeDonnee.typeDonnee,
                  nombre: current.nombre,
                  annee: current.annee,
                  type: 'ref-' + current.refTypeDonnee.id,
                };
              }

              return acc;
            }, {});

            // Convertir en tableau de résultats
            const response: any = Object.values(groupedData);

            const order = [
              'ref-8',
              'ref-6',
              'ref-9',
              'ref-7',
              'ref-1',
              'ref-5',
              'ref-2',
              'ref-3',
              'ref-4',
            ];

            this.currentData = response.sort(
              (a: any, b: any) => order.indexOf(a.type) - order.indexOf(b.type)
            );
            this.sumUpYear = this.currentData[0].annee;
            for (let i = 0; i < this.currentData.length; i++) {
              if (this.sumUpYear != '') {
                if (this.currentData[i].annee != this.sumUpYear)
                  this.sumUpYear = '';
              }
              if (this.currentData[i].nombre <= 1)
                this.currentData[i].typeDonnee = this.currentData[
                  i
                ].typeDonnee.replace(/\(s\)/g, '');
              else
                this.currentData[i].typeDonnee = this.currentData[
                  i
                ].typeDonnee.replace(/\(s\)/g, 's');
            }
            if (this.sumUpYear != '')
              this.sumUpYear = '(' + this.sumUpYear + ')';
            this.cdref.detectChanges();
          },
        });

        if (this.map.getZoom() < 10) return;

        /** affichage des isochrones */
        dd.forEach((data: any, index: number) => {
          const color: any = {
            '30 mn à pied': 'red',
            '15 mn à pied': 'blue',
            '10 mn en voiture': 'green',
          };

          let polygonLayer;

          const itemId = data.refTypeIsochrone.id;
          if (
            (data.refTypeIsochrone.libelle == '15 mn à pied' &&
              this.GMenu.iso1 == true) ||
            (data.refTypeIsochrone.libelle == '10 mn en voiture' &&
              this.GMenu.iso2 == true) ||
            (data.refTypeIsochrone.libelle == '30 mn à pied' &&
              this.GMenu.iso3 == true)
          ) {
            if (data.refTypeIsochrone.libelle == '10 mn en voiture')
              polygonLayer = this.addPolygon(
                data.refTypeIsochrone.libelle,
                false
              );
            else
              polygonLayer = this.addPolygon(
                data.refTypeIsochrone.libelle,
                true
              );
            /*var polygonCoordinates = data.theGeom.coordinates[0].map(function (
              coord: any
            ) {
              return [coord[1], coord[0]];
            });*/
          }
        });
      },
    });
  }

  loadOnZoomIsochrone(gares: any[]) {
    const gareIds = gares.map((o) => o.id);
    const txGares = gares.reduce((acc, objet) => {
      acc[objet.id] = objet;
      return acc;
    }, {});

    // Préparer une seule fois le colorMap
    const colorMap: { [key: string]: string } = {
      '30 mn à pied': 'red',
      '15 mn à pied': 'blue',
      '10 mn en voiture': 'green',
    };

    // Créer et stocker les panes une seule fois
    const panes: { [key: string]: any } = {
      '15 mn à pied': '500',
      '30 mn à pied': '400',
      '10 mn en voiture': '300',
    };

    Object.keys(panes).forEach((libelle) => {
      if (!this.map.getPane(libelle)) {
        this.map.createPane(libelle);
        this.map.getPane(libelle).style.zIndex = panes[libelle];
      }
    });

    // Charger les isochrones pour les gares sélectionnées
    this.isochrones.loadIsochrones(gareIds).subscribe({
      next: (response: any[]) => {
        response.forEach((d) => {
          if (!this.alreadyLoadedIsochrone[d.id]) {
            const gareId = d.gareId;
            const refTypeIsochrone = {
              id: d.refTypeIsochroneId,
              libelle:
                d.refTypeIsochroneId === 1
                  ? '15 mn à pied'
                  : d.refTypeIsochroneId === 2
                  ? '30 mn à pied'
                  : '10 mn en voiture',
            };

            const polygonCoordinates = d.theGeom.coordinates[0].map(
              (coord: any) => [coord[1], coord[0]]
            );

            const polygonLayer = this.addPolygon(
              refTypeIsochrone.libelle,
              refTypeIsochrone.libelle !== '10 mn en voiture'
            );

            const polygon = L.polygon(polygonCoordinates, {
              color: colorMap[refTypeIsochrone.libelle],
              weight: 1,
              pane: refTypeIsochrone.libelle,
            });

            polygonLayer.addLayer(polygon);
            this.isochroneLayers[d.id] = polygonLayer;
            this.alreadyLoadedIsochrone[d.id] = true;
            this.isochroneLayerGare[d.id] = txGares[gareId];
          }
        });
        // Toggle isochrone layers une seule fois après la boucle
        this.toggleIsochroneLayer('15 minutes à pied', this.GMenu.iso1);
        this.toggleIsochroneLayer('30 minutes à pied', this.GMenu.iso2);
        this.toggleIsochroneLayer('10 minutes en voiture', this.GMenu.iso3);
      },
    });
  }

  loadMyIsochrone(gareId: string, highlight: boolean = false): void {
    this.isochrones.get(gareId).subscribe({
      next: (d) => {
        // Ne pas continuer si le zoom est inférieur à 10
        if (this.map.getZoom() < 10) return;

        // Vérifier et supprimer les isochrones précédemment sélectionnés
        if (this.selectedIsochrones) {
          this.selectedIsochrones.eachLayer((layer: any) => {
            this.map.removeLayer(layer);
          });
          this.selectedIsochrones.clearLayers();
          this.selectedIsochrones = null;
        }

        const polygonLayers: L.Polygon[] = [];
        const colorMap: { [key: string]: string } = {
          '30 mn à pied': highlight ? 'red' : 'red',
          '15 mn à pied': highlight ? 'blue' : 'blue',
          '10 mn en voiture': highlight ? 'green' : 'green',
        };

        // Utilisation d'un batch pour rendre les polygones progressivement
        let batchIndex = 0;
        const batchSize = 50; // Nombre de polygones à ajouter par batch
        const renderBatch = () => {
          const start = batchIndex * batchSize;
          const end = Math.min(start + batchSize, d.length);

          for (let i = start; i < end; i++) {
            const data = d[i];
            let color = 'transparent';
            if (
              (data.refTypeIsochrone.id == 1 && this.GMenu.iso1) ||
              (data.refTypeIsochrone.id == 2 && this.GMenu.iso2) ||
              (data.refTypeIsochrone.id == 3 && this.GMenu.iso3)
            ) {
              color = colorMap[data.refTypeIsochrone.libelle] || 'gray';
            }

            const polygonCoordinates = data.theGeom.coordinates[0].map(
              (coord: any) => [coord[1], coord[0]]
            );

            const polygon = L.polygon(polygonCoordinates, {
              color: color,
              weight: highlight ? 3 : 0, // Surbrillance si nécessaire
            });

            polygonLayers.push(polygon);
          }

          if (end < d.length) {
            batchIndex++;
            requestAnimationFrame(renderBatch); // Prochaine tranche
          } else {
            // Quand tous les batches sont traités, ajouter les polygones à la carte
            this.selectedIsochrones = L.featureGroup(polygonLayers).addTo(
              this.map
            );
            this.isochroneLayers[gareId] = this.selectedIsochrones;
          }
        };

        renderBatch();
      },
    });
  }

  // Méthode pour masquer tous les isochrones
  hideAllIsochrones(): void {
    Object.keys(this.isochroneLayers).forEach((gareId) => {
      this.isochroneLayers[gareId].eachLayer((layer: L.Layer) => {
        if (layer instanceof L.Polygon) {
          layer.setStyle({
            opacity: 0, // Rend les bordures invisibles
            fillOpacity: 0, // Rend le remplissage invisible
          });
        }
      });
    });
  }

  // Méthode pour afficher à nouveau tous les isochrones
  showAllIsochrones(): void {
    if (this.map.zoomLevel > 11) return;
    Object.keys(this.isochroneLayers).forEach((gareId) => {
      this.isochroneLayers[gareId].eachLayer((layer: L.Layer) => {
        if (layer instanceof L.Polygon) {
          layer.setStyle({
            opacity: 1, // Rétablit la bordure visible
            fillOpacity: 0.3, // Remet le remplissage visible
          });
        }
      });
    });
  }

  adjustMapView(): void {
    if (!this.map) return;
    if (this.displayInfo == false) return;

    const sidePanelWidth = 450;
    // Déplacer la vue de la carte vers la gauche
    this.map.panBy([sidePanelWidth / 2, 0], { animate: true });
  }

  moveToLocation(event: any, zoomLevel: number = 13) {
    if (event.value === null) {
      this.selectedGare = null;
      const latitude = 46.71109;
      const longitude = 1.7191036;
      const zoom = 6;
      this.map.setView([latitude, longitude], zoom);
      this.displayInfo = false;
    } else {
      const zoom = zoomLevel;
      if (event.target) {
        let coords = [];
        try {
          coords = [event.target._latlng.lat, event.target._latlng.lng];
        } catch (e) {
          coords = [event.target[0], event.target[1]];
        }

        return this.map.setView(coords, zoom);
      }
      if (event.value) {
        if (event.value.geometry) {
          const coords = [...event.value.geometry.coordinates].reverse();
          return this.map.setView(coords, zoom);
        }
        if (event.value.theGeom) {
          const coords = [...event.value.theGeom.coordinates].reverse();
          this.selectedGare = event.value;
          this.onGareSelect(event, this.selectedGare);
          return this.map.setView(coords, zoom);
        }
      }
      if (this.selectedITE) {
        //console.log('this.selectedITE', this.selectedITE);
        try {
          const coords = [...this.selectedITE.geom.coordinates].reverse();
          this.map.setView(coords, zoom);
        } catch (e) {
          const coords = [...this.selectedITE.theGeom.coordinates].reverse();
          this.map.setView(coords, zoom);
        }
        this.onITESelect(event, this.selectedITE);
        return;
      }
      try {
        const coords = [...this.selectedGare.geom.coordinates].reverse();
        this.map.setView(coords, zoom);
      } catch (e) {
        console.log(e);
        const coords = [...this.selectedGare.theGeom.coordinates].reverse();
        this.map.setView(coords, zoom);
      }
    }
    this.onGareSelect(event, this.selectedGare);
  }

  gotoStreetView(): void {
    let coords;
    try {
      coords = [...this.selectedGare.theGeom.coordinates].reverse();
    } catch (e) {
      coords = [...this.selectedITE.theGeom.coordinates].reverse();
    }

    this.currentMarker = coords;
    this.curLatitude = coords[0];
    this.curLongitude = coords[1];
    this.goToStreetView(this.curLatitude, this.curLongitude);
  }
  // Réinitialiser la taille de l'icône du marqueur précédemment agrandi (gare ou ITE)
  resetMarkerGare(e?: any) {
    if (!e) {
      if (this.expandedMarker) {
        if (this.lastSelectedItem === 'gare')
          this.changeIconSize(this.expandedMarker, false, false);
        else this.changeIconSize(this.expandedMarker, false, true);
      }
      return;
    }
    if (this.expandedMarker && this.expandedMarker !== e.target) {
      if (this.lastSelectedItem === 'gare') {
        this.changeIconSize(this.expandedMarker, false, false); // Réinitialise l'icône de la gare
      } else {
        this.changeIconSize(this.expandedMarker, false, true); // Réinitialise l'icône ITE
      }
    }
  }

  onITESelect(e: any, d: any): void {
    // Ouvre le panneau latéral pour les informations sur l'ITE
    this.displayStatsPanel = false;
    this.displayInfoITE = true;
    this.displayInfo = false;
    this.selectedGare = null;

    this.selectedITE = d;

    this.resetIsochronesHighlight();

    // Définir l'icône de l'ITE en fonction de son état
    switch (this.selectedITE.etatIte) {
      case 'Utilisée':
        this.currentITE = 'ite-utile';
        break;
      case 'Bon':
        this.currentITE = 'ite-bon';
        break;
      case 'Inutilisable':
        this.currentITE = 'ite-inutile';
        break;
      case 'Mauvais':
        this.currentITE = 'ite-mauvais';
        break;
      case 'Neuf':
        this.currentITE = 'ite-neuf';
        break;
      default:
        this.currentITE = 'ite-unknown';
        break;
    }

    // Mémoriser le marqueur cliqué
    this.currentMarker = e.target;
    this.curLatitude = e.target._latlng.lat;
    this.curLongitude = e.target._latlng.lng;

    this.resetMarkerGare(e);

    this.lastSelectedItem = 'ite';

    this.etablissements.getITEDetails(this.selectedITE.id).subscribe({
      next: (d: any) => {
        let flux: any = {};
        for (let i = 0; i < d.length; i++) {
          if (!flux[d[i].flux]) flux[d[i].flux] = [];
          flux[d[i].flux].push(d[i].codeNst + ' - ' + d[i].libelle);
        }
        for (let el in flux) flux[el] = flux[el].join(', ');
        this.selectedITE.flux = flux;
        this.cdref.detectChanges();
      },
    });

    // Agrandir l'icône de l'ITE sélectionné
    this.changeIconSize(e.target, true, true);

    // Mettez à jour le marqueur agrandi
    this.expandedMarker = e.target;

    // Appliquer les modifications visuelles
    this.cdref.detectChanges();
  }

  resetIsochronesHighlight(): void {
    if (this.selectedIsochrones) {
      this.selectedIsochrones.eachLayer((layer: L.Layer) => {
        if (layer instanceof L.Polygon) {
          layer.setStyle({
            color: 'transparent', // Couleur initiale
            weight: 1, // Poids initial
          });
        }
      });
    }
  }

  onGareSelect(e: any, d: any, isZoom: boolean = false): void {
    // Réinitialiser les isochrones précédemment sélectionnés
    this.resetIsochronesHighlight();
    this.hideStatsSidebar();
    if (this.map < 11) this.hideAllIsochrones();

    // Charger les isochrones pour la gare sélectionnée
    this.loadIsochrone(d.id);

    // Ouvre le panneau latéral pour les informations sur la gare
    this.displayInfoITE = false;
    this.displayInfo = true;

    this.selectedGareRegion = '';
    this.dptRegions
      .getRegionByDpt(d.inseeDepartement)
      .subscribe((response: any) => {
        if (response.length > 0)
          this.selectedGareRegion = response[0].regionName;
        this.cdref.detectChanges();
      });
    this.selectedGare = d;

    if (isZoom == true) this.gotoLocation([d], 7);

    // Obtenir les coordonnées de la gare sélectionnée
    let coordinates = [];
    if (e.value) {
      if (e.value.geom) coordinates = e.value.geom;
      if (e.value.theGeom) coordinates = e.value.theGeom;

      // Rechercher et assigner le bon marqueur (gare ouverte ou fermée)
      for (let i = 0; i < this.Markers.gares.length; i++) {
        const item = this.Markers.gares[i];
        if (
          item.marker._latlng.lat == coordinates[0] &&
          item.marker._latlng.lng == coordinates[1]
        ) {
          e.target = item.marker;
          this.selectedGare = item.data;
        }
      }
      if (!e.target) {
        for (let i = 0; i < this.Markers.garesClosed.length; i++) {
          const item = this.Markers.garesClosed[i];
          if (
            item.marker._latlng.lat == coordinates[0] &&
            item.marker._latlng.lng == coordinates[1]
          ) {
            e.target = item.marker;
            this.selectedGare = item.data;
          }
        }
      }
    }

    // Si aucun marqueur n'a été trouvé, utiliser les données directement
    if (!e.target) {
      if (e.value) {
        if (e.value.geom) coordinates = e.value.geom;
        if (e.value.theGeom) coordinates = e.value.theGeom;
        if (coordinates.coordinates) coordinates = coordinates.coordinates;

        for (let i = 0; i < this.Markers.gares.length; i++) {
          const item = this.Markers.gares[i];
          if (
            item.marker._latlng.lat == coordinates[1] &&
            item.marker._latlng.lng == coordinates[0]
          ) {
            e.target = item.marker;
            this.selectedGare = item.data;
          }
        }
        if (!e.target) {
          for (let i = 0; i < this.Markers.garesClosed.length; i++) {
            const item = this.Markers.garesClosed[i];
            if (
              item.marker._latlng.lat == coordinates[1] &&
              item.marker._latlng.lng == coordinates[0]
            ) {
              e.target = item.marker;
              this.selectedGare = item.data;
            }
          }
        }
      }
    }

    if (this.selectedGare.siOuverte === true) this.currentGareIcon = 'g-1';
    else this.currentGareIcon = 'g-0';

    // Charger les isochrones de la gare sélectionnée
    this.loadMyIsochrone(this.selectedGare.id, true);

    // Si aucun marqueur n'a été trouvé, arrêter la méthode
    if (!e.target) return;

    this.currentMarker = e.target;
    this.curLatitude = e.target._latlng.lat;
    this.curLongitude = e.target._latlng.lng;

    // Réinitialiser l'icône du marqueur précédemment agrandi (ITE ou gare)
    if (this.expandedMarker && this.expandedMarker !== e.target) {
      if (this.lastSelectedItem === 'gare') {
        this.changeIconSize(this.expandedMarker, false, false); // Réinitialise l'icône de la gare
      } else {
        this.changeIconSize(this.expandedMarker, false, true); // Réinitialise l'icône ITE
      }
    }

    this.lastSelectedItem = 'gare';

    // Agrandir l'icône de la gare sélectionnée
    this.changeIconSize(e.target, true, false);

    // Mettez à jour le marqueur agrandi
    this.expandedMarker = e.target;

    // Charger les graphiques pour la gare sélectionnée
    const documentStyle = getComputedStyle(document.documentElement);

    // Charger les arrêts
    this.dataArret.getByGareId(this.selectedGare.id).subscribe({
      next: (data) => {
        data.sort(
          (a: any, b: any) =>
            new Date(a.annee).getTime() - new Date(b.annee).getTime()
        );

        const labels = [''];
        const d = [''];
        let total = 0;
        let tmptable: any = {};
        let year = 0;
        // 1. Trouver la dernière année
        const lastYear = Math.max(...data.map((item) => item.annee));

        // 2. Filtrer pour ne garder que les objets de la dernière année
        const filteredData = data.filter((item) => item.annee === lastYear);
        for (let i = 0; i < filteredData.length; i++) {
          if (!tmptable[filteredData[i].refTypeArret.libelle])
            tmptable[filteredData[i].refTypeArret.libelle] = 0;
          tmptable[filteredData[i].refTypeArret.libelle] +=
            filteredData[i].nombreArret;
          if (year != filteredData[i].annee) {
            year = filteredData[i].annee;
            total = 0;
          }
          total += filteredData[i].nombreArret;
        }
        for (let el in tmptable) {
          labels.push(el);
          d.push(tmptable[el]);
        }
        this.totalArrets = total;
        labels.push('');
        d.push('');

        this.dataA = {
          labels: labels,
          datasets: [
            {
              label: 'Total arrêts',
              data: d,
              backgroundColor: [
                'rgba(255, 159, 64, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
              ],
              borderColor: [
                'rgb(255, 159, 64)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
              ],
              borderWidth: 1,
            },
          ],
        };
        this.optionsA = {
          responsive: false,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              labels: {
                color: documentStyle.getPropertyValue('--text-color'),
              },
            },
          },
          scales: {
            x: {
              ticks: {
                color: documentStyle.getPropertyValue('--text-color-secondary'),
              },
              grid: {
                color: documentStyle.getPropertyValue('--surface-border'),
                drawBorder: false,
              },
            },
            y: {
              beginAtZero: true,
              ticks: {
                color: documentStyle.getPropertyValue('--text-color-secondary'),
              },
              grid: {
                color: documentStyle.getPropertyValue('--surface-border'),
                drawBorder: false,
              },
            },
          },
        };
      },
    });

    // Charger la fréquentation
    this.frequentation.getByGareId(this.selectedGare.id).subscribe({
      next: (data) => {
        data.sort(
          (a: any, b: any) =>
            new Date(a.annee).getTime() - new Date(b.annee).getTime()
        );

        const labels = [];
        const datasets = [];
        let year = 0;
        for (let i = 0; i < data.length; i++) {
          labels.push(data[i].annee);
          datasets.push(data[i].nombreVoyageur);
          const stringValue = data[i].nombreVoyageur
            .toString()
            .replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
          if (data[i].annee > year)
            this.statLabel =
              'Nombre de voyageurs en ' + data[i].annee + ' : ' + stringValue;
        }
        this.dataF = {
          labels: labels,
          datasets: [
            {
              label: '',
              data: datasets,
              fill: false,
              borderColor: documentStyle.getPropertyValue('--blue-500'),
              tension: 0.4,
            },
          ],
        };
        this.cdref.detectChanges();
      },
    });
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue(
      '--text-color-secondary'
    );
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
    this.optionsF = {
      maintainAspectRatio: false,
      aspectRatio: 0.8,
      responsive: true,
      plugins: {
        tooltip: {
          mode: 'index',
          intersect: false,
        },
        legend: {
          display: false,
        },
      },
    };

    this.cdref.detectChanges();
  }

  // Méthode pour gérer le clic sur un passage à niveau
  onPassageNiveauClick(e: any, d: any): void {
    this.currentMarker = e.target;
    this.curLatitude = e.target._latlng.lat;
    this.curLongitude = e.target._latlng.lng;
    this.goToStreetView(this.curLatitude, this.curLongitude);
  }

  onPassageNiveauSelect(e: any, d: any): void {
    // ouvre le sidebar
  }

  // Méthode pour gérer le clic sur Ecole
  onScolarClick(e: any, d: any): void {
    this.currentMarker = e.target;
    this.curLatitude = e.target._latlng.lat;
    this.curLongitude = e.target._latlng.lng;

    this.goToStreetView(this.curLatitude, this.curLongitude);
  }

  baselayerchange(event: any) {
    this.selectedBaseLayer = event.name;
  }

  lookUpVisibleGares() {
    // Obtenir les limites de la carte (la zone visible)
    const bounds = this.map.getBounds();

    // Filtrer les gares visibles
    const visibleGares = this.datastore.gares.filter((gare: any) => {
      //if (gare.siOuverte === true) {
      const coords = gare.theGeom.coordinates;
      const latLng = L.latLng(coords[1], coords[0]);
      return bounds.contains(latLng);
      //}
      /*if (gare.siOuverte === false) {
        const coords = gare.theGeom.coordinates;
        const latLng = L.latLng(coords[1], coords[0]);
        return bounds.contains(latLng);
      }*/
    });

    // Ajouter uniquement les marqueurs visibles au cluster et charger les isochrones
    let allGares: any[] = [];
    visibleGares.forEach((gare: any) => {
      const coords = gare.theGeom.coordinates;
      const isOpen = gare.siOuverte;
      // Charger les isochrones de la gare visible
      allGares.push(gare);
    });
    this.loadOnZoomIsochrone(allGares);
  }

  onSort(o: any) {
    this.cdref.detectChanges();
  }

  onPageChange(event: any) {
    this.cdref.detectChanges();
  }

  toggleIsochroneLayer(type: string, shouldBeVisible: boolean) {
    if (this.map.getZoom() < 11) {
      console.warn(
        'Le niveau de zoom est inférieur à 11, les isochrones ne seront pas affichés.'
      );
      return;
    }
    console.log('x');
    // Récupérer la couche isochrone correspondant au type
    let color = '';
    switch (type) {
      case '15 minutes à pied':
        color = 'blue';
        break;
      case '30 minutes à pied':
        color = 'red';
        break;
      case '10 minutes en voiture':
        color = 'green';
        break;
      default:
        console.warn(`Type d'isochrone inconnu : ${type}`);
        return;
    }

    const layersToToggle = Object.values(this.isochroneLayers).filter(
      (layerGroup) => {
        return layerGroup.getLayers().some((layer: any) => {
          return layer.options.color === color;
        });
      }
    );

    if (layersToToggle.length > 0) {
      layersToToggle.forEach((layerGroup) => {
        if (shouldBeVisible) {
          this.map.addLayer(layerGroup);
        } else {
          this.map.removeLayer(layerGroup);
        }
      });
    } else {
      console.warn(`Aucune couche d'isochrone trouvée pour ${type}`);
    }
  }

  private isZooming = false;

  private handleMapChange(): void {
    const zoomLevel = this.map.getZoom();
    this.getVisibleMarkers();
    this.map.invalidateSize();
    if (zoomLevel >= 11) {
      this.lookUpVisibleGares();
      this.showAllIsochrones();
    } else {
      this.hideAllIsochrones();
    }
  }

  onZoomEnd(event: L.LeafletEvent): void {
    this.isZooming = true;
    this.handleMapChange();
    // Réinitialiser isZooming après un court délai
    setTimeout(() => {
      this.isZooming = false;
    }, 50);
  }

  onMoveEnd(event: L.LeafletEvent): void {
    if (!this.isZooming) {
      this.handleMapChange();
    }
  }

  togglePolygonsVisibility(): void {
    const currentZoom = this.map.getZoom();
    const minZoomToShow = 11;

    Object.keys(this.polygonLayerGroup).forEach((layer) => {
      if (currentZoom < minZoomToShow) {
        this.map.removeLayer(this.polygonLayerGroup[layer]);
      } else {
        if (layer != '10 mn en voiture')
          this.map.addLayer(this.polygonLayerGroup[layer]);
      }
    });
  }

  addPolygon(layer: string, visible: boolean = true) {
    // Initialiser le LayerGroup
    if (!this.polygonLayerGroup[layer]) {
      if (visible == true)
        this.polygonLayerGroup[layer] = L.layerGroup().addTo(this.map);
      else this.polygonLayerGroup[layer] = L.layerGroup();
    }
    const layers = this.polygonLayerGroup[layer];

    this.addLayerToGroup(layers, layer, 'Isochrones');

    this.overlayMaps[layer] = this.polygonLayerGroup[layer];

    return this.polygonLayerGroup[layer];
  }

  // Méthode pour ajouter une couche à un groupe
  public addLayerToGroup(
    layer: any,
    layerName: string,
    groupName: string
  ): void {
    // Vérifier que groupedLayerControl existe
    if (!this.groupedLayerControl) {
      console.error("groupedLayerControl n'est pas initialisé");
      return;
    }

    // Ajouter la couche au groupe spécifié
    if (!this.overlayMaps[groupName]) {
      this.overlayMaps[groupName] = {};
    }

    // Vérifier si la couche existe déjà
    if (!this.overlayMaps[groupName][layerName]) {
      this.overlayMaps[groupName][layerName] = layer;

      // Mettre à jour groupedLayerControl uniquement si la couche n'existe pas déjà
      try {
        this.groupedLayerControl.addOverlay(layer, layerName, groupName);
      } catch (e) {
        //console.log(e);
      }
    }
  }

  public addLayer(
    name: string,
    layerName: string,
    display: boolean = false,
    groupName: string
  ) {
    const pIcons = ['Passages à niveau', 'Collège et lycée'];
    let LayersCluster;
    if (display) {
      LayersCluster = L.layerGroup().addTo(this.map);
    } else {
      LayersCluster = L.layerGroup();
    }

    const markersCluster = L.markerClusterGroup({
      disableClusteringAtZoom: 9,
      maxClusterRadius: 40,
    });
    LayersCluster.addLayer(markersCluster);

    this.addLayerToGroup(LayersCluster, layerName, groupName);
    let icons;
    try {
      if (pIcons.indexOf(layerName) > -1) icons = this.loadSmallIcon(name);
      else icons = this.loadIcon(name);
    } catch (e) {}
    return {
      layer: LayersCluster,
      markersCluster,
      icons,
    };
  }

  countPN(): Number {
    return 0;
  }

  getVisibleMarkers(): void {
    const bounds = this.map.getBounds(); // Obtenez les limites actuelles de la carte

    // 1. Filtrer les marqueurs pour ne conserver que ceux qui sont dans les limites de la carte
    const visibleES = this.datastore.es.filter((markerData: any) => {
      const coords = markerData.theGeom.coordinates;
      const latLng = L.latLng(coords[1], coords[0]);
      return bounds.contains(latLng);
    });
    const visiblePN = this.datastore.pniveau.filter((markerData: any) => {
      const coords = markerData.theGeom.coordinates;
      const latLng = L.latLng(coords[1], coords[0]);
      return bounds.contains(latLng);
    });

    // Filtrer et charger les passages à niveau visibles
    this.loadPNiveau(visiblePN);
    // Filtrer et charger les établissements scolaires visibles
    this.loadES(visibleES);
    // Filtrer et charger les ITE
    this.loadVisibleITE(bounds);
  }

  loadPNiveau(filteredData: any[] = []) {
    const bounds = this.map.getBounds();
    this.datalayers.pn.markersCluster.clearLayers();

    filteredData.forEach((d: any) => {
      const coords = d.theGeom.coordinates;
      const latLng = L.latLng(coords[1], coords[0]);

      if (bounds.contains(latLng)) {
        const marker = L.marker([coords[1], coords[0]], {
          icon: L.icon(this.datalayers.pn.icons.verysmall),
        }).setZIndexOffset(this.zOffets.PN);

        marker.bindTooltip(
          d.libelle +
            '<br>' +
            (d.refClassePassageNiveau?.definition ||
              'Aucune information disponible'),
          {
            permanent: false,
            direction: 'bottom',
          }
        );

        marker.on('click', (e: any) => this.onPassageNiveauSelect(e, d));
        marker.on('dblclick', (e: any) => this.onPassageNiveauClick(e, d));

        this.datalayers.pn.markersCluster.addLayer(marker);
      }
    });
  }

  loadES(filteredData: any[] = []) {
    const bounds = this.map.getBounds();
    this.datalayers.es.markersCluster.clearLayers();
    filteredData.forEach((d: any) => {
      const coords = d.theGeom.coordinates;
      const latLng = L.latLng(coords[1], coords[0]);
      if (bounds.contains(latLng)) {
        const marker = L.marker([coords[1], coords[0]], {
          icon: L.icon(this.datalayers.es.icons.small),
        });
        marker.bindTooltip(
          d.nomEtablissement + '<br>' + d.nbEleve + ' élèves',
          {
            permanent: false,
            direction: 'bottom',
          }
        );
        marker.on('dblclick', (e: any) => this.onScolarClick(e, d));
        this.datalayers.es.markersCluster.addLayer(marker);
      }
    });
  }

  addLignes(lines: any[], item: any) {
    const railwayLines: L.LayerGroup = L.layerGroup();

    lines.forEach((ligne) => {
      var coordinates;
      if (ligne.theGeom)
        coordinates = ligne.theGeom.coordinates[0].map(function (coord: any) {
          return [coord[1], coord[0]];
        });
      else
        coordinates = ligne.geometry.coordinates[0].map(function (coord: any) {
          return [coord[1], coord[0]];
        });
      // Ajouter une polyline à la carte
      let weight = 0;

      if (item.title.indexOf('Double') > -1) weight = 4;
      else weight = 2;

      const settings: any = {
        update: (p: any[]) => {
          for (let i = 0; i < p.length; i++) {
            p[i].bindTooltip(item.title, {
              permanent: false,
              direction: 'top',
            });
            p[i].on('mouseover', function (e: any) {
              var tooltip = e.target.getTooltip();
              tooltip.setLatLng(e.latlng);
              try {
                this.map.openTooltip(tooltip);
              } catch (e) {}
            });
            p[i].on('mouseout', function (e: any) {
              try {
                this.map.closeTooltip();
              } catch (e) {}
            });
          }
          var polyline = L.layerGroup(p);
          return polyline;
        },
        'Voie unique fret électrifiée': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '20, 0',
            weight: 3,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 2,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '5, 5',
            weight: 1,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Double voie électrifiée fret': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '20, 0',
            weight: 6,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 4,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '5, 5',
            weight: 2,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Double voie fret électrifiée': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '20, 0',
            weight: 6,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 4,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '5, 5',
            weight: 2,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Voie unique fret': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '20, 0',
            weight: 3,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: '#6EB123',
            weight: 2,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '5, 5',
            weight: 1,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Double voie fret': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '20, 0',
            weight: 6,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: '#6EB123',
            weight: 4,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#6EB123',
            dashArray: '5, 5',
            weight: 2,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Double voie suspendue': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#3a3b3c',
            weight: 3,
            dashArray: '10, 10',
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Voie unique suspendue': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#3a3b3c',
            weight: 3,
            dashArray: '10, 10',
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Voie unique fret suspendue': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#3a3b3c',
            weight: 3,
            dashArray: '10, 10',
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Double voie électrifiée suspendue': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#3a3b3c',
            weight: 3,
            dashArray: '10, 10',
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 4,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#3a3b3c',
            weight: 3,
            dashArray: '10, 10',
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Double voie électrifiée': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#533D8D',
            dashArray: '20, 0',
            weight: 6,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 4,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#533D8D',
            dashArray: '5, 5',
            weight: 2,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Voie unique électrifiée': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#533D8D',
            dashArray: '20, 0',
            weight: 3,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 2,
            dashOffset: '10',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#533D8D',
            dashArray: '5, 5',
            weight: 1,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Voie unique': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#533D8D',
            weight: 2,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },

        'Double voie': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#533D8D',
            weight: 4,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        LGV: (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#008FD6',
            weight: 5,
          });
          plTrainPost[1] = L.polyline(coordinates, {
            color: 'white',
            weight: 3,
            dashArray: '20, 20',
            dashOffset: '0',
          });
          plTrainPost[2] = L.polyline(coordinates, {
            color: '#008FD6',
            weight: 3,
            dashArray: '20, 20',
            dashOffset: '20',
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
        'Hors RFN': (coordinates: any) => {
          let plTrainPost: any[] = [];
          plTrainPost[0] = L.polyline(coordinates, {
            color: '#533D8D',
            weight: 2,
          });
          const polyline = settings.update(plTrainPost);
          return polyline;
        },
      };

      let polyline: any;
      if (settings[item.title]) polyline = settings[item.title](coordinates);
      else console.log('!!!!!!!', item.title);
      if (polyline) {
        polyline.addTo(this.map);
        railwayLines.addLayer(polyline);
      }
    });
    railwayLines.addTo(this.map);
    this.overlayMaps[item.title] = railwayLines;

    if (item.title) {
      if (item.title.indexOf('suspendue') > -1)
        this.addLayerToGroup(railwayLines, item.title, 'Lignes non exploitées');
      if (item.title.indexOf('fret') > -1)
        this.addLayerToGroup(railwayLines, item.title, 'Voies ferrées fret');
      else {
        if (item.title.indexOf('LGV') > -1)
          this.addLayerToGroup(
            railwayLines,
            item.title,
            'Lignes à grandes vitesse'
          );
        else {
          this.addLayerToGroup(railwayLines, item.title, 'Voies ferrées');
        }
      }
    }
  }

  loadGares() {
    const data = this.datastore.gares;
    const gares = this.datalayers.gares;
    const garesClosed = this.datalayers.garesClosed;
    let aom: any[] = [];

    data.forEach((d: any) => {
      // Ajoute l'AOM à la liste si elle n'est pas déjà présente
      if (d.nomAom && aom.indexOf(d.nomAom) === -1) {
        aom.push(d.nomAom);
      }

      if (d.siOuverte === true) {
        this.listGares.push({
          id: d.id,
          name: `${d.nomGare} (${d.inseeCommune})`,
          geom: d.theGeom,
        });

        const coords = d.theGeom.coordinates;
        const m = L.marker([coords[1], coords[0]], {
          icon: L.icon(gares.icons.small),
        }).setZIndexOffset(this.zOffets.GaresOuvertes);

        m.bindTooltip(d.nomGare, { permanent: false, direction: 'bottom' });
        m.on('click', (e: any) => this.onGareSelect(e, d));

        gares.markersCluster.addLayer(m);
        if (!this.Markers['gares']) this.Markers['gares'] = [];
        this.Markers['gares'].push({ marker: m, data: d });
      } else {
        this.listGaresClosed.push({
          id: d.id,
          name: `${d.nomGare} (${d.inseeCommune})`,
          geom: d.theGeom,
        });

        const coords = d.theGeom.coordinates;
        const m = L.marker([coords[1], coords[0]], {
          icon: L.icon(garesClosed.icons.small),
        }).setZIndexOffset(this.zOffets.GaresClosed);

        m.bindTooltip(d.nomGare, { permanent: false, direction: 'bottom' });
        m.on('click', (e: any) => this.onGareSelect(e, d));

        garesClosed.markersCluster.addLayer(m);
        if (!this.Markers['garesClosed']) this.Markers['garesClosed'] = [];
        this.Markers['garesClosed'].push({ marker: m, data: d });
      }
    });

    // Tri du tableau aom après avoir ajouté tous les éléments
    aom.sort();
  }

  loadVisibleITE(bounds: L.LatLngBounds): void {
    const iteTypes: Array<keyof typeof this.zOffets> = [
      'ITEBon',
      'ITEInutile',
      'ITENeuves',
      'ITEMauvais',
      'ITEUtilise',
    ];

    // Effacer les clusters ITE existants
    iteTypes.forEach((type) => {
      if (this.datalayers[`ite_${type.slice(3).toLowerCase()}`])
        this.datalayers[
          `ite_${type.slice(3).toLowerCase()}`
        ].markersCluster.clearLayers();
    });

    // Filtrer et ajouter les ITE visibles
    iteTypes.forEach((type) => {
      let filteredITE;
      let t = type.slice(3);

      if (this.datastore.ite[t])
        filteredITE = this.datastore.ite[t].filter((d: any) => {
          const coords = d.theGeom.coordinates;
          const latLng = L.latLng(coords[1], coords[0]);
          return bounds.contains(latLng);
        });
      if (filteredITE)
        filteredITE.forEach((d: any) => {
          const coords = d.theGeom.coordinates;
          const marker = L.marker([coords[1], coords[0]], {
            icon: L.icon(this.datalayers[`ite_${t.toLowerCase()}`].icons.ite),
          }).setZIndexOffset(this.zOffets[type]);

          marker.bindTooltip(
            d.raisonSociale +
              "<br>Etat de l'ITE : " +
              (d.etatIte || 'Aucune information disponible'),
            { permanent: false, direction: 'bottom' }
          );

          marker.on('click', (e: any) => this.onITESelect(e, d));

          this.datalayers[`ite_${t.toLowerCase()}`].markersCluster.addLayer(
            marker
          );
        });
    });
  }

  loadIte() {
    const bounds = this.map.getBounds();
    this.loadVisibleITE(bounds);
  }

  private addAOMPolygon(points: any[]): void {
    const polygon = L.polygon(points, {
      color: 'transparent', // Couleur du polygone
      fillOpacity: 0,
    }).addTo(this.map);
    console.log(polygon.toGeoJSON());
    this.selectedPassagesANiveau = this.checkPNInPolygon(
      polygon.toGeoJSON().geometry
    );

    // Ajuster la vue pour englober le polygone
    this.map.fitBounds(polygon.getBounds());
  }

  filterDataInBounds(data: any) {
    if (!data) return;
    const bounds = this.map.getBounds();
    return data.filter((item: any) => {
      const coords = item.theGeom.coordinates;
      const lat = coords[1];
      const lng = coords[0];
      return bounds.contains([lat, lng]);
    });
  }

  loadMarkers(): void {
    L.control
      .scale({
        metric: true,
        imperial: false,
        position: 'bottomright',
      })
      .addTo(this.map);
    this.myLegendes.push({
      color: 'red',
      libelle: '30 mn à pied',
      class: 'single',
    });
    this.myLegendes.push({
      color: 'blue',
      libelle: '15 mn à pied',
      class: 'single',
    });
    this.myLegendes.push({
      color: '#6EB123',
      libelle: '10 mn en voiture',
      class: 'single',
    });

    this.groupedLayerControl = L.control
      .groupedLayers(
        this.baseMaps,
        {},
        {
          exclusiveGroups: [],
          groupCheckboxes: true,
          position: 'topleft',
        }
      )
      .addTo(this.map);

    this.datalayers = {
      gares: this.addLayer('gares', 'Gares ouvertes', true, 'Gares'),
      garesClosed: this.addLayer(
        'gares-closed',
        'Gares fermées',
        true,
        'Gares'
      ),
      pn: this.addLayer(
        'pniveau',
        'Passages à niveau',
        false,
        'Points remarquables'
      ),
      es: this.addLayer(
        'ecole',
        'Collège et lycée',
        false,
        'Points remarquables'
      ),
      ite_bon: this.addLayer(
        'ite_bon',
        'ITE en bon état, inutilisées',
        false,
        'ITE'
      ),
      ite_neuf: this.addLayer(
        'ite_neuf',
        'ITE neuves, inutilisées',
        false,
        'ITE'
      ),
      ite_inutile: this.addLayer(
        'ite_inutile',
        'ITE inutilisable',
        false,
        'ITE'
      ),
      ite_mauvais: this.addLayer(
        'ite_mauvais',
        'ITE en mauvais état, inutilisées',
        false,
        'ITE'
      ),
      ite_utilise: this.addLayer('ite_utile', 'ITE utilisées', false, 'ITE'),
    };

    // load passageNiveau
    this.pNiveau.get().subscribe({
      next: (data) => {
        this.datastore.pniveau = data;
        this.loadPNiveau();
      },
    });

    // load Etablissements scolaires
    this.scolar.get().subscribe({
      next: (data) => {
        this.datastore.es = data;
        this.loadES();
      },
    });

    // load Gares (ouvertes, fermées)
    this.gares.get().subscribe({
      next: (data) => {
        this.datastore.gares = data;
        const aoms: any = {};
        for (let j = 0; j < data.length; j++) {
          if (!aoms[data[j].nomAom]) aoms[data[j].nomAom] = [];
          const coordinatesCopy = [...data[j].theGeom.coordinates]; // Crée une copie des coordonnées
          aoms[data[j].nomAom].push(coordinatesCopy.reverse()); // Inverser la copie, pas les coordonnées originales
        }
        this.aoms = aoms;
        this.loadGares();
      },
    });

    // load ITE
    this.etablissements.getAll().subscribe({
      next: (data) => {
        let custom: any = {};
        for (let i = 0; i < data.length; i++) {
          if (!custom[data[i].etatIte]) custom[data[i].etatIte] = [];
          custom[data[i].etatIte].push(data[i]);
        }
        data = null;

        this.datastore.ite = custom;
        this.datastore.ite['Utilise'] = this.datastore.ite['Utilisée'];
        this.loadIte();
      },
    });

    // load Réseau ferroviaire
    this.reseauxFerroviaires.get().subscribe({
      next: (lines) => {
        let types: any[] = [];
        let lignes: { [key: string]: any[] } = {}; // Utilise un objet pour stocker les lignes par type
        // Parcourir les lignes pour les trier par infrastructure
        for (let i = 0; i < lines.length; i++) {
          if (types.indexOf(lines[i].infrastructure) === -1) {
            //console.log(lines[i].infrastructure);
            types.push(lines[i].infrastructure);
            lignes[lines[i].infrastructure] = []; // Initialise l'entrée pour ce type
          }

          lignes[lines[i].infrastructure].push(lines[i]); // Ajoute les lignes dans la catégorie appropriée
        }

        // Itérer sur les types dans 'lignes' pour appeler addLignes
        for (let el of types) {
          this.addLignes(lignes[el], { title: el, color: {} });
        }
      },
    });
  }

  adjustHeight() {
    this.height = window.innerHeight - 70;
    this.cdref.detectChanges();
  }

  loadSearchData() {
    let search: any[] = [];
    this.communes.lookUp().subscribe({
      next: (data_communes: any) => {
        this.departements.lookUp().subscribe({
          next: (data: IDepartements[]) => {
            this.gares.get().subscribe({
              next: (data_gares) => {
                let aom: any[] = [];
                for (let i = 0; i < data_gares.length; i++) {
                  if (data_gares[i].siOuverte === true)
                    this.gareOuverte.push(data_gares[i].id);
                  else this.gareClosed.push(data_gares[i].id);
                  if (aom.indexOf(data_gares[i].nomAom) == -1)
                    aom.push(data_gares[i].nomAom);
                  search.push({
                    id: 'gare-' + data_gares[i].id,
                    name: data_gares[i].nomGare,
                    group: 'Gares',
                  });
                }
                // Trier la liste 'aom' par ordre alphabétique
                aom.sort((a, b) => a.localeCompare(b));

                // Trier la liste 'search' des gares par ordre alphabétique
                search.sort((a, b) => {
                  if (a.group === 'Gares' && b.group === 'Gares') {
                    return a.name.localeCompare(b.name);
                  }
                  return 0; // Ne pas modifier l'ordre si le groupe est différent
                });

                this.regions.lookUp().subscribe({
                  next: (data_regions: any) => {
                    for (let i = 0; i < data_regions.length; i++)
                      search.push({
                        id: 'region-' + data_regions[i].id,
                        name: data_regions[i].nom,
                        group: 'Régions',
                      });
                    for (let i = 0; i < data.length; i++)
                      search.push({
                        id: 'dpt-' + data[i].code,
                        name: data[i].nom + ' (' + data[i].code + ')',
                        group: 'Départements',
                      });
                    for (let i = 0; i < aom.length; i++) {
                      search.push({
                        id: 'aom-' + aom[i],
                        name: aom[i],
                        group: 'AOM',
                      });
                    }
                    const uniqueEntries: any = {};

                    for (let i = 0; i < data_communes.length; i++) {
                      const key = data_communes[i].id;

                      if (!uniqueEntries[key]) {
                        uniqueEntries[key] = {
                          id: 'commune-' + data_communes[i].comCode,
                          name:
                            data_communes[i].comNom +
                            ' (' +
                            data_communes[i].comCode +
                            ')',
                          group: 'Communes',
                        };
                      }
                    }
                    const tbl = Object.values(uniqueEntries);
                    tbl.sort((a: any, b: any) => {
                      return a.name.localeCompare(b.name);
                    });

                    search.push(...tbl);

                    this.searchData = search;
                    this.cdref.detectChanges();
                  },
                });
              },
            });
          },
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  ngOnInit(): void {
    this.subscription = this.actionService.formAction$.subscribe(() => {
      this.performAction();
    });
    this.loadSearchData();
    this.map.invalidateSize();
    // load options
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue(
      '--text-color-secondary'
    );
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');
    this.optionsStackedNoLegend = {
      maintainAspectRatio: false,
      aspectRatio: 0.8,
      responsive: true,
      plugins: {
        tooltip: {
          mode: 'index',
          intersect: false,
        },
        legend: {
          labels: {
            color: textColor,
          },
        },
      },
      scales: {
        x: {
          stacked: true,
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
        y: {
          stacked: true,
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
      },
    };
    this.optionsStackedFreq = {
      maintainAspectRatio: false,
      aspectRatio: 1.8,
      height: 100,
      plugins: {
        tooltip: {
          mode: 'index',
          intersect: false,
        },
        legend: {
          labels: {
            color: textColor,
          },
        },
      },
      scales: {
        x: {
          stacked: true,
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
        y: {
          stacked: true,
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
      },
    };
    this.optionsStackedF = {
      maintainAspectRatio: false,
      height: 500,
      aspectRatio: 0.8,
      responsive: true,
      plugins: {
        tooltip: {
          mode: 'index',
          intersect: false,
        },
        legend: {
          labels: {
            color: textColor,
          },
        },
      },
      scales: {
        x: {
          stacked: true,
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
        y: {
          stacked: true,
          ticks: {
            color: textColorSecondary,
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false,
          },
        },
      },
    };
  }

  // Méthode pour déplacer le bouton de polygone
  movePolygonButton(position: string): void {
    // Accéder à l'élément du bouton Polygone via Renderer2
    const drawToolbar = this.map.nativeElement.querySelector(
      '.leaflet-draw-toolbar'
    );

    if (drawToolbar) {
      switch (position) {
        case 'topright':
          this.renderer.setStyle(drawToolbar, 'top', '10px');
          this.renderer.setStyle(drawToolbar, 'right', '10px');
          this.renderer.setStyle(drawToolbar, 'left', 'auto');
          break;
        case 'topleft':
          this.renderer.setStyle(drawToolbar, 'top', '10px');
          this.renderer.setStyle(drawToolbar, 'left', '10px');
          this.renderer.setStyle(drawToolbar, 'right', 'auto');
          break;
        case 'bottomleft':
          this.renderer.setStyle(drawToolbar, 'bottom', '10px');
          this.renderer.setStyle(drawToolbar, 'left', '10px');
          this.renderer.setStyle(drawToolbar, 'top', 'auto');
          this.renderer.setStyle(drawToolbar, 'right', 'auto');
          break;
        case 'bottomright':
          this.renderer.setStyle(drawToolbar, 'bottom', '10px');
          this.renderer.setStyle(drawToolbar, 'right', '10px');
          this.renderer.setStyle(drawToolbar, 'top', 'auto');
          this.renderer.setStyle(drawToolbar, 'left', 'auto');
          break;
        default:
          // Position par défaut en haut à gauche
          this.renderer.setStyle(drawToolbar, 'top', '10px');
          this.renderer.setStyle(drawToolbar, 'left', '10px');
          this.renderer.setStyle(drawToolbar, 'right', 'auto');
          break;
      }
    }
  }

  /** menu handle */
  hideMenu() {
    this.displaySideMenu = false;
  }

  performAction() {
    if (this.FirstTime === false) {
      this.displaySideMenu = true;
    }
    this.FirstTime = false;
  }

  /** Map ready */
  onMapReady(map: Map) {
    this.map = map;
    document.body.style.overflow = 'hidden';
    this.drawnItems = new L.FeatureGroup();
    this.currentLayer = this.LayerOSM;

    // Ajouter le contrôle de dessin
    const drawControl = new L.Control.Draw({
      edit: {
        featureGroup: this.drawnItems,
        edit: false,
        remove: false,
      },
      draw: {
        polygon: {},
        polyline: false,
        circle: false,
        rectangle: false,
        marker: false,
        circlemarker: false,
      },
    });

    this.map.addControl(drawControl);

    // Gérer les événements de création (par exemple, lorsqu'un polygone est dessiné)
    this.map.on(L.Draw.Event.CREATED, (event: any) => {
      const layer = event.layer;
      this.drawnItems.addLayer(layer);
      this.onPolygonDrawn(event);
    });
    this.loadMarkers();
    if (this.selectedBaseLayer === 'IGN') {
      this.map.setZoom(this.map.getZoom());
    }
    if (this.groupedLayerControl) {
      this.map.removeControl(this.groupedLayerControl);
    }
  }
}

export interface Item {
  name: string;
}

export interface GroupedItem {
  label: string;
  items: Item[];
}

export interface Geometry {
  type: string;
  coordinates: number[];
}

export interface Feature {
  type: string;
  geometry: Geometry;
  properties: any;
}

export interface GeoJSON {
  type: string;
  features: Feature[];
}

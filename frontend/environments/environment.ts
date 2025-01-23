export const environment = {
  production: false,
  version: '1.0.0',
  clientId: 'app',
  orionUrl:
    'https://orion.cerema.dev/realms/38820b69-5fa4-4ceb-b510-fcbf25ab94f4',
  redirectUri: 'https://app-szu.cerema.dev/login',
  allowedUrls: [
    'https://api-szu.cerema.dev/api/',
    'https://api-szu.cerema.dev/api-open/',
  ],
  serverApiUrl: 'https://api-szu.cerema.dev/api',
  deconnexionUri: 'https://app-szu.cerema.dev/deconnexion',
  config: {
    version: '1.0.4',
  },
};

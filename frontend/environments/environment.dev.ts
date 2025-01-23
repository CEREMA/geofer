export const environment = {
  production: false,
  version: '1.0.0',
  clientId: 'app',
  orionUrl: 'https://orion.cerema.dev/auth/realms/demo',
  redirectUri: 'http://localhost:4200/login',
  allowedUrls: [
    'http://localhost:8080/api/',
    'http://localhost:8080/api-open/',
  ],
  serverApiUrl: 'http://localhost:8080/api',
  deconnexionUri: 'http://localhost:8080/deconnexion',
  config: {
    version: '1.0.4',
  },
};

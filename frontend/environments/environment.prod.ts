export const environment = {
  production: true,
  version: '1.0.0',
  clientId: '${CI_ORION_CLIENT_ID}',
  orionUrl: '${CI_ORION_URL}/realms/${CI_ORION_REALM}',
  redirectUri: '${CI_ENVIRONMENT_URL}/login',
  allowedUrls: [
    'https://${CI_PROJECT_NAME}-api-${CI_ENVIRONMENT_NAME}.${CI_DOMAIN}/api/',
    'https://${CI_PROJECT_NAME}-api-${CI_ENVIRONMENT_NAME}.${CI_DOMAIN}/api-open/',
  ],
  serverApiUrl:
    'https://${CI_PROJECT_NAME}-api-${CI_ENVIRONMENT_NAME}.${CI_DOMAIN}/api',
  deconnexionUri: '${CI_ENVIRONMENT_URL}/deconnexion',
  config: {
    version: '1.0.4',
  },
};

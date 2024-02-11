# multicert_countries

## Manual
local url -> https://localhost:{port}
cloud -> https://country-data-x969.onrender.com

documentation available at /documentation and /swagger-ui/index.html

A single GET endpoint is available, /countries/{countryCode} where the countryCode is the country's cca3 code

### Environment variables

locally: COUNTRY_API_URL=https://countryinfoapi.com/api/;KEYSTORE_PASS=country;PORT=8080;SSL_ENABLED=true

since render already has built in support for https and takes care of the certificate provisioning and renewal, the cloud port is set to 443 and ssl is disabled

### Running locally
docker build . (if you want to see the output)
docker run -it $(docker build -q .) to build and run
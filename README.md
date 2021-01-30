# Find near by places using Maps API

Help users to find nearby Cafes, Bars and Restaurants.

This project uses the Fused Location Google Play service to get the device location,
fetch the nearby places using Google Places API and display a list of results.

## Setup

Create your Google Maps API key and add your key in the "gradle.properties" file.
Get an API key following this documentation:
[https://developers.google.com/places/web-service/get-api-key]

## Android Studio

Just import the project in Android Studio IDE

## How to build using CLI (Linux/MacOS)

Execute the following in the project root directory
```
git clone https://github.com/emrachid/nearby-places.git
cd nearby-places
chmod +x gradlew
./gradlew build
```

## API

This project uses the Google Maps API Nearby Search request.
See the documentation [here][https://developers.google.com/places/web-service/search#PlaceSearchRequests]

## License

Apache 2.0. See the LICENSE file for details.

# Find near by places - Android Kotlin project

Help users to find nearby Cafes, Bars and Restaurants.

This project uses the Fused Location Google Play service to get the device location,
fetch the nearby places using Google Places API and/or LocationIQ API.

It was implementated following the SOLID principles using Clean Architecture in Android and using the Kotlin programming language.

External libraries used:
* Dagger for dependency injection
* Room to persist data into DB
* Retrofit for network communications
* Glide media management and image loading framework
* Coroutines with Livedata
* ViewModel
* RecyclerView

## Setup

Edit the "gradle.properties" files to add API keys.

This project will work with Maps API and/or LocationIQ depending on the following configuration:
* If only the Maps API key is set, it will use only the Maps API.
* If only the LocationIQ key is set, it will use only the LocationIQ.
* If both keys are set, it will use both APIs.

Create your Google Maps API key and add your key in the "gradle.properties" file.

Get a Google Maps API key following this documentation:
https://developers.google.com/places/web-service/get-api-key

You can also use the LocationIQ API. This is optional.

Get a LocationIQ key following this documentation:
https://locationiq.com/docs

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

This project uses the Google Maps API Nearby Search request and Geocoding for reverse the location into street address.
See the Search request documentation [here](https://developers.google.com/places/web-service/search#PlaceSearchRequests) and
the Geocoding request documentation [here](https://developers.google.com/maps/documentation/geocoding/overview#ReverseGeocoding).

Optionally, it can be used the LocationIQ for searching places and reverse geocoding.
See the documentation [here](https://locationiq.com/docs).

## License

Apache 2.0. See the LICENSE file for details.

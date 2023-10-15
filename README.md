# RouteApi
### API for calculating the distance between two points on the earth's surface. 

API for calculating the distance between two points on the earth's surface, as well as for getting a route on public roads using an additional. The API application will be available at [Openrouteservice](https://openrouteservice.org/dev/#/api-docs/v2/directions) in JSON format.
The [Haversine Formula](https://en.wikipedia.org/wiki/Haversine_formula#:~:text=The%20haversine%20formula%20determines%20the,and%20angles%20of%20spherical%20triangles.) was used to calculate the distance. 

## Installation and launch
1. Make sure you have Java and Maven installed.

2. Clone the repository to your computer:
   >git clone <https://github.com/Tim01Bro/RouteApi.git>
3. Go to the project directory:
   >cd route_api
4. Build a project using Maven:
   >mvn clean install
5. Launching the app:

   There are several options for launching the application:
   1. Launching via the console:
      >java -jar target/routeapi-0.1.war
   2. Launch via Docker, first go to the project directory and enter the command in the console:
      >docker compose up
   3. Launch via the IDE interface

The application will be available at [/api/v1/distance](http://localhost:8080/api/v1/distance).
## Using

The API provides the following endpoint for calculating the distance between two points and getting a route:
> GET /api/v1/distance/inkm?startLatitude={startLatitude}&startLongitude={startLongitude}&endLatitude={endLatitude}&endLongitude={endLongitude}
Where:
- `startLatitude` and `startLongitude` - latitude and longitude of the first point, respectively,
- `endLatitude` and `endLongitude` - latitude and longitude of the second point, respectively.

Request example:
>localhost:8080/api/v1/distance/inkm?startLatitude=52.231838&startLongitude=21.005995&endLatitude=52.247976&endLongitude=21.015256

Response example:
```json
{
   "startLatitude": 52.231838,
   "startLongitude": 21.005995,
   "endLatitude": 52.247976,
   "endLongitude": 21.015256,
   "distanceKm": 1.9020368211503094,
   "dateRequest": "2023-10-15 18:57:33"
}
```
The distanceKm is expressed in kilometers.

The API provides the following endpoint for get route between two points:
> GET /api/v1/distance/inroad?startLatitude={startLatitude}&startLongitude={startLongitude}&endLatitude={endLatitude}&endLongitude={endLongitude}
Where:
- `startLatitude` and `startLongitude` - latitude and longitude of the first point, respectively,
- `endLatitude` and `endLongitude` - latitude and longitude of the second point, respectively.

Request example:
>localhost:8080/api/v1/distance/inroad?startLatitude=52.231838&startLongitude=21.005995&endLatitude=52.247976&endLongitude=21.015256

Response example:
```json
{
   "startLatitude": 52.231838,
   "startLongitude": 21.005995,
   "endLatitude": 52.247976,
   "endLongitude": 21.015256,
   "route": "{bbox=[21.002467, 52.230081, 21.018853, 52.248613], routes=[{summary={distance=4700.7, duration=740.1}, segments=[{distance=4700.7, duration=740.1, steps=[{distance=32.5, duration=11.7, type=11.0, instruction=Head west, name=-, way_points=[0.0, 3.0]}, {distance=96.5, duration=23.2, type=1.0, instruction=Turn right, name=-, way_points=[3.0, 4.0]}, {distance=201.6, duration=36.3, type=1.0, instruction=Turn right, name=-, way_points=[4.0, 7.0]}, {distance=1013.7, duration=185.1, type=1.0, instruction=Turn right, name=-, way_points=[7.0, 35.0]}, {distance=446.0, duration=73.2, type=3.0, instruction=Turn sharp right onto Świętokrzyska, name=Świętokrzyska, way_points=[35.0, 45.0]}, {distance=353.7, duration=40.3, type=0.0, instruction=Turn left onto Marszałkowska, name=Marszałkowska, way_points=[45.0, 52.0]}, {distance=548.5, duration=68.7, type=1.0, instruction=Turn right onto Królewska, name=Królewska, way_points=[52.0, 64.0]}, {distance=10.1, duration=3.7, type=0.0, instruction=Turn left, name=-, way_points=[64.0, 65.0]}, {distance=303.5, duration=50.7, type=6.0, instruction=Continue straight onto Plac marszałka Józefa Piłsudskiego, name=Plac marszałka Józefa Piłsudskiego, way_points=[65.0, 72.0]}, {distance=34.0, duration=4.9, type=13.0, instruction=Keep right, name=-, way_points=[72.0, 76.0]}, {distance=34.8, duration=5.0, type=2.0, instruction=Turn sharp left onto Trębacka, name=Trębacka, way_points=[76.0, 78.0]}, {distance=201.0, duration=22.3, type=1.0, instruction=Turn right onto Moliera, name=Moliera, way_points=[78.0, 86.0]}, {distance=238.7, duration=34.9, type=4.0, instruction=Turn slight left onto Nowy Przejazd, name=Nowy Przejazd, way_points=[86.0, 95.0]}, {distance=360.4, duration=47.5, type=5.0, instruction=Turn slight right onto Aleja \"Solidarności\", name=Aleja \"Solidarności\", way_points=[95.0, 99.0]}, {distance=364.6, duration=45.3, type=6.0, instruction=Continue straight onto Aleja \"Solidarności\", name=Aleja \"Solidarności\", way_points=[99.0, 105.0]}, {distance=239.7, duration=37.6, type=2.0, instruction=Turn sharp left, name=-, way_points=[105.0, 116.0]}, {distance=29.7, duration=13.1, type=12.0, instruction=Keep left, name=-, way_points=[116.0, 119.0]}, {distance=166.0, duration=26.8, type=2.0, instruction=Turn sharp left onto Grodzka, name=Grodzka, way_points=[119.0, 125.0]}, {distance=25.8, duration=9.9, type=1.0, instruction=Turn right, name=-, way_points=[125.0, 127.0]}, {distance=0.0, duration=0.0, type=10.0, instruction=Arrive at your destination, on the right, name=-, way_points=[127.0, 127.0]}]}], bbox=[21.002467, 52.230081, 21.018853, 52.248613], geometry=ypx}Hepe_CFd@DXAZaD`Bi@}Dg@iDk@cEhDcBN@VELG~@e@JGVWPU|C{AD@DPd@fDd@jD\\`CF|@ZxB?PGToCrAsDdBkB~@e@Te@Rc@TYLgAh@iBz@UTWuBWuBCu@_@_DaAyHYkAMgAAOs@{FIy@_@PwDpBcBz@_Bx@qCvAQHSRMo@aAyEgB}I{@}Du@mD]eB_@_Be@wAW_AKo@C]A]QFkDfAmCt@iAZYHQN]l@e@fAMNKBOMGO@|@Ed@c@VKFo@\\_@R_@Vo@d@m@`@mAv@_B|CS`@Mf@_@pDK^SPS@MGa@g@Q}Ca@}Hi@uP?EMsHOmBW_BYeAc@sA}BgHD~Av@vC@`@AXCRGLWTS@ICWYeAuCIUGMSUFd@`A|CPh@Z^LDjAJ?THn@, way_points=[0.0, 127.0], legs=[]}], metadata={attribution=openrouteservice.org | OpenStreetMap contributors, service=routing, timestamp=1.697396463569E12, query={coordinates=[[21.005995, 52.231838], [21.015256, 52.247976]], profile=driving-car, format=json}, engine={version=7.1.0, build_date=2023-07-09T01:31:50Z, graph_date=2023-10-08T10:15:30Z}}}",
   "dateRequest": "2023-10-15 19:01:04"
}
```
The distanceKm is expressed in kilometers.

To use [Postman](https://github.com/Tim01Bro/RouteApi/tree/master/postman), there is a directory called "Postman" where the collection for import is located
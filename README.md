#Bet Application

##How to Run

The application is spring boot app, so it's very easy to setup.

you could simply execute maven command line like below

```
./mvnw spring-boot:run
```

or build a excitable jar

```
./mvnw clean package
java -jar target/bets-app-0.0.1-SNAPSHOT.jar
```


###Rest interface 

The application has jwt enabled to secure the request. 
so to be able to successfully visit the service, we need to register a user.

**1. register a new user with similar command-line**

```
curl -H "Content-Type: application/json" -X POST -d '{
    "username": "marcus",
    "password": "password"
}' http://localhost:8080/bets/user/signup

```

**2. sign to application to receive security token**

```
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "marcus",
    "password": "password"
}' http://localhost:8080/login
```

then you should get similar response like below.


```
HTTP/1.1 200 
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJjdXMiLCJleHAiOjE1MzAxNDMxMzN9.sn_ItuaV32h5W4NoYDx1uWlPzBN6T0GS65pdkr8LutsdeYKfGJ2AvguDr_EW1mjm0zxIfygqKbJqIF5OjoP1jQ
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Length: 0
Date: Tue, 26 Jun 2018 23:45:33 GMT
```
The token get attached to header "Authorization". once we have token, we can use the security token to access rest services.

visit a service without security token, we will receive 403 fobden error.

For example

```
curl -H "Content-Type: application/json" \
-X GET http://localhost:8080/bets/report/totalBetsSoldPerHour
```

Error response

```
{"timestamp":"2018-06-27T00:23:26.933+0000",
 "status":403,"error":"Forbidden",
 "message":"Access Denied",
 "path":"/bets/report/totalBetsSoldPerHour"}

```


**3.place bets**

place bet service can be access via following url
```
http://localhost:8080/bets/submit
```

then we cloud attach security token to visit the service.

```
curl -H "Content-Type: application/json" \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJjdXMiLCJleHAiOjE1MzAxNDMxMzN9.sn_ItuaV32h5W4NoYDx1uWlPzBN6T0GS65pdkr8LutsdeYKfGJ2AvguDr_EW1mjm0zxIfygqKbJqIF5OjoP1jQ" \
-X POST -d '[
                    {
                    	"DateTime": "2018-01-01 14:56",
                        "BetType": "TRIFECTA",
                        "PropNumber": 104567,
                        "CustomerID": 1080,
                        "Investment": 100.00
                    },
                    {
                        "DateTime": "2018-01-01 12:56",
                        "BetType": "WIN",
                        "PropNumber": 103333,
                        "CustomerID": 1081,
                        "Investment": 500.50
                    }
            ]'  http://localhost:8080/bets/submit

```

if the bet record get saved successfully, then the response is
 
 ```
{"betPlaced":true}
```

**4.Statistic Report - total investment per bet type report**

Service URL
```
http://localhost:8080/bets/report/totalInvestmentPerType

```
Example Request

```
curl -H "Content-Type: application/json" \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJjdXMiLCJleHAiOjE1MzAxNDMxMzN9.sn_ItuaV32h5W4NoYDx1uWlPzBN6T0GS65pdkr8LutsdeYKfGJ2AvguDr_EW1mjm0zxIfygqKbJqIF5OjoP1jQ" \
-X GET http://localhost:8080/bets/report/totalInvestmentPerType

```

Example Response

```
[["WIN",500.50],["TRIFECTA",100.00]]
```

**5.Statistic Report - total investment per CustomerID**

Service URL
```
http://localhost:8080/bets/report/totalInvestmentPerCustomer

```
Example Request
```
curl -H "Content-Type: application/json" \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJjdXMiLCJleHAiOjE1MzAxNDMxMzN9.sn_ItuaV32h5W4NoYDx1uWlPzBN6T0GS65pdkr8LutsdeYKfGJ2AvguDr_EW1mjm0zxIfygqKbJqIF5OjoP1jQ" \
-X GET http://localhost:8080/bets/report/totalInvestmentPerCustomer

```

Example Response

```
[[1081,500.50],[1080,100.00]]
```

**6.Statistic Report -total bets sold per bet type**

Service URL
```
http://localhost:8080/bets/report/totalBetsPerType

```
Example Request
```
curl -H "Content-Type: application/json" \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJjdXMiLCJleHAiOjE1MzAxNDMxMzN9.sn_ItuaV32h5W4NoYDx1uWlPzBN6T0GS65pdkr8LutsdeYKfGJ2AvguDr_EW1mjm0zxIfygqKbJqIF5OjoP1jQ" \
-X GET http://localhost:8080/bets/report/totalBetsPerType

```

Example Response

```
[["WIN",1],["TRIFECTA",1]]
```

**7.Statistic Report - total number of bets sold per hour**

Service URL
```
http://localhost:8080/bets/report/totalBetsSoldPerHour

```

Example Request
```
curl -H "Content-Type: application/json" \
-H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJjdXMiLCJleHAiOjE1MzAxNDMxMzN9.sn_ItuaV32h5W4NoYDx1uWlPzBN6T0GS65pdkr8LutsdeYKfGJ2AvguDr_EW1mjm0zxIfygqKbJqIF5OjoP1jQ" \
-X GET http://localhost:8080/bets/report/totalBetsSoldPerHour

```
Example Response

```
[["2018-01-02 01",1],["2018-01-01 11",1]]
```

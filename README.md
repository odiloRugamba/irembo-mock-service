# irembo-mock-service

## Description

Welcome Onboard fellow Developer, in this project you will be able to develop and deploy mock service.
this is project it use WireMock as HTTP mock server. 
At its core it is web server that can be primed to serve canned responses to particular requests (stubbing) and that captures incoming requests so that they can be checked later (verification).  (https://wiremock.org/docs/)

## Functionality and instructions

### Run on local machine 
   1. Build  project
       - `cd ${irembo-mock-service path}`
       - `mvn clean install -DskipTests`
   3. Run project
      - `mvn spring-boot:run`
   
## create mock files (.json)
- `cd ${irembo-mock-service path}/resources/stubs/mappings/`
create or select a folder related to your mock project for better separation of concerns
- create Json file, e.g: `sampleMockApi.json`
   ### mock content
```json
{
  "request": {
    "method": "GET",
    "url": "/some/thing"
  },
  "response": {
    "status": 201,
    "body": "Hello world!",
    "headers": {
      "Content-Type": "application/json"
    }
  }
}

```

   ### Request matching example
```json
{
    "request": {
        "urlPath": "/everything",
        "method": "ANY",
        "headers": {
            "Accept": {
                "contains": "xml"
            }
        },
        "queryParameters": {
            "search_term": {
                "equalTo": "WireMock"
            }
        },
        "cookies": {
            "session": {
                "matches": ".*12345.*"
            }
        },
        "bodyPatterns": [
            {
                "equalToXml": "<search-results />"
            },
            {
                "matchesXPath": "//search-results"
            }
        ],
        "multipartPatterns": [
            {
                "matchingType": "ANY",
                "headers": {
                    "Content-Disposition": {
                        "contains": "name=\"info\""
                    },
                    "Content-Type": {
                        "contains": "charset"
                    }
                },
                "bodyPatterns": [
                    {
                        "equalToJson": "{}"
                    }
                ]
            }
        ],
        "basicAuthCredentials": {
            "username": "test@example.com",
            "password": "testteenjefftyfsdfs"
        }
    },
    "response": {
        "status": 200
    }
}

```
### Simulating Faults
EMPTY_RESPONSE: Return a completely empty response.

MALFORMED_RESPONSE_CHUNK: Send an OK status header, then garbage, then close the connection.

RANDOM_DATA_THEN_CLOSE: Send garbage then close the connection.

CONNECTION_RESET_BY_PEER: Close the connection, setting SO_LINGER to 0 and thus preventing the TIME_WAIT state being entered. 

```json
{
    "request": {
        "method": "GET",
        "url": "/fault"
    },
    "response": {
        "fault": "MALFORMED_RESPONSE_CHUNK"
    }
}
```





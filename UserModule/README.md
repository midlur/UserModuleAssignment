User Module
=======================
----------

Description
===========

Backend implementation for mobile and web applications to store and retrieve user data.

Services
=========
The functions are described in detail below.

/locationData
-----

This API saves User Location with a valid request.

    Request: POST http://{HOST}:{PORT}/user/locationData
    Request Body : {
    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
    "createdOn": "2022-03-08T11:50:22.524",
    "location": {
        "latitude": 53.25742342295784,
        "longitude": 11.540583401747602
    }
    }
     Response: Base response with the following structure
    {
    "message": "User Location saved successfully",
    "code": 200
    }

/saveUser
-----

This API will be used when user details needs to be updated.


    Request: POST http://{HOST}:{PORT}/user/saveUser
    Request body: {
    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
    "createdOn": "2022-03-08T11:44:00.524",
    "email": "medha.mehta@gmail.com",
    "firstName": "Medha",
    "secondName": "Mehta"
    }
    Response: Base response with the following structure
    {
    "message": "User Details saved successfully",
    "code": 200
    }


/latestLocation/{id}
---------------
This API retrieves latest location of the user on the basis of provided userId.

    Request: GET http://{HOST}:{PORT}/user/latestLocation/{id}
    Path Paramter <id> : 2e3b11b0-07a4-4873-8de5-d2ae2eab26b2
    Response Stucture : 
    {
    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
    "createdOn": "2022-03-08T11:50:22.524+00:00",
    "email": "medha.mehta@gmail.com",
    "firstName": "Medha",
    "secondName": "Mehta",
    "location": {
        "latitude": 53.25742342295784,
        "longitude": 11.540583401747602
    }
    }


/userLocationsByTimeRange
---------------
This API retrieves All User locations on the basis of provided TimeRange.

    Request: GET http://{HOST}:{PORT}/user/userLocationsByTimeRange
    Request Body : 
    {
    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
    "fromDate": "2022-03-08T11:40:00.524",
    "toDate": "2022-03-08T11:50:00.524"
    }
    Response Stucture : 
    {
    "userId": "2e3b11b0-07a4-4873-8de5-d2ae2eab26b2",
    "locationsList": [
        {
            "createdOn": "2022-03-08T11:44:22.524+00:00",
            "location": {
                "latitude": 53.25742342295784,
                "longitude": 11.540583401747602
            }
        },
        {
            "createdOn": "2022-03-08T11:45:22.524+00:00",
            "location": {
                "latitude": 53.25742342295784,
                "longitude": 11.540583401747602
            }
        }
    ]
    }

Technical Details for the implementation
---------------
For the Data Storage H2 in memory database is used. When the spring boot application starts in memory database is created and acc to the 
spring properties mentioned all the ddl statements will be executed. 
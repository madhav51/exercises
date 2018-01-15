# exercises
1.  Clone the repository https://github.com/madhav51/exercises.git.
2. Import the projects into eclipse.
3. Run maven clean, maven install.
4. Deploy 'MarketPlaceService' on the server.

API's.
 #Creating a project 
    http://localhost:8080/market-place/service/project/create 
 #Creating a bid for the project 
    http://localhost:8080/market-place/service/project/bid
 #Get all open projects 
    http://localhost:8080/market-place/service/project/openProjects
 #Get a project by id 
    http://localhost:8080/market-place/service/project

Access to API's via Curl:

#Create Project
curl -X POST "http://localhost:8080/market-place/service/project/create" -H "accept: text/plain" -H "Content-Type: application/json" -d "{ \"title\": \"Test\", \"description\": \"Test\", \"maxBidAmout\": \"500\", \"maxbidDate\": \"15/01/2018\", \"userId\": 10}"

#Bid for a project
curl -X POST "http://localhost:8080/market-place/service/project/bid" -H "accept: text/plain" -H "Content-Type: application/json" -d "{ \"bidAmount\": \"200\", \"projectId\": 1, \"userId\": 20}"

#Get all open projects
curl -X GET "http://localhost:8080/market-place/service/project/openProjects" -H "accept: application/json"

#Get a project by id
curl -X GET "http://localhost:8080/market-place/service/project/1" -H "accept: application/json"

Access via Java Client.
Run MarketPlaceClient.java to view the output in  console.

//////////////// MySQL Necessities /////////////////
var mysql = require('mysql');
var pool = mysql.createPool({
	connectionLimit: 100,
	host: 'localhost',
	user: 'root',
	database: 'ten0clock',
	password: 'hopscotch90210'
});
var queries = require('./queries.js');




////////////// HTTP Server Necessities /////////////////

var http = require('http');
var express = require('express');
var bodyParser = require('body-parser');
var app = express();




///////////////// Request Routing //////////////////////////

var router = express.Router();
router.use(bodyParser.json());

router.post('/user', function(request, response) { // create new user
	var newUserData = request.body;
	var queryResult = queries.createUser(pool, newUserData, function(success) {
		if (success != -1) {
			response.send({"status": success});
		} else {
			response.send({"status": success}); // the value of success is either -1 indicating failure or the row ID
		}
	});
});

router.get('/user/:username', function(request, response) { // get data of particular user
	var queryResult = queries.getUserData(pool, request.params.username, function(success, user) {
		if (success) {
			response.send(user);
		} else {
			response.send({"status": "Failure"});
		}
	});
});
		

router.post('/event', function(request, response) { // create new event
	var newEventData = request.body;
	var queryResult = queries.createEvent(pool, newEventData, function(success) {
		if (success != -1) {
			response.send({"status": success});
		} else {
			response.send({"status": success});
		}
	});
});


router.post('/venue', function(request, response) { // create new venue
        var newVenueData = request.body;
        var queryResult = queries.createVenue(pool, newVenueData, function(success) {
                if (success != -1) {
                        response.send({"status": success});
                } else {
                        response.send({"status": success});
                }
        });
});


router.post('/friendrequest/', function(request, response) { // create new friend request
        var newFwData = request.body;
        var queryResult = queries.createFriendRequest(pool, newFwData, function(success) {
                if (success == true) {
                        response.send({"status": "success"});
                } else {
                        response.send({"status": "failure"});
                }
        });
});


router.post('/friendrequestaccept/', function(request, response) { // accept friend request
	var fwData = request.body;
        var queryResult = queries.acceptFriendRequest(pool, fwData, function(success) {
                if (success == true) {
                        response.send({"status": "success"});
                } else {
                        response.send({"status": "failure"});
                }
        });
});



router.post('/friendrequestdeny/', function(request, response) { // deny friend request
        var fwData = request.body;
        var queryResult = queries.denyFriendRequest(pool, fwData, function(success) {
                if (success == true) {
                        response.send({"status": "success"});
                } else {
                        response.send({"status": "failure"});
                }
        });
});



app.use('/', router);
app.listen(9001);


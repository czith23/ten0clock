module.exports = {

	/*
	creatUser - Creates user in database, sends callback of true on success, false otherwise
	args: connectionPool - MySQL connection pool
	user: JavaScript object representation of user
	callback: callback function to be used to determine success and pass back to client.
	returns -1 if function fails, else returns the id of the inserted row
	*/

	createUser: function(connectionPool, user, callback) {
		return connectionPool.getConnection(function sendQuery(err, connection) {
			if (err) {
				callback(-1);
			} else {
				 connection.query('INSERT INTO user SET ?', user, function queryResult(err, result) {
					connection.release();
					if (err) {
						callback(-1);
					} else {
						callback(result.insertId);
					}
				});
			}
		});
	},





	getUserData: function(connectionPool, username, callback) {
		return connectionPool.getConnection(function sendQuery(err, connection) {
			connection.query('SELECT * FROM user WHERE username = ?', [username], function(err, result) {
				connection.release();
				if (result[0] === undefined) {
					callback(false, null);
				} else {	
					callback(true, result[0]);
				}
			});
		});
	},




	createEvent: function(connectionPool, eVent, callback) {
		return connectionPool.getConnection(function sendQuery(err, connection) {
			if (err) {
				callback(-1);
			} else {
				connection.query('INSERT INTO event SET ?', eVent, function queryResult(err, result) {
					connection.release();
					if (err) {
						callback(-1);
					} else {
						callback(result.insertId);
					}
				});
			}
		});
	},




	createVenue: function(connectionPool, venue, callback) {
                return connectionPool.getConnection(function sendQuery(err, connection) {
                        if (err) {
                                callback(-1);
                        } else {
                                connection.query('INSERT INTO venue SET ?', venue, function queryResult(err, result) {
                                        connection.release();
                                        if (err) {
                                                console.log(err);
						callback(-1);
                                        } else {
                                                callback(result.insertId);
                                        }
                                });
                        }
                });
        },

	


	createFriendRequest: function(connectionPool, request, callback) {
		return connectionPool.getConnection(function sendQuery(err, connection) {
			if (err) {
				callback(false);
			} else {
				connection.query('INSERT INTO user_friend_request SET ?', request, function queryResult(err, result) {
					connection.release();
					if (err) {
						callback(false);
					}
					else {
						callback(true);
					}
				});
			}
		});
	},

	


	acceptFriendRequest: function(connectionPool, request, callback) {
                return connectionPool.getConnection(function sendQuery(err, connection) {
			if (err) {
                                callback(false);
                        } else {
				var requester_id = request.requester_id;
				var requestee_id = request.requestee_id;
				var date_established = request.date_established;
                                connection.query('DELETE FROM user_friend_request WHERE `requester_id`=? AND `requestee_id`=?;', [requester_id, requestee_id], function queryResult(err, result) {
                                 
                                        if (err) {
                                                callback(false);
                                        }
                                        else {
                                                connection.query('INSERT INTO user_friendship (user_id,friend_id,date_established) VALUES (?,?,?);', [requester_id, requestee_id, date_established], 
						function queryResult(err, result) {
							connection.release();
							if (err) {
								callback(false);
							} else {
								callback(true);
							}
                                        	});
                                	}
                        });
                }
        });
	},


	


	denyFriendRequest: function(connectionPool, request, callback) {
                return connectionPool.getConnection(function sendQuery(err, connection) {
                        if (err) {
                                callback(false);
                        } else {
                                var requester_id = request.requester_id;
                                var requestee_id = request.requestee_id;
                                var date_established = request.date_established;
                                connection.query('DELETE FROM user_friend_request WHERE `requester_id`=? AND `requestee_id`=?;', [requester_id, requestee_id], function queryResult(err, result) {

                                        if (err) {
                                                callback(false);
                                        }
                                        else {
						callback(true);
                                        }
                        });
                }
        });
        }
}

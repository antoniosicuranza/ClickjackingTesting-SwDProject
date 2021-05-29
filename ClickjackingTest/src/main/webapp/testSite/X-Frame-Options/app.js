var express = require('express');
const path = require('path')
var app = express()
const port = 8080
var args  = 0
var readline = require('readline');

var rl = readline.createInterface({
 input: process.stdin,
 output: process.stdout
});

function waitForUserInput() {
  rl.question("Command: ", function(answer) {
    if (answer == 1 || answer == 2){
		args = answer;
        rl.close();
    } else {
        waitForUserInput();
    }
  });
}
app.get('/index.html', function (req,res){
	res.sendFile('index.html', {
		root: path.join(__dirname, '.')
	})
})


app.get('/frame.html', function (req,res){
	console.log('eseguo comando: ' + args);
	if(args == 1){
		console.log('setto header con X-Frame-Options DENY: ');
		res.setHeader('X-Frame-Options','DENY');
	}
	if(args ==2){
		console.log('setto header con SAMERORIGIN: ');
		res.setHeader('X-Frame-Options','SAMERORIGIN');
	}
	res.sendFile('frame.html', {
		root: path.join(__dirname, '.')
	})
})

var server = app.listen(port, function(error){
	var host = server.address().address
	var port = server.address().port
	if (error) {
		console.log('something wrong', error)
	} else {
		console.log("Server listening at http://%s:%s", host, port)
	}
	if(args == 0){
		console.log('inserire 1 per X-Frame-Options DENY o 2 X-Frame-Options SAMERORIGIN');
		waitForUserInput();
	}
})
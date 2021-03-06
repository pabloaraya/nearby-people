var app = require('http').createServer(handler)
var io = require('socket.io')(app);
var fs = require('fs');

app.listen(80);

function handler (req, res) {
  fs.readFile(__dirname + '/index.html',
  function (err, data) {
    if (err) {
      res.writeHead(500);
      return res.end('Error loading index.html');
    }

    res.writeHead(200);
    res.end(data);
  });
}

io.on('connection', function (socket) {
  console.log("New user!");
  socket.emit('connect', { hello: 'world' });
  socket.on('message', function (data) {
    console.log(data);
    socket.broadcast.emit('message', data);
  });
  socket.on('disconnect', function (data) {

  });
});
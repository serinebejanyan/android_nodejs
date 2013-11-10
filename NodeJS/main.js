var http = require('http'),
    express = require('express'),
    cake_module = require('./models/cake'),
    mysql = require('mysql');

var app = express();
app.use(express.bodyParser());

var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '123456',
    database: 'test'

});

app.post('/addCake', function(request, result){
    console.log(request.body);
    var name = request.body.name;
    var brand = request.body.brand;
    var type = request.body.type;
    var price = request.body.price;
    var weight = request.body.weight;

    var post  = {
        name: name,
        brand: brand,
        type:type,
        price: price,
        weight:weight
    };
    connection.query('Insert into Cake SET ?',post,function(post_err, post_result) {

        if (post_err) {
            result.send(post_err);
        } else {
            result.send(post_result);
        }
    });
    connection.end();
});

app.get('/getCakes', function(req, res, next){


    connection.query('SELECT * FROM Cake', function(err, rows, fields){
        if(err) throw err;
        var values = "";
        var cake_array = new Array();
        for (i = 0; i < rows.length; i++)
        {
            var cake = new  cake_module.cake();
            values = rows[i];
            cake.set_brand(values.brand);
            cake.set_name(values.name);
            cake.set_price(values.price);
            cake.set_type(values.type);
            cake.set_id(values.ID);
            cake.set_weight(values.weight);
            cake_array.push(cake);

        }
        res.json(cake_array);
    });

});
app.get('/addcake', function(req, res, next) {
    var name = 'paxlava';
    var brand = 'Grand Candy';
    var price = 250;
    var weight = 200;
    var type = 'with cream';

    var post  = {
        name: name,
        brand: brand,
        type:type,
        price: price,
        weight:weight
    };

//    var query_stirng = 'Insert into Cake Values("'+name+'","gdet",'+price+','+price+')';
  //  console.log(query_stirng);
    connection.query('Insert into Cake SET ?',post,function(sql_err, sql_result) {

        if (sql_err) {
             res.send(sql_err);
        } else {
            res.send(sql_result);
         };
//    console.log("dfsgsag");
//    connection.end();
    });
});

http.createServer(app).listen(3001, function(){
    console.log("Server started!");
});

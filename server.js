var http = require("http");
var url = require("url");
 
function start(router,handler) {
  function onRequest(request, response) {
	var postData = "";
	var pathname = url.parse(request.url).pathname;
   
	//request.setEncoding("utf8");
  
    
	request.addListener("data",function(dataChunk){
		postData += dataChunk;
	});
	
  
	request.addListener("end",function(){
		router(handler,pathname,response,postData);
	});
  }
  
 
  http.createServer(onRequest).listen(8888);
  console.log("Server has started.");
}
 
exports.start = start;
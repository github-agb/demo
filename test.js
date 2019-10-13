const a = require('child_process');
var b = a.exec('dir', function (error, stdout, stderr) {
        console.log(b.error);
		console.log('------------------------------------------');
        console.log(b.stdout);
		console.log('------------------------------------------');
        console.log(b.stderr);
    });
console.log('hehe');

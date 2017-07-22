module.exports = {
    greet: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "RfidScanner", "greet", [name]);
    },
    scan: function ( successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "RfidScanner", "scan",[]);
    }
};
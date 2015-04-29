currencyConverterApp.factory('currencyService', function($http) { 
	return {
		getCurrencies: function() {
			return $http.get("/api/currency/list").then(function(resp) {
					return resp.data;
			});
		},
		
		convert: function(value, fromCode, toCode) {
			var request = {
				"value": value,
				"fromCode": fromCode,
				"toCode": toCode
			};
			
			return $http.post("/api/currency/convert", request).then(function(resp) {
				return resp.data;
			});
		}
	};
});
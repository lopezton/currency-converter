currencyConverterApp.controller('converterCtrl', [
'$scope',
'currencyService',
function($scope, currencyService) {

	$scope.value = 1;
	
	currencyService.getCurrencies().then(function (data) {
		$scope.currencies = data;
		$scope.supportedCurrenciesList = [];
		for(var i in data) {
			$scope.supportedCurrenciesList.push(data[i].code);
		}
	});
	
	$scope.convert = function (value, fromCode, toCode) {
		// TODO - Research Angular controller-layer validation
		if (!$.isNumeric(value)) {
			return;
		}
		
		if (isEmpty(fromCode) || isEmpty(toCode)) {
			return;
		}

		currencyService.convert(value, fromCode, toCode).then(function(data) {
			$scope.convertedResponse = data;
		});
	};
	
	function isEmpty(str) {
		return !str || str.length === 0; 
	}
	
	// Initial on load case
	$scope.convert($scope.value, "USD", "CNY");
}]);
<!DOCTYPE html>

<html>
<head>
  <title>Currency Converter</title>
  <link rel="stylesheet" href="/css/bootstrap.css" />
  <link rel="stylesheet" href="/css/home.css" />
</head>
<body ng-app="currencyConverterApp">
    
    <div id="page-content-wrapper">
    	<section id="converter" class="" ng-include="'/client/components/converter/converterView.html'" ng-controller="converterCtrl"></section>
    </div>
    
    <%-- JQuery Dependencies --%>
    <script src="/js/jquery.min.js"></script>
    
    <%-- Bootstrap Dependencies --%>
    <script src="/js/bootstrap.min.js"></script>
    
    <%-- Angular Dependencies --%>
    <script src="/js/angular.min.js"></script>
    <script src="/js/angular-route.min.js"></script>
    <script src="/js/angular-ui-bootstrap.js"></script>
    
    	<%-- Configuration --%>
    	<script src="/client/app.module.js"></script>
    	<%-- <script src="/client/app.routes.js"></script> --%>
    
    	<%-- Controllers --%>
    	<script src="/client/components/converter/converterController.js"></script>
    	
    	<%-- Services --%>
    	<script src="/client/services/currencyService.js"></script>
</body>
</html>
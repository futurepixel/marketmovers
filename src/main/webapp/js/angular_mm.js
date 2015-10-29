var app = angular.module('marketmovers', []);

app.controller('MainController', ['$scope', '$timeout', 'DataAccessService', 'TestStockService', function($scope, $timeout, DataAccessService, TestStockService) {

    DataAccessService.get('/test/top/' + 18)
        .done(function(stocks) {
            $timeout(function() {
                $scope.stocks1 = stocks;
                TestStockService.getStuff(18)
                    .done(function(stocks) {
                        $timeout(function() {
                            $scope.stocks2 = stocks;
                        }, 0);
                    })
            }, 0)
        });

    $scope.helloWord = new Date();
}]);
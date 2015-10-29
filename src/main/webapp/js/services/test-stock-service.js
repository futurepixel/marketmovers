app.service('TestStockService', ['DataAccessService', function (DataAccessService) {

    var _urlPrefix = '/test';

    return {
        getStuff: function(number) {
            return DataAccessService.get(_urlPrefix + '/top/' + number);
        }
    };

}]);
/**
 * Service to perform jQuery AJAX calls
 */
app.service('AjaxService', function () {
    return {
        request: function (options) {
            return $.ajax(options);
        }
    }
});

/**
 * Service to perform generic GET/POST actions (Uses JQuery promises)
 */
app.service('DataAccessService', ['AjaxService', 'MM_CONST',
    function (AjaxService, MM_CONST) {

        var _urlPrefix = '/data';

        /**
         * Execute an AJAX request
         * @param type
         * @param url
         * @param data
         * @returns {*}
         * @private
         */
        var _request = function (type, url, data) {
            var options = {
                type: type,
                url: _urlPrefix + url,
                dataType: 'json'
            };
            if (data) {
                options.data = JSON.stringify(data);
                options.contentType = 'application/json';
            }

            var deferred = $.Deferred();

            AjaxService.request(options)
                .done(function (response) {
                    _isSuccess(response)
                        ? _success(deferred, response)
                        : _fail(deferred, response);
                })
                .fail(function (jqXHR, status, error) {
                    _error(deferred, jqXHR, error);
                });

            return deferred;
        };

        /**
         * Determine from the response whether the request was successful
         * @param response
         * @returns {*|boolean}
         * @private
         */
        var _isSuccess = function(response) {
            return response && (response.status === MM_CONST.REQUEST_STATUS.SUCCESS);
        };

        /**
         * Resolve the promise with response data; log messages
         * @param deferred
         * @param response
         * @private
         */
        var _success = function(deferred, response) {
            deferred.resolve(response.data);
            if (response.message){
                console.log(response.message);
            }
        };

        /**
         * Reject the promise; log messages
         * @param deferred
         * @param response
         * @private
         */
        var _fail = function(deferred, response) {
            deferred.reject(response);
            console.error(response);
        };

        /**
         * Reject the promise; notify user of certain statuses; log messages
         * @param deferred
         * @param jqXHR
         * @param error
         * @private
         */
        var _error = function(deferred, jqXHR, error) {
            var responseJson = jqXHR.responseJSON;
            if (angular.isDefined(responseJson)){
                console.error(jqXHR.status, error, responseJson.message);
                deferred.reject(responseJson)
            } else {
                console.error(jqXHR.status, error, 'No message provided');
                deferred.reject();
            }

        };

        return {
            get: function (url) {
                return _request('GET', url, null);
            },
            post: function (url, data) {
                return _request('POST', url, data);
            }
        }

    }]);
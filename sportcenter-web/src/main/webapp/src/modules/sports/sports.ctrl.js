(function (ng) {
    var mod = ng.module("sportModule");
    mod.constant("sportContext", "api/sports");
    mod.controller('sportCtrl', ['$scope', '$http', 'sportContext',
        function ($scope, $http, sportContext) {
            $http.get(sportContext).then(function (response) {
                $scope.sportsRecords = response.data;
            });
        }
    ]);
}
)(window.angular);
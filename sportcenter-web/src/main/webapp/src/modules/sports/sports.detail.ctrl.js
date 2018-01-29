(function (ng) {
    var mod = ng.module("sportModule");
    mod.constant("sportContext", "api/sports");
    mod.controller('sportDetailCtrl', ['$scope', '$http', 'sportContext', '$state', '$filter',
        function ($scope, $http, sportContext, $state, $filter) {

            if (($state.params.sportId !== undefined) && ($state.params.sportId !== null)) {
                $http.get(sportContext + "/" + $state.params.sportId).then(function (response) {
                    $scope.currentSport = response.data;
                });
            }
        }
    ]);
}
)(window.angular);
(function (ng) {
    var mod = ng.module("sportModule");
    mod.constant("sportsContext", "api/sports");
    mod.controller('sportNewCtrl', ['$scope', '$http', 'sportsContext', '$state', '$rootScope',
        function ($scope, $http, sportsContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createSport = function () {
                $http.post(sportsContext, $scope.data).then(function (response) {
                    $state.go('sportsList', {sportId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);